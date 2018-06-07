package pro.sharath.ga.image;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ImageAlgorithm extends GeneticAlgorithm {
    List<Individual> best = new CopyOnWriteArrayList<>();

    ImageAlgorithm(Individual[] initialPopulation, double goalFitness, int max_iterations, double pmut) {
        super(initialPopulation, goalFitness, max_iterations, pmut);
    }

    public List<Individual> getBest() {
        return best;
    }

    Individual[] select() {
        Individual[] individuals = new Individual[population.length];
        System.arraycopy(population, 0, individuals, 0, population.length);

        Map<Individual, Double> fitnessValues = new HashMap<>();
        for (Individual e : individuals) {
            fitnessValues.put(e, e.fitness());
        }

        Arrays.sort(individuals, Comparator.comparingDouble(fitnessValues::get));

        double rand = Math.random();

        double cumulative = 0;
        for (int i = 1; i < individuals.length; i++) {
            cumulative += fitnessValues.get(individuals[i]);
            if (cumulative > rand) {
                return new Individual[]{individuals[i], individuals[i - 1]};
            }
        }
        return null;
    }

    void run() {
        // set initial values for the best members of this population
        double bestFitness = this.population[0].fitness();
        Individual bestIndividual = this.population[0];
        best.add(bestIndividual);

        // keeping track of iterations
        int iterations = 0;

        while (bestFitness < this.goalFitness && iterations < this.max_iterations) {
            Individual[] newPopulation = new Individual[population.length];
            for (int i = 0; i < population.length; i++) {
                Individual[] parents = select();
                @SuppressWarnings("unchecked")
                Individual child = parents[0].recombine(parents[1]);
                double fitness = child.fitness();
                if (bestFitness < fitness) {
                    best.add(child);
                    System.out.print("iterations = " + iterations);
                    System.out.println(" fitness = " + fitness);
                    bestFitness = fitness;
                }
                child.mutate(pmut);
                newPopulation[i] = child;
            }
            population = newPopulation;
            iterations++;
        }

    }
}
