var particle = function(dimensions, func) {
    this.initPosition = new Array(dimensions);
    this.currentPosition = new Array(dimensions);
    this.acceleration = new Array(dimensions);
    this.lastBestPosition = new Array(dimensions);
    this.func = func;
    this.omegaValue = omegaValue;

    for (var i = 0; i < this.currentPosition.length; i++) {
        this.currentPosition[i] = Math.random() * 2 - (2 / 2);
        this.acceleration[i] = Math.random() * 2 - (2 / 2);
        this.lastBestPosition[i] = this.currentPosition[i];
        this.initPosition[i] = this.currentPosition[i];
    }

    this.updateLastBestPosition = function() {
        var newPositionEvaluation = this.func(this.currentPosition);
        var lastBestPositionEvalutation = this.func(this.lastBestPosition);
        if (newPositionEvaluation < lastBestPositionEvalutation) {
            this.lastBestPosition = this.currentPosition;
            return true;
        }
        return false;
    };

    this.calculateAcceleration = function(omegaValue, fiParticles, fiSwarm, swarmBestPosition) {
        var randParticleFi = Math.random();
        var randSwarmFi = Math.random();
        for (var i = 0; i < this.acceleration.length; i++) {
            this.acceleration[i] = omegaValue * this.acceleration[i]
                    + fiParticles * randParticleFi
                    * (this.lastBestPosition[i] - this.currentPosition[i])
                    + fiSwarm * randSwarmFi
                    * (swarmBestPosition[i] - this.lastBestPosition[i]);
        }
    };
};

var PSO = function(iterations, particlesNumber, dimensions, func, fiSwarm, fiParticles, omegaValue) {
    var sw = new swarm(particlesNumber, dimensions, func, fiSwarm, fiParticles, omegaValue);
    sw.init();
    for (var i = 0; i < iterations; i++) {
        for (var j = 0; j < sw.particlesList.length; j++) {
            for (var k = 0; k < sw.particlesList[j].acceleration.length; k++) {
                sw.particlesList[j].calculateAcceleration(omegaValue, fiParticles, fiSwarm, sw.swarmBestPosition);
            }
            for (var l = 0; l < sw.particlesList[j].currentPosition.length; l++) {
                sw.particlesList[j].currentPosition[l] += sw.particlesList[j].acceleration[l];
            }
            for (var p = 0; p < sw.particlesList[j].currentPosition.length; p++) {
                if (sw.particlesList[j].updateLastBestPosition()) {

                    var particleLastBestPosition = func(sw.particlesList[j].lastBestPosition);
                    var swarmBestPosition = func(sw.swarmBestPosition);
                    if (particleLastBestPosition < swarmBestPosition) {
                        sw.swarmBestPosition = sw.particlesList[j].lastBestPosition;
                    }
                }
            }
        }
    }
    return sw.swarmBestPosition;
};

var swarm = function(particlesNumber, dimensions, func, fiSwarm, fiParticles, omegaValue) {
    this.particlesNumber = particlesNumber;
    this.func = func;
    this.dimensions = dimensions;
    this.fiSwarm = fiSwarm;
    this.fiParticles = fiParticles;
    this.omegaValue = omegaValue;
    this.particlesList = new Array(particlesNumber);
    this.swarmBestPosition = new Array(dimensions);

    this.init = function() {
        for (var i = 0; i < this.particlesNumber; i++) {
            this.particlesList[i] = new particle(this.dimensions, this.func);
            if (i === 0) {
                this.swarmBestPosition = this.particlesList[i].initPosition;
            }
        }
    };
};

var sin = function(x) {
    return Math.sin(x);
};

var rast = function(x) {
    var sum = 0;
    for (var i = 0; i < x.lenght; i++) {
        sum += (Math.pow(x[i], 2) - 10 * Math.cos(2 * 3.14 * x[i]));
    }
    return x.lenght * 10 + sum;
};

var square = function(x) {
    return Math.pow(x - 10, 2);
};

var func = rast;
var omegaValue = 0.1;
var fiParticles = 2;
var fiSwarm = 2;
var iterations = 2000;
var particlesNumber = 40;
var dimensions = 2;

for (var z = 0; z < 30; z++) {
    ps = PSO(iterations, particlesNumber, dimensions, func, fiSwarm, fiParticles, omegaValue);
    console.log(ps);
}
