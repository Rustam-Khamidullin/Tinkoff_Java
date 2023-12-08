package edu.project4.Transformations;

import edu.project4.Point;

public class PolarTransformation extends Transformation {
    public PolarTransformation(long seed) {
        super(seed);
    }

    @Override
    @SuppressWarnings("MagicNumber")
    Point nonAffineTransformation(double y, double x) {
        return new Point(3 * (Math.sqrt(x * x + y * y) - 1), 3 * Math.atan2(y, x) / Math.PI);
    }
}
