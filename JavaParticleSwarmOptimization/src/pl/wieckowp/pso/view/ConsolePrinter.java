package pl.wieckowp.pso.view;

import java.util.List;
import pl.aaugustyniak.pso.PsoParams;

/**
 *
 * @author wieckowp
 */
public class ConsolePrinter {

    public static void printResults(List<Double> resultPSO, PsoParams p) {
        System.out.println("Tested function: " + p.getFunction());
        for (int i = 0; i < resultPSO.size(); i++) {
            
            System.out.println("Coordinate no:" + i + " = " + resultPSO.get(i));
        }
        
        System.out.println("Function minima = " + p.getFunction().functionEvaluation(resultPSO));
    }
}
