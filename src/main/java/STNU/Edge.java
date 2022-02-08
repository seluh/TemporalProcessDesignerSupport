package STNU;
import STNU.*;

public class Edge {
    private String id;
    private Node source;
    private Node target;
    private int value;



    public Edge (String id, Node source, Node target, int value){
        this.id = id;
        this.source = source;
        this.target = target;
        this.value = value;
    }

    public Edge getCopy(){
       return new Edge(id,source,target,value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                ", source=" + source +
                ", target=" + target +
                ", value=" + value +
                '}';
    }
}
