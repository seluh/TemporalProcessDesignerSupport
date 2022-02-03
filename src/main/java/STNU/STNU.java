package STNU;

import java.util.ArrayList;
import java.util.List;

public class STNU {
    private List <Edge> edges;
    private List <Node> nodes;
    private List <ContingentLink>  contingentLinks;
    private List <Node> contingentTimepoints;

    public STNU(List<Edge> edges, List<Node> nodes, List<ContingentLink> contingentLinks, List<Node> contingentTimepoints) {
        this.edges = edges;
        this.nodes = nodes;
        this.contingentLinks = contingentLinks;
        this.contingentTimepoints = contingentTimepoints;
    }



}
