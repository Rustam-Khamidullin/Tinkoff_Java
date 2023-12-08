package edu.project4.Transformations;

import edu.project4.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class HandkerchiefTransformation extends Transformation {
    public HandkerchiefTransformation(long seed) {
        super(seed);
    }

    @Override
    Point nonAffineTransformation(double y, double x) {
        var r = sqrt(x * x + y * y);
        var theta = atan2(y, x);
        return new Point(r * cos(theta - r), r * sin(theta + r));
    }
}
