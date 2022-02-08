package STNU;

public class ContingentLink extends Edge {



    private Node contingentTimepoint;

    @Override
    public String toString() {
        return "ContingentLink{" +
                "id='" + super.getId() + '\'' +
                ", source=" + super.getSource() +
                ", target=" + super.getTarget() +
                ", value=" + super.getValue() +
                "contingentTimepoint=" + contingentTimepoint +
                '}';
    }

    public ContingentLink(String id, Node source, Node target, Node contingentTimepoint, int value) {
        super(id, source, target, value);
        this.contingentTimepoint = contingentTimepoint;
    }

    @Override
    public ContingentLink getCopy() {
       return new ContingentLink(super.getId(),super.getSource(),super.getTarget(),contingentTimepoint,super.getValue());
    }

    public Node getContingentTimepoint() {
        return contingentTimepoint;
    }

}
