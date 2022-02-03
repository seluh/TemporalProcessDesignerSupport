package STNU;

public class Edge {
    private Node source;
    private Node target;
    double value;

    public Edge (Node source, Node target, double value){
        this.source = source;
        this.target = target;
        this.value = value;
    }
}
