import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

public class Image extends Individual<Image> {
    Pixel[][] data;
    private Pixel[][] original;
    private Random random = new Random();

    /**
     * Loads this image from file into both original and data
     *
     * @param file the file to load from
     */
    public Image(Path file) {
        try {
            BufferedImage image = ImageIO.read(file.toFile());
            original = new Pixel[image.getWidth()][image.getHeight()];
            data = new Pixel[image.getWidth()][image.getHeight()];
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int rgb = image.getRGB(i, j);
                    original[i][j] = new Pixel(rgb);
                    data[i][j] = new Pixel(rgb);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * copies the original and data fields from image
     *
     * @param image the image object to copy
     */
    public Image(Image image) {
        this.original = image.original;
        this.data = image.data;
    }

    public void save(Path path) {
        try {
            BufferedImage image = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    image.setRGB(i, j, data[i][j].rgb);
                }
            }
            ImageIO.write(image, "png", path.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * total = x * y * z
     * 1. - (sum(sum(sum(abs(original - individual) / 255.))) / total)
     * https://cdn.discordapp.com/attachments/235125185492156416/454044007648198677/Screen_Shot_2018-06-06_at_6.08.30_PM.png
     *
     * @return the L1 distance between original and data normalized to be between 0 and 1
     */
    double fitness() {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                Pixel e = data[i][j].subtract(original[i][j]);
                sum += e.red / 255.0;
                sum += e.green / 255.0;
                sum += e.blue / 255.0;
            }
        }
        return 1 - sum / (3 * data[0].length * data.length);
    }

    /**
     * 1. Generate a random number between 0 and the width of the image.
     * 2. Take pixel to the left of that number from Individual a, and every pixel to the right from Individual b
     * 3. return that
     *
     * @param b Individual b
     *
     * @return combined individual
     */
    Image recombine(Image b) {
        int rand = random.nextInt(data.length);
        Image ret = new Image(this);
        for (int i = 0; i < data.length; i++) {
            if (data[i].length - rand >= 0) {
                System.arraycopy(b.data[i], rand, ret.data[i], rand, data[i].length - rand);
            }
        }
        return ret;
    }

    /**
     * 1. flip pmut-weighted coin. if tails return, else
     * 2. pick 100 random pixels and change their values to a random int between 0 and 255 inclusive
     *
     * @param pmut probability of mutation
     */
    void mutate(double pmut) {
        if (random.nextDouble() >= pmut) {
            return;
        }

        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(data.length);
            int y = random.nextInt(data[x].length);
            data[x][y] = new Pixel(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }
    }
}
