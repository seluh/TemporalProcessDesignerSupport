package TCN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class STNU {
    private ArrayList <Edge> edges;
    private ArrayList <Node> nodes;
    private int edgeCount;

    public int getDeadline() {
        return deadline;
    }

    private int deadline;

    public Matrix getMatrix() {
        return matrix;
    }

    private Matrix matrix;

    public STNU(List<Edge> edges, List<Node> nodes, int deadline, boolean initialize) {

        this.edges = new ArrayList<>();
        this.edges.addAll(edges);
        this.nodes = new ArrayList<>();
        this.nodes.addAll(nodes);
        this.edgeCount = edges.size();
        this.deadline = deadline;

         if(initialize){
             initSTNU();
         }
        matrix = new Matrix(this.edges,this.nodes);
    }

    public STNU getCopy(){
        List<Edge> edgeList = new ArrayList<>();
        List<Node> nodesList = new ArrayList<>(nodes);

        for (Edge e:edges
        ) {
            edgeList.add(e.getCopy());
        }
        return new STNU(edgeList,nodesList,deadline,false);
    }

    private void initSTNU(){
        /**
         * Assumption: Each STNU represents a process model, thus each STNU has a deadline
         */
        Node z = new Node("Z",false, null); //zero time point i.e start of process
        Node o = new Node("O",false, null); //end time point
        nodes.add(z);
        nodes.add(o);

        for (Node n: nodes
             ) {
            edges.add(new Edge("e"+edgeCount,n,z,0,ConstraintType.nL));
            edgeCount++;
            edges.add(new Edge("e"+edgeCount,n,o,deadline,ConstraintType.nL));
            edgeCount++;
            edges.add(new Edge("e"+edgeCount,o,n,0,ConstraintType.nL));
            edgeCount++;
        }

    }

    public Edge getEdge(Node source, Node target){
        for (Edge e: edges
             ) {
            if(e.getSource().equals(source)&&e.getTarget().equals(target)){
                return e;
        }


        }
        return null;
    }

    public boolean addEdge(Edge newEdge){

        if (matrix.updateMatrix(newEdge)){
            edgeCount++;
            edges.add(newEdge);
            return true;
        }
        return false;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
