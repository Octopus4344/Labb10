import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
public class TextItem extends Item {

    private String text;

    public TextItem(Point position, String text) {
        super(position);
        this.text = text;
    }

    @Override
    public void translate(Point p) {
        position.translate(p);
    }

    @Override
    public Point[] getBoundingBox() {
        Scalar color = new Scalar(0, 0, 255);
        int font = Imgproc.FONT_HERSHEY_SIMPLEX;
        int scale = 1;
        int thickness = 3;
        int[] arr= {position.getY()};
        double widthD = Imgproc.getTextSize(text, font, scale, thickness, arr).width;
        double heightD = Imgproc.getTextSize(text, font, scale, thickness, arr).height;
        int width = (int)widthD;
        int height=(int)heightD;
        Point point2 = new Point(position.getX()+width, position.getY());
        Point point3 = new Point(position.getX()+width, position.getY()-height);
        System.out.println(height);
        Point point4 = new Point(position.getX(), position.getY()-height);
        Point[]box={position,point2,point3,point4};
        return  box;




    }

    @Override
    public void draw(Mat src) {
        org.opencv.core.Point pos = new org.opencv.core.Point(position.getX(), position.getY());
        Scalar color = new Scalar(64, 64, 64);
        int font = Imgproc.FONT_HERSHEY_SIMPLEX;
        int scale = 1;
        int thickness = 3;
        //Adding text to the image
        Imgproc.putText(src, text, pos, font, scale, color, thickness);
        //Displaying the resultant Image
       // HighGui.imshow("Contours operation", src);
//        Point[] arr = getBoundingBox();
//        org.opencv.core.Point point2 = new org.opencv.core.Point(position.getX(), position.getY());
//        org.opencv.core.Point point1 = new org.opencv.core.Point(arr[2].getX(), arr[2].getY());
//        Scalar color1 = new Scalar(64, 64, 64);
//        int thickness1 = 1;
//        Imgproc.rectangle (src, point1, point2, color1, thickness1);
//        //Saving and displaying the image
//        Imgcodecs.imwrite("arrowed_line.jpg", src);
//        //HighGui.imshow("Drawing a rectangle", src);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
