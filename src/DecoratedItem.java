

import java.util.Arrays;
import java.util.LinkedList;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class DecoratedItem extends Item {
    private final Item item;

    public DecoratedItem(Item item) {
        this.item = item;
        this.position = item.getPosition();
    }

    public void translate(Point p) {
        this.item.translate(p);
    }

    public Point[] getBoundingBox() {
        return this.item.getBoundingBox();
    }

    public void draw(Mat src) {
        this.item.draw(src);
        this.decorate(src);
    }

    private void decorate(Mat src) {
        Point[] arr = this.item.getBoundingBox();
        LinkedList<Point> box = new LinkedList(Arrays.asList(arr));
        box.sort(Point::compareToHigher);
        int y0 = ((Point)box.getFirst()).getY();
        int y1 = ((Point)box.getLast()).getY();
        box.sort(Point::compareToCloser);
        int x0 = ((Point)box.getFirst()).getX();
        int x1 = ((Point)box.getLast()).getX();
        org.opencv.core.Point point4 = new org.opencv.core.Point((double)x0, (double)y0);
        org.opencv.core.Point point5 = new org.opencv.core.Point((double)x1, (double)y1);
        Scalar color1 = new Scalar(64.0, 64.0, 64.0);
        int thickness1 = 1;
        Imgproc.rectangle(src, point4, point5, color1, thickness1);
        Imgcodecs.imwrite("arrowed_line.jpg", src);
    }
}
