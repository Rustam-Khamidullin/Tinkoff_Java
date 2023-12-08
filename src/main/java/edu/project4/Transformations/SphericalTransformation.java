package edu.project4.Transformations;

import edu.project4.Point;

public class SphericalTransformation extends Transformation {
    public SphericalTransformation(long seed) {
        super(seed);
    }

    @Override
    @SuppressWarnings("MagicNumber")
    Point nonAffineTransformation(double y, double x) {
        double tmp = x * x + y * y;
        return new Point(y * 3 / tmp, x * 3 / tmp);
    }
}
