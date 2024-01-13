import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Triangle extends Shape{

    private Point p1;
    private Point p2;
    private Point p3;

    public Triangle(boolean filled, Point p1, Point p2, Point p3) {
        super(filled);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.position=calculatePosition(p1,p2,p3);
    }

    public Point getP1() {
        return p1;
    }


    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    private Point calculatePosition(Point p1, Point p2, Point p3) {
        LinkedList<Point> list = new LinkedList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.sort(Point::compareToCloser);
        int x = list.getFirst().getX();
        list.sort(Point::compareToHigher);
        int y = list.getFirst().getY();
        return new Point(x,y);
    }

    @Override
    public void translate(Point p) {
        position.translate(p);
        p1.translate(p);
        p2.translate(p);
        p3.translate(p);

    }

    @Override
    public Point[] getBoundingBox() {
        LinkedList<Point> list = new LinkedList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Point[] arr = new Point[4];
        Point p4=position;
        int x1= p4.getX();
        int y3 = p4.getY();
        list.sort(Point::compareToCloser);
        int x2 = list.getLast().getX();
        int x3 = x2;
        list.sort(Point::compareToHigher);
        int y1 = list.getLast().getY();
        int y2 = y1;
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
        List<MatOfPoint> list = new ArrayList<MatOfPoint>();
        org.opencv.core.Point point1 = new org.opencv.core.Point(p1.getX(), p1.getY());
        org.opencv.core.Point point2 = new org.opencv.core.Point(p2.getX(), p2.getY());
        org.opencv.core.Point point3 = new org.opencv.core.Point(p3.getX(), p3.getY());
        list.add( new MatOfPoint (point1,point2,point3));
        if(!filled) {
            boolean isClosed = true;
            Scalar color = new Scalar(64, 64, 64);
            int thickness = 10;
            Imgproc.polylines(src, list, isClosed, color, thickness);
            //Saving and displaying the image
            Imgcodecs.imwrite("arrowed_line.jpg", src);
            HighGui.imshow("Drawing a polylines", src);
        }
        else{
            MatOfPoint points = new MatOfPoint (point1,point2,point3);
            Scalar color = new Scalar(64, 64, 64);
            Imgproc.fillConvexPoly (src, points, color);
            //Saving and displaying the image
            Imgcodecs.imwrite("arrowed_line.jpg", src);
           // HighGui.imshow("Drawing an polygon", src);
        }
//        Point[] arr = getBoundingBox();
//        org.opencv.core.Point point4 = new org.opencv.core.Point(position.getX(), position.getY());
//        org.opencv.core.Point point5 = new org.opencv.core.Point(arr[1].getX(), arr[1].getY());
//        Scalar color1 = new Scalar(64, 64, 64);
//        int thickness1 = 1;
//        Imgproc.rectangle (src, point4, point5, color1, thickness1);
//        //Saving and displaying the image
//        Imgcodecs.imwrite("arrowed_line.jpg", src);
//        //HighGui.imshow("Drawing a rectangle", src);


    }
}
