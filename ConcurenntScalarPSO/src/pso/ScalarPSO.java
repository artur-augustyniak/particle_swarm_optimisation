package pso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ScalarPSO {

    private static final int DIM = 2 - 1;

    private static final int PARTICLES_NUMBER = 90;
    private static final int ITERATIONS = 100000;

    private static final double OMEGA_VALUE = -0.6;
    private static final double FI_PARTICLE = 0.6;
    private static final double FI_SWARM = 2.2;

    private static final Random RAND = new Random(42);
    private static final double[] lastSwarmBestPos = new double[DIM];

    private static double fit(double[] p) {
        double ret = 0.0;
        for (double d : p) {
            ret += 3 * Math.pow(d - 124, 2) + 4;
        }
        return ret;
    }

    public static class P {

        private final int id;
        public double[] lastPos = new double[DIM];
        public double[] lastBestPos = new double[DIM];
        public double lastBestFit;
        public double[] velocity = new double[DIM];

        public P(int id) {
            this.id = id;
            for (int i = 0; i < DIM; i++) {
                double coord = RAND.nextDouble();
                lastPos[i] = coord;
                lastBestPos[i] = coord;
                velocity[i] = RAND.nextDouble();
            }
            lastBestFit = fit(lastPos);
        }

        public void move() {
            double currFit = fit(lastPos);
            if (currFit < lastBestFit) {
                lastBestFit = currFit;
                for (int i = 0; i < DIM; i++) {
                    lastBestPos[i] = lastPos[i];
                    lastSwarmBestPos[i] = lastPos[i];
                }
            }
            for (int i = 0; i < DIM; i++) {
                velocity[i] = OMEGA_VALUE * velocity[i]
                        + FI_PARTICLE * RAND.nextDouble()
                        * (lastBestPos[i] - lastPos[i])
                        + FI_SWARM * RAND.nextDouble()
                        + (lastSwarmBestPos[i] - lastPos[i]);
                lastPos[i] += velocity[i];
            }

        }

    }

    public static void main(String[] args) {

        List<P> swarm = new ArrayList<>(PARTICLES_NUMBER);
        for (int i = 0; i < PARTICLES_NUMBER; i++) {
            swarm.add(new P(i));
        }
        // kolejnosc ma wplyw na wynik
        //long seed = System.nanoTime();
        //Collections.shuffle(swarm, new Random(seed));
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < ITERATIONS; j++) {
            for (int k = 0; k < PARTICLES_NUMBER; k++) {
                swarm.get(k).move();
            }
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);

        //124
        System.out.println("Minima at params");
        for (int i = 0; i < DIM; i++) {
            System.out.printf("%d - %.0f\n", i, lastSwarmBestPos[i]);
        }

    }
}
