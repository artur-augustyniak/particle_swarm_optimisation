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
public class Bohachevsky1 implements FunctionInterface {

    @Override
    public Double functionEvaluation(List<Double> args) {
        return Math.pow(args.get(0), 2) + 2 * Math.pow(args.get(1), 2)
                - 0.3 * Math.cos(3 * Math.PI * args.get(0)) - 0.4 * Math.cos(4 * Math.PI * args.get(1)) + 0.7;
    }
}
