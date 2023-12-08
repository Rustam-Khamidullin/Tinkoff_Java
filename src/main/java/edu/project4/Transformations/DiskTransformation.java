package edu.project4.Transformations;

import edu.project4.Point;

public class DiskTransformation extends Transformation {
    public DiskTransformation(long seed) {
        super(seed);
    }

    @Override
    Point nonAffineTransformation(double y, double x) {
        var r = Math.sqrt(x * x + y * y) * Math.PI;
        var theta = Math.atan2(y, x) / Math.PI;
        return new Point(theta * Math.cos(r), theta * Math.sin(r));
    }
}
