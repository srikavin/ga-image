import java.util.ArrayList;

public class ImageAlgorithm extends GeneticAlgorithm {

    ImageAlgorithm(Individual[] initialPopulation, double goalFitness, int max_iterations, double pmut) {
        super(initialPopulation, goalFitness, max_iterations, pmut);
    }

    /**
     * @param n
     */
    void select(int n) {

    }

    void run() {
        // set initial values for the best members of this population
        double bestFitness = this.population[0].fitness();
        Individual bestIndividual = this.population[0];
        ArrayList<Individual> trumps = new ArrayList<Individual>();
        trumps.add(bestIndividual);

        // keeping track of iterations
        int iterations = 0;

        while (bestFitness < this.goalFitness && iterations < this.max_iterations) {

            iterations++;
        }

    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {

            }
        });
    }
}
