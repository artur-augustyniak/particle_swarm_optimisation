package pl.aaugustyniak.pso.logic;

import java.util.List;
import pl.aaugustyniak.pso.PsoParams;

/**
 *
 * @author artur
 */
public class Pso {

    private final Swarm swarm;
    private final PsoParams params;

    public Pso(PsoParams p) {
        this.params = p;
        this.swarm = new Swarm(p);
    }

    public List<Double> optimize() {
        for (int i = 0; i < params.getIterations(); i++) {
            swarm.moveSwarm();
        }
        return swarm.getSwarmBestPosition();
    }
}
