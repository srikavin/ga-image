abstract class GeneticAlgorithm {

    final double goalFitness;
    final int max_iterations;
    final double pmut;
    Individual[] population;

    GeneticAlgorithm(Individual[] initialPopulation, double goalFitness, int max_iterations, double pmut) {

        this.population = initialPopulation;
        this.goalFitness = goalFitness;
        this.max_iterations = max_iterations;
        this.pmut = pmut;
    }

    abstract Individual[] select();

    abstract void run();
}