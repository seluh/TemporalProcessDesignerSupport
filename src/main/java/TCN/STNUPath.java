package TCN;

public class STNUPath {


    private Edge e1;
    private Edge e2;

    @Override
    public String toString() {
        return "STNUPath{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                '}';
    }

    public STNUPath(Edge e1, Edge e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public Edge getE1() {
        return e1;
    }

    public Edge getE2() {
        return e2;
    }
}
