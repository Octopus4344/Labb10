import java.util.Arrays;
import java.util.LinkedList;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class DecoratedItem extends Decorator {


    public DecoratedItem(IItem item) {
        super(item);
    }

    public void translate(Point p) {
        this.item.translate(p);
    }

    @Override
    public int compareToHigher(Object other) {
        return item.compareToHigher(other);
    }

    @Override
    public int compareToCloser(Object other) {
        return item.compareToCloser(other);
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
        int y0 = (box.getFirst()).getY();
        int y1 = (box.getLast()).getY();
        box.sort(Point::compareToCloser);
        int x0 = (box.getFirst()).getX();
        int x1 = (box.getLast()).getX();
        org.opencv.core.Point point4 = new org.opencv.core.Point(x0, y0);
        org.opencv.core.Point point5 = new org.opencv.core.Point(x1, y1);
        Scalar color1 = new Scalar(64.0, 64.0, 64.0);
        int thickness1 = 1;
        Imgproc.rectangle(src, point4, point5, color1, thickness1);
        Imgcodecs.imwrite("arrowed_line.jpg", src);
    }
}
