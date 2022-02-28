package Algorithms;

import TCN.Edge;
import TCN.STNU;

import java.util.ArrayList;

public class Result {


    public ArrayList<Status> getHistory() {
        return history;
    }

    public STNU getDerivedSTNU() {
        return derivedSTNU;
    }

    public boolean isDC() {
        return isDC;
    }

    public int getRuns() {
        return runs;
    }

    ArrayList<Status> history;
     STNU derivedSTNU;
     boolean isDC;
     int runs;

    public long getTime() {
        return time;
    }

    long time;

     public Result(ArrayList<Status>history, STNU stnu,boolean isDC, int runs, long time){
         this.history = history;
         this.derivedSTNU = stnu;
         this.isDC = isDC;
         this.runs = runs;
         this.time = time;
     }

    public void printHistory(){
        for (Status h: history
        ) {
            Edge derived = h.getDerivedEdge();
            System.out.println(h.getRule()+": "+derived);
            System.out.println("DC: "+h.isDc() + "; HasDerived: "+h.hasDerived());
        }


    }


}
