import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int MAX_ITERATIONS = 1_000_000;
    private static Path IMAGE_PATH = Paths.get("./best/");

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("First argument must be input file name!");
            System.out.println("Second argument can be best output file directory!");
            return;
        }

        if (args.length == 2) {
            IMAGE_PATH = Paths.get(args[1]);
        }

        Path darwinPath = Paths.get(args[0]);
        Image darwin = new Image(darwinPath);

        Individual[] individuals = getInitial(15, darwin);

        ImageAlgorithm imageAlgorithm = new ImageAlgorithm(individuals, .95, MAX_ITERATIONS, 0.25);

        List<Individual> best = imageAlgorithm.getBest();

        if (!Files.isDirectory(IMAGE_PATH)) {
            Files.createDirectory(IMAGE_PATH);
        }

        AtomicInteger last = new AtomicInteger();
        Thread thread = new Thread(() -> {
            while (true) {
                if (best.size() - 1 > last.get()) {
                    for (int i = last.get(); i < best.size(); i++) {
                        Path path = IMAGE_PATH.resolve(i + ".png");
                        ((Image) best.get(i)).save(path);
                        last.set(i);
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        imageAlgorithm.run();

    }

    static Individual[] getInitial(int n, Image image) {
        Pixel[][] pixels = new Pixel[image.data.length][image.data[0].length];
        Pixel blank = new Pixel(0, 0, 0);
        for (Pixel[] e : pixels) {
            for (int i = 0; i < e.length; i++) {
                e[i] = blank;
            }
        }

        Image[] individuals = new Image[n];
        for (int i = 0; i < n; i++) {
            individuals[i] = new Image(image);
            System.arraycopy(pixels, 0, individuals[i].data, 0, pixels.length);
        }

        return individuals;
    }
}
