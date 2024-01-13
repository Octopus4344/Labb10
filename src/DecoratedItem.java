import org.opencv.core.Mat;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

public class DecoratedItem extends  Item{
    private final Item item;

    public DecoratedItem(Item item) {
        this.item = item;
        this.position=item.getPosition();
    }

    @Override
    public void translate(Point p) {
        item.translate(p);
    }

    @Override
    public Point[] getBoundingBox() {
        return item.getBoundingBox();
    }

    @Override
    public void draw(Mat src) {
        item.draw(src);
        decorate(src);
    }

    private void decorate(Mat src){
        Point[] arr = item.getBoundingBox();
        LinkedList<Point> box = new LinkedList<>(Arrays.asList(arr));
        box.sort(Point::compareToHigher);
        int y0= box.getFirst().getY();
        int y1= box.getLast().getY();
        box.sort(Point::compareToCloser);
        int x0=box.getFirst().getX();
        int x1 = box.getLast().getX();
        org.opencv.core.Point point4 = new org.opencv.core.Point(x0, y0);
        org.opencv.core.Point point5 = new org.opencv.core.Point(x1, y1);
        Scalar color1 = new Scalar(64, 64, 64);
        int thickness1 = 1;
        Imgproc.rectangle (src, point4, point5, color1, thickness1);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        //HighGui.imshow("Drawing a rectangle", src);
    }
}
