package Algorithms;

import TCN.ConstraintType;
import TCN.Edge;
import TCN.Node;
import TCN.STNU;

public class BinarySearch  {

    private STNU originalstnu;
    private Node source;
    private  Node target;



    public BinarySearch(STNU stnu, Edge e) {
        this.originalstnu = stnu;
        this.source = e.getSource();
        this.target = e.getTarget();

    }

    public int getValue (){
        int right = originalstnu.getDeadline();
        int left = right*-1;
        return search(left,right);
    }


    private int search(int left, int right)  {

        STNU currentSTNU = originalstnu.getCopy();

        int mid = (left + (right) )/ 2;
        // System.out.println("trying  "+mid);
        Edge e = new Edge("mrc",source,target,mid, ConstraintType.nL);
        currentSTNU.addEdge(e);
        DC_Checker_RUL solver = new DC_Checker_RUL(currentSTNU,false);
        // solver.updateEdge(e);
        // System.out.println("trying with: "+solver.getDerivedSTNU().getEdge(source,target).toString());
        Result r = solver.check();

        if (r.isDC) {
            //  System.out.println("DC:  search left: "+left+"-"+mid);

            if(Math.abs(mid)-Math.abs(left)==-1 || Math.abs(mid)-Math.abs(left)==0) {
                return mid;
            }


            return search(left, mid);
        } else {
            //      System.out.println("NOT DC:   search right: "+mid +" -"+right);

            if(Math.abs(right) - Math.abs(mid) ==1 || Math.abs(right) - Math.abs(mid) == -1 ||Math.abs(right) - Math.abs(mid) == 0){
                return right;
            }
            return search(mid, right);
        }
    }

}
