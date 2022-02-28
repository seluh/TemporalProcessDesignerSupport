package TCN;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class STNUIterator implements Iterator {

    private List<STNUPath> iterator;
    int current;



    public STNUIterator(STNU stnu){

        iterator = new ArrayList<>();
        current = 0;
        for (Edge e1: stnu.getEdges()
        ) {
            Node inter = e1.getTarget();
            for (Edge e2: stnu.getEdges()
            ) {
                if(inter.equals(e2.getSource())){
                    iterator.add(new STNUPath(e1,e2));
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return current < iterator.size();
    }

    @Override
    public STNUPath next() {
       STNUPath ret = iterator.get(current);
       current++;
       return ret;
    }

    @Override
    public void remove() {

    }


}
