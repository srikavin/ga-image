public abstract class Individual<T extends Individual> {
    abstract double fitness();

    abstract T recombine(T b);

    abstract void mutate(double pmut);
}
