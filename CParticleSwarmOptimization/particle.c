#include "particle.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

double randBetween(double min, double max) {
    double random = ((double) rand()) / (double) RAND_MAX;
    random = min + (random * (max - min));
    return random;
}

Particle* createParticle(PsoParams *p) {

    Particle* psoParticle = (Particle*) malloc(sizeof (Particle));
    if (psoParticle != NULL) {
        double negDiff = -1 * (p->psoMaxPerVecElem - p->psoMinPerVecElem);
        double posDiff = fabs(negDiff);
        srand((unsigned) time(NULL));
        psoParticle->psoParams = p;
        psoParticle->currentPosition = (double*) malloc(sizeof (double) * p->dim);
        psoParticle->acceleration = (double*) malloc(sizeof (double) * p->dim);
        psoParticle->lastBestPosition = (double*) malloc(sizeof (double) * p->dim);
        int i;
        for (i = 0; i < p->dim; i++) {
            psoParticle->currentPosition[i] = randBetween(p->psoMinPerVecElem, p->psoMaxPerVecElem);
            psoParticle->acceleration[i] = randBetween(negDiff, posDiff);
            psoParticle->lastBestPosition[i] = psoParticle->currentPosition[i];
        }
        psoParticle->lastBestEval = p->func(psoParticle->currentPosition, p->dim);
    }
    return psoParticle;
}

void moveParticle(Particle *p, double* swarmLastBestPos) {

    int i;
    for (i = 0; i < p->psoParams->dim; i++) {
        p->acceleration[i] = p->psoParams->omegaVal * p->acceleration[i]
                + p->psoParams->fiParticles * randBetween(0.0, 1.0)
                * (p->lastBestPosition[i] - p->currentPosition[i])
                + p->psoParams->fiSwarm * randBetween(0.0, 1.0)
                + (swarmLastBestPos[i] - p->lastBestPosition[i]);
        p->currentPosition[i] += p->acceleration[i];
    }
}

int checkParticleLastBestPos(Particle *p) {

    int retVal = 0;
    double newPosEval = p->psoParams->func(p->currentPosition, p->psoParams->dim);

    if (newPosEval < p->lastBestEval) {
        p->lastBestEval = newPosEval;
        memmove(p->lastBestPosition, p->currentPosition, p->psoParams->dim * sizeof (double));
        retVal = 1;
    }
    return retVal;
}

void freeParticle(Particle *p) {
    free(p->acceleration);
    free(p->lastBestPosition);
    free(p->currentPosition);
    free(p);
}

