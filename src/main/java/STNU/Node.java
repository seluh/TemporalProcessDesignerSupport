package STNU;

public class Node {

    private String id;
    private boolean isContingent;

    public Node(String name, boolean isContingent){
        this.isContingent = isContingent;
        this.id = name;
    }
}
