/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.wieckowp.pso.functions;

import java.util.List;
import pl.aaugustyniak.pso.logic.FunctionInterface;

/**
 *
 * @author wieckowp
 */
public class SchafferF7 implements FunctionInterface {

    @Override
    public Double functionEvaluation(List<Double> args) {
        Double x1x2_2 = args.get(0) * args.get(0) + args.get(1) * args.get(1);
        return Math.pow(x1x2_2, 0.25) * (Math.pow(Math.sin(50 * Math.pow(x1x2_2, 0.1)), 2) + 1);
    }
}
