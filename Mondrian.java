import java.util.*;
import java.awt.*;

// Allows the user to create Mondrian art. It allows 
// users to create two types of Mondrian art, one where 
// the colors are random and another where the colors are 
// more focused in specific areas. 
public class Mondrian {
    private final int minPaintingSize = 300;
    private final int smallestSubsection = 10;
    private Random random = new Random();

    // Behavior : 
    //    - edits the picture given to Mondrian art
    // Parameters : 
    //    - Color[][] : the pixels in the picture that needs to be edited
    // Returns : None
    // Exceptions : 
    //    - IllegalArgumentException the pixels are null and if the width or height are less than 
    //      minPaintingSize pixels
    public void paintBasicMondrian(Color[][] pixels) {
        if (pixels == null || pixels.length < minPaintingSize || pixels[0].length < minPaintingSize) {
            throw new IllegalArgumentException();
        } 
        paintBasicMondrian(pixels, 0, pixels[0].length, 0, pixels.length);
    }

    // Behavior : 
    //    - edits the picture given to Mondrian art
    // Parameters : 
    //    - Color[][] : the pixels in the picture that needs to be edited
    //    - int x1 : the starting point of the image (top left)
    //    - int x2 : the end of the image horizonally (top right)
    //    - int y1 : the starting point of the image (top left)
    //    - int y2 : the end of the image vertically (bottom left)
    // Returns : None
    // Exceptions : None
    private void paintBasicMondrian(Color[][] pixels, int x1, int x2, int y1, int y2) {
        if (x2 - x1 > pixels[0].length / 4 && y2 - y1 > pixels.length / 4) {
            int randomHorizontal = getRandomNum(y2, y1);
            int randomVertical = getRandomNum(x2, x1);
            paintBasicMondrian(pixels, x1, randomVertical, y1, randomHorizontal);
            paintBasicMondrian(pixels, x1, randomVertical, randomHorizontal, y2);
            paintBasicMondrian(pixels, randomVertical, x2, randomHorizontal, y2);
            paintBasicMondrian(pixels, randomVertical, x2, y1, randomHorizontal);
        } else if (x2 - x1 > pixels[0].length / 4) {
            int randomVertical = getRandomNum(x2, x1);
            paintBasicMondrian(pixels, x1, randomVertical, y1, y2);
            paintBasicMondrian(pixels, randomVertical, x2, y1, y2);
        } else if (y2 - y1 > pixels.length / 4) {
            int randomHorizontal = getRandomNum(y2, y1);
            paintBasicMondrian(pixels, x1, x2, y1, randomHorizontal);
            paintBasicMondrian(pixels, x1, x2, randomHorizontal, y2);
        } else {
            fill(pixels, x1, x2, y1, y2);
        }
    }

    // Behavior : 
    //    - returns a random number between the min and max
    // Parameters : 
    //    - int max : the max the random number can be
    //    - int min : the min the random number can be
    // Returns : 
    //    - int : the random number generated
    // Exceptions : None
    private int getRandomNum(int max, int min) {
        int randNum = random.nextInt(max - min + 1) + min;
        while (randNum - min < smallestSubsection || max - randNum < smallestSubsection) {
            randNum = random.nextInt(max - min + 1) + min;
        }
        return randNum;
    }


    // Behavior : 
    //    - fills the space given with a random color
    // Parameters : 
    //    - Color[][] : the pixels in the picture that needs to be edited
    //    - int x1 : the starting point of the area needing to be colored (top left)
    //    - int x2 : the end of the area needing to be colored horizonally (top right)
    //    - int y1 : the starting point of area needing to be colored (top left)
    //    - int y2 : the end of the area needing to be colored vertically (bottom left)
    // Returns : None
    // Exceptions : None
    private void fill(Color[][] pixels, int x1, int x2, int y1, int y2) {
        int color = random.nextInt(3 - 0 + 1);
        Color[] colors = {Color.RED, Color.YELLOW ,Color.CYAN, Color.WHITE};
        for (int i = Math.max(y1 + 1, 0); i < Math.min(y2 - 1, pixels.length); i++) {
            for (int j = Math.max(x1 + 1, 0); j < Math.min(x2 - 1, pixels[0].length); j++) {
                pixels[i][j] = colors[color];
            }
        }
    }

    // Behavior : 
    //    - edits the picture given to Mondrian art (focused colors)
    // Parameters : 
    //    - Color[][] : the pixels in the picture that needs to be edited
    // Returns : None
    // Exceptions : 
    //    - IllegalArgumentException the pixels are null and if the width or height are less than 
    //      minPaintingSize pixels
    public void paintComplexMondrian(Color[][] pixels) {
        if (pixels == null || pixels.length < minPaintingSize || pixels[0].length < minPaintingSize) {
            throw new IllegalArgumentException();
        }
        paintComplexMondrian(pixels, 0, pixels[0].length, 0, pixels.length);
    }

    // Behavior : 
    //    - edits the picture given to Mondrian art (focused colors)
    // Parameters : 
    //    - Color[][] : the pixels in the picture that needs to be edited
    //    - int x1 : the starting point of the image (top left)
    //    - int x2 : the end of the image horizonally (top right)
    //    - int y1 : the starting point of the image (top left)
    //    - int y2 : the end of the image vertically (bottom left)
    // Returns : None
    // Exceptions : None
    private void paintComplexMondrian(Color[][] pixels, int x1, int x2, int y1, int y2) {
        if (x2 - x1 > pixels[0].length / 4 && y2 - y1 > pixels.length / 4) {
            int randomHorizontal = getRandomNum(y2, y1);
            int randomVertical = getRandomNum(x2, x1);
            paintBasicMondrian(pixels, x1, randomVertical, y1, randomHorizontal);
            paintBasicMondrian(pixels, x1, randomVertical, randomHorizontal, y2);
            paintBasicMondrian(pixels, randomVertical, x2, randomHorizontal, y2);
            paintBasicMondrian(pixels, randomVertical, x2, y1, randomHorizontal);
        } else if (x2 - x1 > pixels[0].length / 4) {
            int randomVertical = getRandomNum(x2, x1);
            paintBasicMondrian(pixels, x1, randomVertical, y1, y2);
            paintBasicMondrian(pixels, randomVertical, x2, y1, y2);
        } else if (y2 - y1 > pixels.length / 4) {
            int randomHorizontal = getRandomNum(y2, y1);
            paintBasicMondrian(pixels, x1, x2, y1, randomHorizontal);
            paintBasicMondrian(pixels, x1, x2, randomHorizontal, y2);
        } else {
            fillComplex(pixels, x1, x2, y1, y2);
        }
    }

    // Behavior : 
    //    - fills the space given with a random color with weight to specific colors
    //      based on location
    // Parameters : 
    //    - Color[][] : the pixels in the picture that needs to be edited
    //    - int x1 : the starting point of the area needing to be colored (top left)
    //    - int x2 : the end of the area needing to be colored horizonally (top right)
    //    - int y1 : the starting point of area needing to be colored (top left)
    //    - int y2 : the end of the area needing to be colored vertically (bottom left)
    // Returns : None
    // Exceptions : None
    private void fillComplex(Color[][] pixels, int x1, int x2, int y1, int y2) {
        Color[] colors = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
        double xCenter = (x1 + x2) / 2.0 / pixels[0].length;
        double yCenter = (y1 + y2) / 2.0 / pixels.length;
        double red = (1 - xCenter) * (1 - yCenter);
        double blue = xCenter * yCenter;
        double yellow = (1 - yCenter) * xCenter;
        double white = yCenter * (1 - xCenter);
        double randomNum = random.nextDouble();
        int color;
        if (randomNum < red) {
            color = 0;
        } else if (randomNum < red + blue) {
            color = 1;
        } else if (randomNum < red + blue + yellow) {
            color = 2;
        } else {
            color = 3;
        }
        for (int i = Math.max(y1 + 1, 0); i < Math.min(y2 - 1, pixels.length); i++) {
            for (int j = Math.max(x1 + 1, 0); j < Math.min(x2 - 1, pixels[0].length); j++) {
                pixels[i][j] = colors[color];
            }
        }
    }
}
