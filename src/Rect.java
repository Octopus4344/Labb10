import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class Rect extends Shape{

    private int width;
    private int height;



    public Rect(boolean filled, int width, int height,Point position) {
        super(filled, position);
        this.width = width;
        this.height = height;
    }

    @Override
    public void translate(Point p) {
        position.translate(p);
    }

    @Override
    public Point[] getBoundingBox() {
        Point[] arr = new Point[4];
        Point p4=position;
        int x1= p4.getX();
        int y1= p4.getY()-height;
        int y2 = y1;
        int x2 = x1+width;
        int x3 = x2;
        int y3 = p4.getY();
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
        Point[] arr = getBoundingBox();
        org.opencv.core.Point point1 = new org.opencv.core.Point(position.getX(), position.getY());
        org.opencv.core.Point point2 = new org.opencv.core.Point(arr[1].getX(), arr[1].getY());
        Scalar color = new Scalar(64, 64, 64);
        int thickness = 4;
        if(filled){
            thickness=Imgproc.FILLED;
        }
        Imgproc.rectangle (src, point1, point2, color, thickness);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        //HighGui.imshow("Drawing a rectangle", src);

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
