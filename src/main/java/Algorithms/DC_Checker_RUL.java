package Algorithms;

import TCN.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DC_Checker_RUL {
    private STNU stnu;
    boolean rulPlus;
    ArrayList<Status> history;

    public DC_Checker_RUL(STNU original, boolean rulPlus){
        this.rulPlus = rulPlus;
        this.stnu = original.getCopy();

    }

    public Result check (){
        history = new ArrayList<>();
        Status status = new Status();
        int runs = 0;
        int step = 0;

        boolean done;
        long before = System.currentTimeMillis();
        do {
           done = true;
           runs++;

           STNUIterator iterator = new STNUIterator(stnu);

           while (iterator.hasNext()) {

               status = applyRUL(iterator.next());

               if(status.hasDerived()){

                   done = false;
                   status.setStep(++step);
                   history.add(status);

                   if(!status.isDc()) break;
               }
           }
       } while(!done && status.isDc());
       return new Result(history, stnu, status.isDc(), runs, System.currentTimeMillis()-before);

    }

    private Status applyRUL(STNUPath path){

       Status relaxStatus = relax(path);
       if(relaxStatus.hasDerived()){
           if(stnu.addEdge(relaxStatus.getDerivedEdge())){
               return relaxStatus;
           }
       }

        Status upperStatus = upper(path);
        if(upperStatus.hasDerived()){
            if(stnu.addEdge(upperStatus.getDerivedEdge())){
                return upperStatus;
            }
        }

        Status lowerStatus = lower(path);
        if(lowerStatus.hasDerived()){
            if(stnu.addEdge(lowerStatus.getDerivedEdge())){
                return lowerStatus;
            }
        }


        return new Status();

    }

    private Status relax(STNUPath path){

        Status status = new Status();
        status.setRule(RULE.RELAX);
        status.setPath(path);

        Edge e1 = path.getE1();
        Edge e2 =path.getE2();
        Node p = e1.getSource();
        Node q = e1.getTarget();
        Node r = e2.getTarget();
        int v = e1.getNonLabeledValue();
        int w = e2.getNonLabeledValue();

        //System.out.println("Checking RELAX "+p.getName() +"-- "+v+" --> "+q.getName()+" -- "+w+" -->"+r.getName());
        if (v != Integer.MAX_VALUE && w != Integer.MAX_VALUE && !p.equals(q) && !q.equals(r)) {
            if (rulPlus) {
                if ((p.isContingent() || p.getId().equals("Z")) && (!q.isContingent() && !q.getId().equals("Z")) && v > 0) {

                    status.setRetValue(v+w);
                    status.setHasDerived(true);
                } else {
                    return status;
                }
            } else{
                status.setRetValue(v+w);
                status.setHasDerived(true);
            }
            status.setDc(checkDC(p,r,status.getRetValue()));
            Edge derivedEdge = new Edge("e*",p,r,status.getRetValue(),ConstraintType.nL);
            derivedEdge.setDerivedBy(path);
            status.setDerivedEdge(derivedEdge);
        }

        return status;
    }

    private Status upper(STNUPath path){

        Status status = new Status();
        status.setRule(RULE.UPPER);
        status.setPath(path);

        Edge e1 = path.getE1();
        Edge e2 =path.getE2();

        Node p = e1.getSource();
        Node c = e1.getTarget();
        Node ac = e2.getTarget();
        int v = e1.getNonLabeledValue();
        int uc = e2.getUpperLabeledValue();
        // System.out.println("Checking UPPER "+p.getName() +"-- "+v+" --> "+c.getName()+" -- "+uc+" -->"+ac.getName());
        if (v != Integer.MAX_VALUE && uc != Integer.MAX_VALUE && !p.equals(c) && !c.equals(ac)) {
            if (c.isContingent() && !ac.isContingent() && e2.getLabelNode().equals(c)) {
                if (rulPlus) {
                    if ((p.isContingent() || p.getId().equals("Z")) && v > uc) {

                        status.setRetValue(v+uc);
                        status.setHasDerived(true);
                    } else{
                        return status;
                    }
                } else {

                    Edge l = stnu.getEdge(ac,c);

                    if(l!=null && l.getLowerLabeledValue()!=Integer.MAX_VALUE&&l.getLabelNode().equals(c)){


                        int l_value= l.getLowerLabeledValue() * -1;

                        // System.out.println("max( "+v+" - "+uc+" , "+lc+") = "+Integer.max(v+uc,lc));

                        status.setRetValue(Integer.max(v + uc, l_value));
                        status.setHasDerived(true);

                        status.setDc(checkDC(p,ac,status.getRetValue()));
                        Edge derived = new Edge("e*",p,ac,status.getRetValue(),ConstraintType.nL);
                        derived.setDerivedBy(path);
                        status.setDerivedEdge(derived);
                    }
                }
            }

        }

        return status;
    }

    private Status lower(STNUPath path) {

        Status status = new Status();
        status.setRule(RULE.LOWER);
        status.setPath(path);

        Edge e1 = path.getE1();
        Edge e2 =path.getE2();

        Node ac = e1.getSource();
        Node c = e1.getTarget();
        Node r = e2.getTarget();
        int lc = e1.getLowerLabeledValue();
        int w = e2.getNonLabeledValue();
        //System.out.println("Checking LOWER "+ac.getName() +"-- "+lc+" --> "+c.getName()+" -- "+w+" -->"+r.getName());
        //System.out.println("   "+r.getName()+" is contingent? "+r.isContingent());
        if (lc != Integer.MAX_VALUE && w != Integer.MAX_VALUE  && !ac.equals(c) && !c.equals(r)) {
            if (!ac.isContingent() && c.isContingent() && e1.getLabelNode().equals(c)) {
                //System.out.println("   "+w+" <= 0 ?");
                if (!r.isContingent() && w <= 0) {
                    //      System.out.println("   true");

                    status.setRetValue(lc+w);
                    status.setHasDerived(true);
                    status.setDc(checkDC(ac,r,status.getRetValue()));
                    Edge derived = new Edge("e*",ac,r,status.getRetValue(),ConstraintType.nL);
                    derived.setDerivedBy(path);
                    status.setDerivedEdge(derived);


                }
                // System.out.println(false);
                if (r.isContingent() && !r.equals(c)) {
                    Edge ur_edge = stnu.getEdge(r, r.getActivationTimepoint()); //Assumption: Every contingent node, has an activation time point and there is an edge with  uc

                    int ur = ur_edge.getUpperLabeledValue();
                    //   System.out.println("   "+r.getName()+"(R) -- "+ur+" --> "+r.getActivationTimepoint().getName());
                    if (w <= (ur*-1)) {
                        status.setRetValue(lc+w);
                        status.setHasDerived(true);
                        status.setRetValue(lc+w);
                        status.setHasDerived(true);
                        status.setDc(checkDC(ac,r,status.getRetValue()));
                        Edge derived = new Edge("e*",ac,r,status.getRetValue(),ConstraintType.nL);
                        derived.setDerivedBy(path);
                        status.setDerivedEdge(derived);

                    }
                }
            }
        }
        return status;
    }


    private boolean checkDC(Node p, Node r, int value){
        return p != r || value >= 0;
    }



}
