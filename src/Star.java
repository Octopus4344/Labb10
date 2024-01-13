import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Star extends Shape{
   private int armLength;
   private Point[] points;

    public Star(boolean filled, Point position,int armLength) {
        super(filled, position);
        this.armLength=armLength;
        points=calculatePoints();
    }

    @Override
    public void translate(Point p) {
        position.translate(p);
        for(int i = 0; i< points.length;i++){
            points[i].translate(p);
        }
    }

    @Override
    public Point[] getBoundingBox() {
        Point p1 = new Point(position.getX(), points[0].getY());
        Point p2 = new Point(points[2].getX(), points[0].getY());
        Point p3 = new Point(points[2].getX(), position.getY());
        Point[] arr=  {position,p1,p2,p3};
        return arr;
    }

    @Override
    public void draw(Mat src) {
        List<MatOfPoint> list = new ArrayList<MatOfPoint>();
        org.opencv.core.Point point1 = new org.opencv.core.Point(points[0].getX(), points[0].getY());
        org.opencv.core.Point point2 = new org.opencv.core.Point(points[1].getX(), points[1].getY());
        org.opencv.core.Point point3 = new org.opencv.core.Point(points[2].getX(), points[2].getY());
        org.opencv.core.Point point4 = new org.opencv.core.Point(points[3].getX(), points[3].getY());
        org.opencv.core.Point point5 = new org.opencv.core.Point(points[4].getX(), points[4].getY());
        org.opencv.core.Point point6 = new org.opencv.core.Point(points[5].getX(), points[5].getY());
        org.opencv.core.Point point7 = new org.opencv.core.Point(points[6].getX(), points[6].getY());
        org.opencv.core.Point point8 = new org.opencv.core.Point(points[7].getX(), points[7].getY());
        list.add( new MatOfPoint (point1,point2,point3,point4,point5,point6,point7,point8));
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
            MatOfPoint points = new MatOfPoint (point1,point2,point3,point4,point5,point6,point7,point8);
            Scalar color = new Scalar(64, 64, 64);
            Imgproc.fillConvexPoly (src, points, color);
            //Saving and displaying the image
            Imgcodecs.imwrite("arrowed_line.jpg", src);
            HighGui.imshow("Drawing an polygon", src);
        }
        Point[] arr = getBoundingBox();
        org.opencv.core.Point point40 = new org.opencv.core.Point(position.getX(), position.getY());
        org.opencv.core.Point point50 = new org.opencv.core.Point(arr[2].getX(), arr[2].getY());
        Scalar color1 = new Scalar(64, 64, 64);
        int thickness1 = 1;
        Imgproc.rectangle (src, point40, point50, color1, thickness1);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        HighGui.imshow("Drawing a rectangle", src);


    }
    private Point[] calculatePoints() {

        int x7 = position.getX();
        int y7= position.getY()-armLength;
        int x8 = x7+ (2*armLength)/3;
        int y8= y7-armLength/3;
        int x1= x7+armLength;
        int y1= y7-armLength;
        int y2= y8;
        int x2 = x1+ armLength/3;
        int x3 = x1 + armLength;
        int y3= y7;
        int x4 = x2;
        int y4 = y3 + armLength/3;
        int x5 = x1;
        int y5 = position.getY();
        int x6= x8;
        int y6=y4;

        Point[] arr=  {new Point(x1,y1),new Point(x2,y2),new Point(x3,y3),new Point(x4,y4),new Point(x5,y5),
                new Point(x6,y6), new Point(x7,y7),new Point(x8,y8)};
        return arr;
    }


}
