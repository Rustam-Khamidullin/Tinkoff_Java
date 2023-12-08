package edu.project4;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        var result = new Pixel[width * height];

        for (int i = 0; i < result.length; i++) {
            result[i] = new Pixel(0, 0, 0, 0);
        }

        return new FractalImage(result, width, height);
    }

    public Pixel pixel(int y, int x) {
        return data[width * x + y];
    }

    public static class Pixel {
        private int r;
        private int g;
        private int b;
        private int hitCount;

        public Pixel(int r, int g, int b, int hitCount) {
            setNewValue(r, g, b, hitCount);
        }

        public void setNewValue(int r, int g, int b, int hitCount) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.hitCount = hitCount;
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        public int getHitCount() {
            return hitCount;
        }

        public void mixColor(int r, int g, int b) {
            this.r = (this.r + r) / 2;
            this.g = (this.g + g) / 2;
            this.b = (this.b + b) / 2;
        }

        public void hit() {
            hitCount++;
        }
    }
}
