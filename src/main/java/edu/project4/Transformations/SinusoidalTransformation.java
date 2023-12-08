package edu.project4.Transformations;

import edu.project4.Point;

public class SinusoidalTransformation extends Transformation {
    public SinusoidalTransformation(long seed) {
        super(seed);
    }

    @Override
    @SuppressWarnings("MagicNumber")
    Point nonAffineTransformation(double y, double x) {
        return new Point(3 * Math.sin(y), 3 * Math.sin(x));
    }
}
