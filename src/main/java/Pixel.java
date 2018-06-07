public class Pixel {
    int red;
    int blue;
    int green;
    int rgb;

    public Pixel(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
        rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
    }

    public Pixel(int rgb) {
        red = (rgb >> 16) & 0xFF;
        green = (rgb >> 8) & 0xFF;
        blue = rgb & 0xFF;
        this.rgb = rgb;
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    public Pixel subtract(Pixel other) {
        int nRed = Math.abs(red - other.red);
        int nBlue = Math.abs(blue - other.blue);
        int nGreen = Math.abs(green - other.green);
        return new Pixel(nRed, nBlue, nGreen);
    }
}
