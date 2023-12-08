package edu.project4.Renderer;

import edu.project4.FractalImage;
import edu.project4.Transformations.Transformation;
import java.util.List;

public interface Renderer {
    void render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        short symmetry,
        long seed
    );
}
