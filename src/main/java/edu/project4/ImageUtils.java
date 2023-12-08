package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import static java.lang.Math.pow;

public final class ImageUtils {
    private ImageUtils() {
    }

    static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage savingImg = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        var stringFormat = switch (format) {
            case BMP -> "bmp";
            case PNG -> "png";
            default -> "jpeg";
        };

        for (int x = 0; x < image.height(); x++) {
            for (int y = 0; y < image.width(); y++) {
                var pixel = image.pixel(y, x);
                savingImg.setRGB(y, x, new Color(pixel.getR(), pixel.getG(), pixel.getB()).getRGB());
            }
        }

        File file = new File(filename.toString());
        ImageIO.write(savingImg, stringFormat, file);
    }

    static void gammaCorrection(FractalImage image, double gamma) {
        var width = image.width();
        var height = image.height();

        var max = 0.0;
        var power = 1 / gamma;

        double[] normals = new double[width * height];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (image.pixel(col, row).getHitCount() != 0) {
                    normals[row * width + col] = Math.log10(image.pixel(col, row).getHitCount());
                    if (normals[row * width + col] > max) {
                        max = normals[row * width + col];
                    }
                }
            }
        }

        double normal;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                normals[row * width + col] /= max;
                normal = normals[row * width + col];

                image.pixel(col, row).setNewValue(
                    (int) (image.pixel(col, row).getR() * pow(normal, power)),
                    (int) (image.pixel(col, row).getG() * pow(normal, power)),
                    (int) (image.pixel(col, row).getB() * pow(normal, power)),
                    image.pixel(col, row).getHitCount()
                );
            }
        }

    }

    enum ImageFormat {
        JPEG, BMP, PNG
    }
}

