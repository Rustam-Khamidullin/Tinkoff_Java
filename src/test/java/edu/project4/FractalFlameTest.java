package edu.project4;

import edu.project4.Renderer.ParallelRenderer;
import edu.project4.Renderer.SimpleRenderer;
import edu.project4.Transformations.HandkerchiefTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SinusoidalTransformation;
import edu.project4.Transformations.SpiralTransformation;
import edu.project4.Transformations.Transformation;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FractalFlameTest {
    @Test
    public void testSimpleRenderer() {
        var random = new Random();

        final int width = 1920;
        final int height = 1080;
        var image = FractalImage.create(width, height);

        var render = new SimpleRenderer();

        List<Transformation> transformations = new ArrayList<>();

        //transformations.add(new DiskTransformation(random.nextInt()));
        //transformations.add(new HeartTransformation(random.nextInt()));
        transformations.add(new SpiralTransformation(random.nextInt()));
        transformations.add(new SinusoidalTransformation(random.nextInt()));
        //transformations.add(new BubbleTransformation(random.nextInt()));
        transformations.add(new PolarTransformation(random.nextInt()));
        //transformations.add(new SphericalTransformation(random.nextInt()));
        transformations.add(new HandkerchiefTransformation(random.nextInt()));

        var start = System.nanoTime();
        render.render(image, transformations, 1000, (short) 30000, (short) 6, random.nextLong());
        var end = System.nanoTime();

        System.out.println(Duration.ofNanos(end - start).toMillis());

        ImageUtils.gammaCorrection(image, 0.7);

        try {
            ImageUtils.save(image, Path.of("simple.png"), ImageUtils.ImageFormat.PNG);
        } catch (IOException e) {
            System.err.println("Failed to write to file");
        }
    }

    @Test
    public void testParallelRenderer() {
        var random = new Random();

        final int width = 1920;
        final int height = 1080;
        var image = FractalImage.create(width, height);

        var render = new ParallelRenderer();

        List<Transformation> transformations = new ArrayList<>();

        //transformations.add(new DiskTransformation(random.nextInt()));
        //transformations.add(new HeartTransformation(random.nextInt()));
        transformations.add(new SpiralTransformation(random.nextInt()));
        transformations.add(new SinusoidalTransformation(random.nextInt()));
        //transformations.add(new BubbleTransformation(random.nextInt()));
        transformations.add(new PolarTransformation(random.nextInt()));
        //transformations.add(new SphericalTransformation(random.nextInt()));
        transformations.add(new HandkerchiefTransformation(random.nextInt()));

        var start = System.nanoTime();
        render.render(image, transformations, 1000, (short) 30000, (short) 6, random.nextLong());
        var end = System.nanoTime();

        System.out.println(Duration.ofNanos(end - start).toMillis());

        ImageUtils.gammaCorrection(image, 0.7);

        try {
            ImageUtils.save(image, Path.of("parallel.png"), ImageUtils.ImageFormat.PNG);
        } catch (IOException e) {
            System.err.println("Failed to write to file");
        }
    }
}
