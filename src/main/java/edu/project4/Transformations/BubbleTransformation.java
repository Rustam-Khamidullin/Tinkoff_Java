package edu.project4.Transformations;

import edu.project4.Point;

public class BubbleTransformation extends Transformation {
    public BubbleTransformation(long seed) {
        super(seed);
    }

    @Override
    @SuppressWarnings("MagicNumber")
    Point nonAffineTransformation(double y, double x) {
        var r = 4 + x * x + y * y;
        return new Point((4.0 * y) / r, (4.0 * x) / r);
    }
}
