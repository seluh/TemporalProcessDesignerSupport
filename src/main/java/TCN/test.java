package TCN;

import Algorithms.BinarySearch;
import Algorithms.DC_Checker_RUL;
import Algorithms.Resolver;
import Algorithms.Result;
import Utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {

        Node as = new Node("as",false, null);
        Node ae = new Node("ae",true,as);
        Node start = new Node("start",false,null);
        Node end = new Node("end",false,null);

       // Node n3 = new Node("bs",false);
      //  Node n4 = new Node("be",true);
        Edge edge1 = new Edge("1",as,ae,4,ConstraintType.nL);
        Edge edge2 = new Edge("2",ae,as,-1, ConstraintType.nL);
        Edge edge3 = new Edge("3",as,start,0,ConstraintType.nL);
        Edge edge4 = new Edge("4",end,ae,0,ConstraintType.nL);


        List<Node>nodes = new ArrayList<>();
        nodes.add(start);
        nodes.add(as);
        nodes.add(ae);
        nodes.add(end);


        List<Edge> edgelist = new ArrayList<>();
        edgelist.add(edge1);
        edgelist.add(edge2);
        edgelist.add(edge3);
        edgelist.add(edge4);


        STNU stnu = new STNU(edgelist,nodes,10,true);
        DC_Checker_RUL rul = new DC_Checker_RUL(stnu,false);
        Result result = rul.check();
        result.printHistory();

        Resolver resolver = new Resolver(result);
        System.out.println(resolver.getCandidates());

        /**
         * Example for most restricitve constraint value:
         */

        BinarySearch bSearch = new BinarySearch(stnu,edge1);
        int mostRestrictiveValue = bSearch.getValue();
        System.out.println(mostRestrictiveValue);



    }
}
