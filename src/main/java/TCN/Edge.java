package TCN;

public class Edge {
    private String id;
    private Node source;
    private Node target;

    private int nonLabeledValue = Integer.MAX_VALUE;
    private int upperLabeledValue = Integer.MAX_VALUE;
    private int lowerLabeledValue =Integer.MAX_VALUE;



    public STNUPath getDerivedBy() {
        return derivedBy;
    }

    public void setDerivedBy(STNUPath derivedBy) {
        this.derivedBy = derivedBy;
    }

    private STNUPath derivedBy = null;

    public Node getLabelNode() {
        return labelNode;
    }

    public void setLabelNode(Node labelNode) {
        this.labelNode = labelNode;
    }

    private Node labelNode;


    private Edge(String id, Node source, Node target, int nonLabeledValue, int upperLabeledValue, int lowerLabeledValue, Node labelNode) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.nonLabeledValue = nonLabeledValue;
        this.upperLabeledValue = upperLabeledValue;
        this.lowerLabeledValue = lowerLabeledValue;
        this.labelNode = labelNode;
    }

    public Edge (String id, Node source, Node target, int value, ConstraintType type){
        this.id = id;
        this.source = source;
        this.target = target;

        if(type.equals(ConstraintType.lC)){
            lowerLabeledValue = value;
            labelNode = target;
        } else if(type.equals(ConstraintType.uC)){
            upperLabeledValue = value;
            labelNode = source;
        } else{
          nonLabeledValue = value;
        }

    }



    public Edge getCopy(){
       return new Edge(this.id,this.source,this.target,this.nonLabeledValue,this.upperLabeledValue,this.lowerLabeledValue,this.labelNode);
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

    public int getNonLabeledValue() {
        return nonLabeledValue;
    }

    public void setNonLabeledValue(int nonLabeledValue) {
        this.nonLabeledValue = nonLabeledValue;
    }

    public int getUpperLabeledValue() {
        return upperLabeledValue;
    }

    public void setUpperLabeledValue(int upperLabeledValue) {
        this.upperLabeledValue = upperLabeledValue;
    }

    public int getLowerLabeledValue() {
        return lowerLabeledValue;
    }

    public void setLowerLabeledValue(int lowerLabeledValue) {
        this.lowerLabeledValue = lowerLabeledValue;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                ", source=" + source +
                ", target=" + target +
                ", nonLabeledValue=" + nonLabeledValue +
                ", upperLabeledValue=" + upperLabeledValue +
                ", lowerLabeledValue=" + lowerLabeledValue +
                ", derivedBy=" + derivedBy +
                ", labelNode=" + labelNode +
                '}';
    }



}
