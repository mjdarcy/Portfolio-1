package recursion;
import java.awt.Color;

//import sedgewick.StdDraw;
import edu.princeton.cs.introcs.StdDraw;

public class Flower {

    /**
     *
     * @param x x coordinate of the center of this ellipse
     * @param y y coordinate of the center of this ellipse
     * @param halfWidth half the width of this ellipse
     * @param halfHeight half the height of this ellipse
     * @param count depth of the recursion, initial call passes 0
     */
    private static void flower(Color[] palette, double x, double y, double halfWidth, double halfHeight, int count){
        if(count==5){
            return;
        }
        count++;
        halfWidth/=2;
        halfHeight/=2;
        int index = (int)(Math.random()*palette.length);
        StdDraw.setPenColor(palette[index]);
        StdDraw.filledEllipse(x, y, halfWidth*2, halfHeight*2);

        flower(palette,x+halfWidth,y,halfWidth,halfHeight,count);
        flower(palette,x-halfWidth,y,halfWidth,halfHeight,count);
        flower(palette,x,y+halfWidth,halfWidth,halfHeight,count);
        flower(palette,x,y-halfWidth,halfWidth,halfHeight,count);
        flower(palette,x,y,halfWidth,halfHeight,count);
    }


    public static void start() {

        Color[] palette = { StdDraw.BLACK, StdDraw.BLUE, StdDraw.CYAN,
                StdDraw.DARK_GRAY, StdDraw.GRAY, StdDraw.GREEN,
                StdDraw.LIGHT_GRAY, StdDraw.MAGENTA, StdDraw.ORANGE,
                StdDraw.PINK, StdDraw.RED, StdDraw.WHITE, StdDraw.YELLOW };

        flower(palette, .5, .5, .3, .5, 0);
    }

}