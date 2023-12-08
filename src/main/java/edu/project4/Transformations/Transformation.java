package edu.project4.Transformations;

import edu.project4.FractalImage.Pixel;
import edu.project4.Point;
import java.util.Random;

public abstract class Transformation {
    protected Coefficients coefficients;

    public Transformation(long seed) {
        this.coefficients = getRandomCoefficients(seed);
    }

    @SuppressWarnings({"MagicNumber", "LocalVariableName"})
    public static Coefficients getRandomCoefficients(long seed) {
        var random = new Random(seed);
        double a;
        double b;
        double c = random.nextDouble(2) - 1;
        double d;
        double e;
        double f = random.nextDouble(2) - 1;
        int R = random.nextInt(256);
        int G = random.nextInt(256);
        int B = random.nextInt(256);

        while (true) {
            a = random.nextDouble(2) - 1;
            d = random.nextDouble(2) - 1;

            if (a * a + d * d > 1) {
                continue;
            }

            b = random.nextDouble(2) - 1;
            e = random.nextDouble(2) - 1;

            if (b * b + e * e > 1) {
                continue;
            }
            if (a * a + b * b + d * d + e * e > 1 + (a * e - b * d) * (a * e - b * d)) {
                continue;
            }

            return new Coefficients(a, b, c, d, e, f, R, G, B);
        }
    }

    abstract Point nonAffineTransformation(double y, double x);

    public Point apply(Point point) {
        var x = point.x();
        var y = point.y();

        var newX = x * coefficients.a() + y * coefficients.b() + coefficients.c();
        var newY = x * coefficients.d() + y * coefficients.e() + coefficients.f();

        return nonAffineTransformation(newY, newX);
    }

    public void updatePixel(Pixel pixel) {
        if (pixel.getHitCount() == 0) {
            pixel.setNewValue(coefficients.R(), coefficients.G(), coefficients.B(), 1);
        } else {
            pixel.mixColor(coefficients.R(), coefficients.G(), coefficients.B());
            pixel.hit();
        }
    }

    @SuppressWarnings("RecordComponentName")
    public record Coefficients(double a, double b, double c, double d, double e, double f, int R, int G, int B) {
    }
}
