/* 
 * File:   main.c
 * Author: artur
 *
 * Created on 10 kwiecień 2013, 19:12
 */

#include <stdio.h>
#include <stdlib.h>
#include "PSO.h"
#include "function.h"

int main(int argc, char** argv) {

    int partNum = 100;
    int iters = 2000;
    int dim = 3;
    double (*functionPointer)(double*, int) = rastrigin;
    double fiSwarm = 0.01;
    double fiParticle = 1.9;
    double omegaVal = 0.7;
    double psoMinPerVecElem = -5.12;
    double psoMaxPerVecElem = 5.12;

    PsoParams *p = createPsoParams(
            iters,
            partNum,
            dim,
            functionPointer,
            fiSwarm,
            fiParticle,
            omegaVal,
            psoMinPerVecElem,
            psoMaxPerVecElem);

    double *v = PSO(p);
    int i;
    for (i = 0; i < dim; i++) {
        printf("Coordinate %d = %f \n", i, v[i]);
    }
    printf("Function minima: =  %f \n", functionPointer(v, dim));
    freePsoParams(p);
    return (EXIT_SUCCESS);
}

