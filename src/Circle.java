import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.LinkedList;

public class Circle extends Shape{

    private int radius;
    private Point center;


    public Circle(boolean filled, Point position, int radius) {
        super(filled, position);
        this.radius = radius;
        center = calculateCenter();
    }

    private Point calculateCenter() {
        int x = position.getX()+radius;
        int y = position.getY()-radius;
        return new Point(x,y);
    }

    @Override
    public void translate(Point p) {
        center.translate(p);
        position.translate(p);
    }

    @Override
    public Point[] getBoundingBox() {
        Point[] arr = new Point[4];
        Point p4=position;
        int x1= p4.getX();
        int y1 = p4.getY()- 2* radius;
        int x3;
        int y3=p4.getY();
        int x2 = x1+ 2* radius;
        int y2 = y1;
        x3 = x2;

        Point p2 = new Point(x2,y2);
        Point p3 = new Point(x3,y3);
        Point p1 = new Point(x1,y1);

        arr[0]=p1;
        arr[1]=p2;
        arr[2]=p3;
        arr[3]=p4;

        return arr;
    }

    @Override
    public void draw(Mat src) {
        org.opencv.core.Point Pcenter = new org.opencv.core.Point(center.getX(), center.getY());
        Scalar color = new Scalar(64, 64, 64);
        int thickness = 10;
        if(filled){
            thickness=Imgproc.FILLED;;
        }
        Imgproc.circle (src, Pcenter, radius, color, thickness);
        //Saving and displaying the image
        Imgcodecs.imwrite("circle.jpg", src);
        HighGui.imshow("Drawing a Circle", src);
        Point[] arr = getBoundingBox();
        org.opencv.core.Point point1 = new org.opencv.core.Point(position.getX(), position.getY());
        org.opencv.core.Point point2 = new org.opencv.core.Point(arr[1].getX(), arr[1].getY());
        Scalar color1 = new Scalar(64, 64, 64);
        int thickness1 = 1;
        Imgproc.rectangle (src, point1, point2, color1, thickness1);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        HighGui.imshow("Drawing a rectangle", src);

    }
}
