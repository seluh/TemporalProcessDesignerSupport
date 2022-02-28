package Algorithms;

        import TCN.Edge;
        import TCN.STNU;
        import TCN.STNUPath;

        import java.util.ArrayList;
        import java.util.List;

public class Resolver {

    private STNU stnu;
    private ArrayList<Status> history;

    public Resolver(Result r){
        this.stnu = r.getDerivedSTNU().getCopy();
        this.history = r.getHistory();


    }

    public ArrayList<Edge> getCandidates(){
        Status conflict = history.get(history.size()-1);
        STNUPath conflictPath = conflict.getPath();

        if(conflict.isDc()){
            return null;
        }

        Edge e1 = conflictPath.getE1();
        Edge e2 = conflictPath.getE2();

       ArrayList<Edge> roots = new ArrayList<>();
       roots.add(e1);
       roots.add(e2);

       ArrayList<Edge> canditates = new ArrayList<>();

        for (Edge e: roots
             ) {
            traverse(e,canditates);
        }

        return filter(canditates);

    }

    private void traverse (Edge node, ArrayList<Edge> candidates){
        STNUPath path = node.getDerivedBy();
        if(path == null){
            candidates.add(node);
            return;
        }
        traverse(path.getE1(), candidates);
        traverse(path.getE2(), candidates);
    }

    private  ArrayList<Edge> filter (ArrayList<Edge> candidates){
        ArrayList<Edge>ret = new ArrayList<>();
        for (Edge e: candidates
             ) {
            if(!e.getTarget().getId().equals("O") && !e.getTarget().getId().equals("Z") && !e.getSource().getId().equals("O") && !e.getSource().getId().equals("Z")){
                ret.add(e);
            }
        }
        return ret;
    }
}
