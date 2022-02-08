package STNU;

import java.util.ArrayList;
import java.util.List;

public class STNU {
    private List <Edge> edges;
    private List <Node> nodes;
    private List <ContingentLink>  contingentLinks;
    private List <Node> contingentTimepoints;
    private int edgeCount;
    private int deadline;

    public STNU(List<Edge> edges, List<Node> nodes, int deadline) {

        this.edges = new ArrayList<>();
        this.edges.addAll(edges);
        this.nodes = new ArrayList<>();
        this.nodes.addAll(nodes);
        this.edgeCount = edges.size();
        this.deadline = deadline;

        initSTNU();

    }

    public STNU getCopy(){
        List<Edge> edgeList = new ArrayList<>();
        List<Node> nodesList = new ArrayList<>(nodes);

        for (Edge e:edges
        ) {
            edgeList.add(e.getCopy());
        }
        return new STNU(edgeList,nodesList,deadline);
    }

    private void initSTNU(){
        /**
         * Assumption: Each STNU represents a process model, thus each STNU has a deadline
         */
        Node z = new Node("Z",false); //zero time point i.e start of process
        Node o = new Node("O",false); //end time point
        nodes.add(z);
        nodes.add(o);

        for (Node n: nodes
             ) {
            addEdge(new Edge("e"+edgeCount,n,z,0));
            addEdge(new Edge("e"+edgeCount,n,o,deadline));
            addEdge(new Edge("e"+edgeCount,o,n,0));
        }

    }

    public void addEdge(Edge e){
        edgeCount++;
        edges.add(e);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<ContingentLink> getContingentLinks() {
        return contingentLinks;
    }

    public List<Node> getContingentTimepoints() {
        return contingentTimepoints;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }





}
