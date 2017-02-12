package pl.aaugustyniak.pso.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.aaugustyniak.pso.PsoParams;

/**
 *
 * @author artur
 */
public class Particle {

    private final Random random;
    private Double lastParticleBestEval;
    private final PsoParams psoParams;
    private final List<Double> currentPosition;
    private final List<Double> acceleration;
    private List<Double> lastBestPosition;

    public Particle(PsoParams psoParams) {
        this.random = new Random();
        this.psoParams = psoParams;

        double accelInitDiffAbs = Math.abs(psoParams.getPsoMaxPerVecElem() - psoParams.getPsoMinPerVecElem());
        double negDiff = -1.0 * accelInitDiffAbs;
        double posDiff = accelInitDiffAbs;
        currentPosition = new ArrayList<Double>(psoParams.getDimension());
        acceleration = new ArrayList<Double>(psoParams.getDimension());
        //lastBestPosition = new ArrayList<Double>(psoParams.getDimension());

        for (int i = 0; i < psoParams.getDimension(); i++) {
            this.currentPosition.add(randBeet(psoParams.getPsoMinPerVecElem(), psoParams.getPsoMaxPerVecElem()));
            this.acceleration.add(randBeet(negDiff, posDiff));
        }
        lastBestPosition = new ArrayList<Double>(this.currentPosition);
        this.lastParticleBestEval = psoParams.getFunction().functionEvaluation(currentPosition);
    }

    public Double getlastParticleBestEval() {
        return lastParticleBestEval;
    }

    public List<Double> getCurrentPosition() {
        return currentPosition;
    }

    private Double randBeet(Double min, Double max) {
        return min + random.nextDouble() * (max - min);
    }

    public void moveParticle(List<Double> swarmLastBestPos) {
        for (int i = 0; i < psoParams.getDimension(); i++) {
            Double accel;
            accel = this.psoParams.getOmegaValue() * this.acceleration.get(i)
                    + this.psoParams.getFiParticle() * randBeet(0.0, 1.0)
                    * (this.lastBestPosition.get(i) - this.currentPosition.get(i))
                    + this.psoParams.getFiSwarm() * randBeet(0.0, 1.0)
                    + (swarmLastBestPos.get(i) - this.currentPosition.get(i));

            this.acceleration.set(i, accel);
            this.currentPosition.set(i, this.currentPosition.get(i) + this.acceleration.get(i));
        }
    }

    public Integer checkParticleLastBestPos() {
        Integer retVal = 0;
        Double newPosEval = this.psoParams.getFunction().functionEvaluation(currentPosition);

        if (newPosEval < this.lastParticleBestEval) {
            this.lastParticleBestEval = newPosEval;
            this.lastBestPosition = new ArrayList<Double>(this.currentPosition);
            retVal = 1;
        }
        return retVal;
    }

    public List<Double> getLastBestPosition() {
        return lastBestPosition;
    }
}
