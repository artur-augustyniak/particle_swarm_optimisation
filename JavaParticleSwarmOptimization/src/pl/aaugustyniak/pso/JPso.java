package pl.aaugustyniak.pso;

import java.util.List;
import pl.aaugustyniak.pso.logic.FunctionInterface;
import pl.aaugustyniak.pso.logic.Pso;
import pl.wieckowp.pso.functions.Parabol;
import pl.wieckowp.pso.view.ConsolePrinter;

/**
 *
 * @author artur
 */
public class JPso {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        PsoParams params = new PsoParams();
        params.setParticlesNumber(100);
        params.setFiParticle(1.9);
        params.setFiSwarm(0.01);
        params.setOmegaValue(0.7);
        params.setPsoMaxPerVecElem(5.12);
        params.setPsoMinPerVecElem(-5.12);
        params.setIterations(2000);
        params.setDimension(1);
        FunctionInterface func = new Parabol();
        params.setFunction(func);

        Pso pso = new Pso(params);
        List<Double> resultVector = pso.optimize();
        ConsolePrinter.printResults(resultVector, params);
    }

}
