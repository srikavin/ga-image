public abstract class Individual {
    abstract double fitness();

    abstract Individual recombine(Individual b);

    abstract void mutate(double pmut);
}
