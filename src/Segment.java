import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class Segment extends PrimitiveItem{

    private double length;
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {

        Point position = calculatePosition(start, end);
        this.position = position;
        this.length = calculateLength(start, end);
        this.start = start;
        this.end = end;
    }

    private Point calculatePosition(Point start, Point end){
        int x0 = start.getX();
        int y0 = start.getY();
        int y1= end.getY();
        int x1 = end.getX();
        int x = x0;
        int y = y0;
        if(x0>x1){
            x=x1;
        }
        if(y0<y1){
            y=y1;
        }
        return new Point(x,y);
    }
    private double calculateLength(Point start, Point end){
        int x0 = start.getX();
        int y0 = start.getY();
        int y1= end.getY();
        int x1 = end.getX();
        double l=Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1));
        return l;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void translate(Point p) {
        position.translate(p);
        end.translate(p);
        start.translate(p);
    }

    @Override
    public Point[] getBoundingBox() {
        Point[] list = new Point[4];
        Point p4=position;
        int x1= p4.getX();
        int y1= end.getY();
        if(start.getY()<end.getY()){
            y1= start.getY();
        }
        int y2 = y1;
        int x2 = end.getX();
        if(start.getX()>x2){
            x2= start.getX();
        }
        int x3 = x2;
        int y3 = p4.getY();
        Point p2 = new Point(x2,y2);
        Point p3 = new Point(x3,y3);
        Point p1 = new Point(x1,y1);

        list[0]=p1;
        list[1]=p2;
        list[2]=p3;
        list[3]=p4;

        return list;

    }


    public void draw(Mat src) {
        org.opencv.core.Point PStart = new org.opencv.core.Point(start.getX(), start.getY());
        org.opencv.core.Point PEnd = new org.opencv.core.Point(end.getX(), end.getY());
        Scalar color = new Scalar(64, 64, 64);
        int thickness = 10;
        Imgproc.line(src, PStart, PEnd, color, thickness);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        //HighGui.imshow("Drawing a line", src);
        Point[] arr = getBoundingBox();
        org.opencv.core.Point point1 = new org.opencv.core.Point(position.getX(), position.getY());
        org.opencv.core.Point point2 = new org.opencv.core.Point(arr[1].getX(), arr[1].getY());
        Scalar color1 = new Scalar(64, 64, 64);
        int thickness1 = 1;
        Imgproc.rectangle (src, point1, point2, color1, thickness1);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        //HighGui.imshow("Drawing a rectangle", src);


    }
}
