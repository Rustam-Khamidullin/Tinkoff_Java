package edu.project4.Transformations;

import edu.project4.Point;

public class HeartTransformation extends Transformation {
    public HeartTransformation(long seed) {
        super(seed);
    }

    @Override
    Point nonAffineTransformation(double y, double x) {
        var r = Math.sqrt(x * x + y * y);
        var theta = Math.atan2(y, x);
        return new Point(-r * Math.cos(theta * r), r * Math.sin(theta * r));
    }
}
