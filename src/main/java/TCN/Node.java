package TCN;

public class Node {

    private String id;



    private boolean isContingent;

    public Node getActivationTimepoint() {
        return activationTimepoint;
    }

    public void setActivationTimepoint(Node activationTimepoint) {
        this.activationTimepoint = activationTimepoint;
    }

    private Node activationTimepoint;

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", isContingent=" + isContingent +
                '}';
    }

    public Node(String name, boolean isContingent, Node activationTimepoint){
        this.isContingent = isContingent;
        this.id = name;
        this.activationTimepoint = activationTimepoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isContingent() {
        return isContingent;
    }

    public void setContingent(boolean contingent) {
        isContingent = contingent;
    }
}
