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
public class Rastrigin implements FunctionInterface {

    @Override
    public Double functionEvaluation(List<Double> args) {
        Double sum = 0.0;
        for (int i = 0; i < args.size(); i++) {
            sum += args.get(i) * args.get(i) - Math.cos(10 * Math.PI * args.get(i));
        }
        return sum;
    }
}
