import org.opencv.core.Mat;

public interface IItem {
    void draw(Mat src);
    Point[] getBoundingBox();
    void translate(Point p);
    int compareToHigher(Object other);
    int compareToCloser(Object other);
}
