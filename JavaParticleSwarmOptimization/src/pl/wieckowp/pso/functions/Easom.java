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
public class Easom implements FunctionInterface {

    @Override
    public Double functionEvaluation(List<Double> args) {
        return -Math.cos(args.get(0)) * Math.cos(args.get(1))
                * Math.pow(Math.E, -Math.pow(args.get(0) - Math.PI, 2) - Math.pow(args.get(1) - Math.PI, 2));
    }
}
