package pso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;





public class ConcurrentScalarPSO {

    private static final int DIM = 2 - 1;
    private static final int PARTICLES_NUMBER = 90;
    private static final int ITERATIONS = 100000;
    private static final double OMEGA_VALUE = -0.6;
    private static final double FI_PARTICLE = 0.6;
    private static final double FI_SWARM = 2.2;
    private static final List<Callable<Object>> SWARM = new ArrayList<>(PARTICLES_NUMBER);
    private static final Random RAND = new Random(42);
    /**
     * CPU bound - #cpu+1 threads
     */
    private static final ExecutorService EXECS_ERVICE = Executors.newFixedThreadPool(5);

    private static final double[] lastSwarmBestPos = new double[DIM];

    private static double fit(double[] p) {
        double ret = 0.0;
        for (double d : p) {
            ret += 3 * Math.pow(d - 124, 2) + 4;
        }
        return ret;
    }

    public static void setSbest(int i, double val) {
        synchronized (lastSwarmBestPos) {
            lastSwarmBestPos[i] = val;
        }
    }

    public static double getSbest(int i) {
        synchronized (lastSwarmBestPos) {
            return lastSwarmBestPos[i];
        }
    }

  

    public static class P implements Callable<Object>{

        private final int id;
        private final double[] lastPos = new double[DIM];
        private final double[] lastBestPos = new double[DIM];
        private double lastBestFit;
        private final double[] velocity = new double[DIM];

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

        @Override
        public Object call() throws Exception {
               double currFit = fit(lastPos);
            if (currFit < lastBestFit) {
                lastBestFit = currFit;
                for (int i = 0; i < DIM; i++) {
                    lastBestPos[i] = lastPos[i];
                    setSbest(i, lastPos[i]);
                }
            }
            for (int i = 0; i < DIM; i++) {
                velocity[i] = OMEGA_VALUE * velocity[i]
                        + FI_PARTICLE * RAND.nextDouble()
                        * (lastBestPos[i] - lastPos[i])
                        + FI_SWARM * RAND.nextDouble()
                        + (getSbest(i) - lastPos[i]); //sync
                lastPos[i] += velocity[i];
            }
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < PARTICLES_NUMBER; i++) {
            SWARM.add(new P(i));
        }
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < ITERATIONS; j++) {            
            EXECS_ERVICE.invokeAll(SWARM);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        EXECS_ERVICE.shutdown();
        //124
        System.out.println("CC Minima at params");
        for (int i = 0; i < DIM; i++) {
            System.out.printf("%d - %.0f\n", i, lastSwarmBestPos[i]);
        }

    }
}
