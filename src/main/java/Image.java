public class Image extends Individual {

    private int[][][] original;
    private int[][][] data;

    /**
     * Loads this image from file into both original and data
     *
     * @param filename the file to load from
     */
    public Image(String filename) {

    }

    /**
     * copies the original and data fields from image
     *
     * @param image the image object to copy
     * @return a 3-D array containing rgb values of image from file
     */
    public Image(Image image) {
        this.original = image.original;
        this.data = image.data;
    }


    /**
     * total = x * y * z
     * 1. - (sum(sum(sum(abs(original - individual) / 255.))) / total)
     * https://cdn.discordapp.com/attachments/235125185492156416/454044007648198677/Screen_Shot_2018-06-06_at_6.08.30_PM.png
     *
     * @return the L1 distance between original and data normalized to be between 0 and 1
     */
    double fitness() {
        return -1;
    }

    /**
     * 1. Generate a random number between 0 and the width of the image.
     * 2. Take pixel to the left of that number from Individual a, and every pixel to the right from Individual b
     * 3. return that
     *
     * @param b Individual b
     * @return combined individual
     */
    Individual recombine(Individual b) {
        return null;
    }

    /**
     * 1. flip pmut-weighted coin. if tails return, else
     * 2. pick 100 random pixels and change their values to a random int between 0 and 255 inclusive
     *
     * @param pmut probability of mutation
     */
    void mutate(double pmut) {

    }
}
