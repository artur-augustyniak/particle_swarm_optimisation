package pl.aaugustyniak.pso;

import pl.aaugustyniak.pso.logic.FunctionInterface;

/**
 *
 * @author artur
 */
public class PsoParams {

    private Integer dimension;
    private Integer iterations;
    private Integer particlesNumber;
    private Double omegaValue;
    private Double fiParticle;
    private Double fiSwarm;
    private Double psoMaxPerVecElem;
    private Double psoMinPerVecElem;
    private FunctionInterface function;

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Integer getParticlesNumber() {
        return particlesNumber;
    }

    public void setParticlesNumber(Integer particlesNumber) {
        this.particlesNumber = particlesNumber;
    }

    public Double getOmegaValue() {
        return omegaValue;
    }

    public void setOmegaValue(Double omegaValue) {
        this.omegaValue = omegaValue;
    }

    public Double getFiParticle() {
        return fiParticle;
    }

    public void setFiParticle(Double fiParticle) {
        this.fiParticle = fiParticle;
    }

    public Double getFiSwarm() {
        return fiSwarm;
    }

    public void setFiSwarm(Double fiSwarm) {
        this.fiSwarm = fiSwarm;
    }

    public Double getPsoMaxPerVecElem() {
        return psoMaxPerVecElem;
    }

    public void setPsoMaxPerVecElem(Double psoMaxPerVecElem) {
        this.psoMaxPerVecElem = psoMaxPerVecElem;
    }

    public Double getPsoMinPerVecElem() {
        return psoMinPerVecElem;
    }

    public void setPsoMinPerVecElem(Double psoMinPerVecElem) {
        this.psoMinPerVecElem = psoMinPerVecElem;
    }

    public FunctionInterface getFunction() {
        return function;
    }

    public void setFunction(FunctionInterface function) {
        this.function = function;
    }

}
