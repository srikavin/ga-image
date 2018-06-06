abstract class GeneticAlgorithm {

    Individual[] population;
    final double goalFitness;
    final int max_iterations;
    final double pmut;

    GeneticAlgorithm(Individual[] initialPopulation, double goalFitness, int max_iterations, double pmut) {

        this.population = initialPopulation;
        this.goalFitness = goalFitness;
        this.max_iterations = max_iterations;
        this.pmut = pmut;
    }

    abstract void select(int n);
}