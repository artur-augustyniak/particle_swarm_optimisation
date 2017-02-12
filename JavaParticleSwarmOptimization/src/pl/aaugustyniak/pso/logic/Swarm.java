package pl.aaugustyniak.pso.logic;

import java.util.ArrayList;
import java.util.List;
import pl.aaugustyniak.pso.PsoParams;

/**
 *
 * @author artur
 */
public class Swarm {

    private Double lastSwarmBestEval;
    private final PsoParams psoParams;
    private final List<Particle> particles;
    private List<Double> swarmBestPosition;

    public Swarm(PsoParams psoParams) {
        this.psoParams = psoParams;
        swarmBestPosition = new ArrayList<Double>(psoParams.getDimension());
        particles = new ArrayList<Particle>(psoParams.getParticlesNumber());
        for (int i = 0; i < psoParams.getParticlesNumber(); i++) {
            this.particles.add(new Particle(psoParams));
            if (i == 0) {
                this.swarmBestPosition = new ArrayList<Double>(particles.get(0).getCurrentPosition());
                //this.swarmBestPosition.set(i, particles.get(i).getCurrentPosition());
                // z Particle.java
                //this.currentPosition.set(i, randBeet(psoParams.getPsoMinPerVecElem(), psoParams.getPsoMaxPerVecElem()));
            }
        }
        this.lastSwarmBestEval = psoParams.getFunction().functionEvaluation(swarmBestPosition);

    }

    public void moveSwarm() {
        for (int i = 0; i < psoParams.getParticlesNumber(); i++) {
            particles.get(i).moveParticle(this.getSwarmBestPosition());
            if ((particles.get(i).checkParticleLastBestPos()) == 1) {
                checkSwarmLastBestPos(particles.get(i));
            }
        }
    }

    private void checkSwarmLastBestPos(Particle p) {
        if (p.getlastParticleBestEval() < this.getLastSwarmBestEval()) {
            this.lastSwarmBestEval = p.getlastParticleBestEval();
            this.swarmBestPosition = new ArrayList<Double>(p.getLastBestPosition());
        }
    }

    public Double getLastSwarmBestEval() {
        return lastSwarmBestEval;
    }

    public List<Double> getSwarmBestPosition() {
        return swarmBestPosition;
    }
}
