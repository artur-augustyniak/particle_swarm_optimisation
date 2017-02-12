/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.wieckowp.pso.functions;

import java.util.List;
import pl.aaugustyniak.pso.logic.FunctionInterface;

/**
 *
 * @author opti
 */
public class SchafferF6 implements FunctionInterface {

    @Override
    public Double functionEvaluation(List<Double> args) {
        return 0.5 + (Math.pow(Math.sin(Math.sqrt(args.get(0) * args.get(0) + args.get(1) * args.get(1))), 2) - 0.5)
                / Math.pow((1 + 0.001 * (args.get(0) * args.get(0) + args.get(1) * args.get(1))), 2);
    }
}
