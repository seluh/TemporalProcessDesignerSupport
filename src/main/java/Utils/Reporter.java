package Utils;

import Algorithms.Result;

public class Reporter {

    public static void report(Result r){
        System.out.println("DC: "+r.isDC());
        System.out.println("Runs: "+ r.getRuns());
        System.out.println("Time elapsed: "+r.getTime());
        System.out.println("final STNU: ");
        r.getDerivedSTNU().getMatrix().printMatrix();
        r.printHistory();
    }

}
