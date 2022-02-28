package TCN;

import java.util.ArrayList;
import java.util.HashMap;

public class Matrix {



    private HashMap<Node, HashMap<Node,Edge>>matrix;

    public Matrix(ArrayList<Edge> edgeList, ArrayList<Node> nodes){

        matrix = new HashMap<>();

        for (Node source : nodes
             ) {
            matrix.put(source, new HashMap<Node, Edge>());
        }

        for (Edge e: edgeList
             ) {
            updateMatrix(e);
        }
    }

    public HashMap<Node, HashMap<Node, Edge>> getMatrix() {
        return matrix;
    }

    public boolean updateMatrix(Edge edge){

        Edge oldEdge = matrix.get(edge.getSource()).get(edge.getTarget());

        if(oldEdge!=null && oldEdge.getNonLabeledValue()!=Integer.MAX_VALUE){

            int newValue = edge.getNonLabeledValue();
            int oldValue = oldEdge.getNonLabeledValue();
            if(oldValue<=newValue){
                return false;
            }
        }
        matrix.get(edge.getSource()).put(edge.getTarget(),edge);
        return true;
    }
    
    public void printMatrix(){

        int padding = getPadding();

        System.out.print(addPadding("",padding)+"|");
        for (Node n: matrix.keySet()
             ) {
            System.out.print(addPadding(n.getId(),padding)+"|");
        }
        System.out.println("\n"+addBoarder(padding));
        for (Node source: matrix.keySet()
             ) {
            System.out.print(addPadding(source.getId(),padding)+"|");
            for (Node target: matrix.get(source).keySet()
                 ) {
                String value = "";
                value +=matrix.get(source).get(target).getNonLabeledValue();
                if(matrix.get(source).get(target).getNonLabeledValue()!=Integer.MAX_VALUE){
                    System.out.print(addPadding(value,padding)+"|");
                }
                else{
                    System.out.print(addPadding("c:?",padding)+"|");
                }
            }
            System.out.println();
            //System.out.println("\n"+addBoarder(padding));
        }
    }

    private int getPadding(){
        int max = 0;
        for (Node n: matrix.keySet()
             ) {
            int current = n.getId().length();
            if(current>max){
                max = current;
            }
        }
        return max;
    }

    private String addPadding(String s, int padding){
        int current = s.length();
        int diff = padding-current;

        StringBuilder ret = new StringBuilder();
        ret.append(s);

        for (int i = 0; i<=diff; i++) {
            ret.append(" ");
        }
        return ret.toString();
    }

    private String addBoarder (int padding){
        int lenght = matrix.keySet().size();
        lenght = (lenght+2)*padding;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= lenght+matrix.keySet().size(); i++){
            sb.append("_");
        }
        sb.append("_");
        return sb.toString();
    }



}
