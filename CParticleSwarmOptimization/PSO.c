#include "PSO.h"
#include <stdio.h>
#include "swarm.h"

PsoParams* createPsoParams(int iterations,
        int particlesNum,
        int dim,
        double (*func)(double*, int),
        double fiSwarm,
        double fiParticles,
        double omegaVal,
        double psoMinPerVecElem,
        double psoMaxPerVecElem) {
    PsoParams* p = (PsoParams*) malloc(sizeof (PsoParams));

    if (p != NULL) {
        p->iterations = iterations;
        p->particlesNum = particlesNum;
        p->dim = dim;
        p->func = func;
        p->fiSwarm = fiSwarm;
        p->fiParticles = fiParticles;
        p->omegaVal = omegaVal;
        p->psoMinPerVecElem = psoMinPerVecElem;
        p->psoMaxPerVecElem = psoMaxPerVecElem;
    }
    return p;

}

void freePsoParams(PsoParams *p) {
    free(p);
}

double* PSO(PsoParams *p) {
    int i;
    double *resPointer;
    Swarm *s = createSwarm(p);
    for (i = 0; i < p->iterations; i++) {
        moveSwarm(s);
    }
    resPointer = s->swarmBestPosition;
    freeSwarm(s);
    return resPointer;
}

