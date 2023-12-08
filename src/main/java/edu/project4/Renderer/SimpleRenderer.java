package edu.project4.Renderer;

import edu.project4.FractalImage;
import edu.project4.Point;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.Random;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SimpleRenderer implements Renderer {
    private static final int SKIP_ITER = 20;

    @Override
    public void render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        short symmetry,
        long seed
    ) {
        var random = new Random(seed);
        final int width = canvas.width();
        final int height = canvas.height();

        final double minX = -1;
        final double maxX = 1;
        final double minY = (double) -width / height;
        final double maxY = (double) width / height;

        for (int sample = 0; sample < samples; sample++) {
            Point point = new Point(2 * random.nextDouble(maxY) - maxY, 2 * random.nextDouble(maxX) - maxX);

            for (int iter = -SKIP_ITER; iter < iterPerSample; iter++) {
                var transformation = variations.get(random.nextInt(variations.size()));

                point = transformation.apply(point);

                var tmpX = point.x();
                var tmpY = point.y();

                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {

                    var newX = tmpX * cos(theta2) + tmpY * sin(theta2);
                    var newY = -tmpX * sin(theta2) + tmpY * cos(theta2);

                    if (iter > 0 && newX > minX && newX < maxX && newY > minY && newY < maxY) {
                        int x = (int) (((maxX - newX) / (maxX - minX)) * height);
                        int y = (int) (((maxY - newY) / (maxY - minY)) * width);

                        transformation.updatePixel(canvas.pixel(y, x));
                    }
                }
            }
        }
    }
}
