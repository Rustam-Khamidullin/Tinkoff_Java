package edu.project4.Renderer;

import edu.project4.FractalImage;
import edu.project4.Point;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ParallelRenderer implements Renderer {
    private static final int THREADS = 6;
    private static final int SKIP_ITER = 20;

    private static void treadRender(
        FractalImage canvas,
        List<Transformation> variations,
        short iterPerSample,
        short symmetry
    ) {
        final int width = canvas.width();
        final int height = canvas.height();

        final double minX = -1;
        final double maxX = 1;
        final double minY = (double) -width / height;
        final double maxY = (double) width / height;


        Point point = new Point(
            2 * ThreadLocalRandom.current().nextDouble(maxY) - maxY,
            2 * ThreadLocalRandom.current().nextDouble(maxX) - maxX
        );

        for (int iter = -SKIP_ITER; iter < iterPerSample; iter++) {
            var transformation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));

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

                    synchronized (canvas.pixel(y, x)) {
                        transformation.updatePixel(canvas.pixel(y, x));
                    }
                }
            }
        }
    }

    @Override
    public void render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        short symmetry,
        long seed
    ) {

        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {
            for (int i = 0; i < samples; i++) {
                executorService.execute(() -> treadRender(
                    canvas,
                    variations,
                    iterPerSample,
                    symmetry
                ));
            }

            executorService.shutdown();

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
