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
public class Parabol implements FunctionInterface {

    @Override
    public Double functionEvaluation(List<Double> args) {
        Double result = 0.0;
        for (int i = 0; i < args.size(); i++) {
            result += args.get(i) * args.get(i);
        }
        return result;
    }
}