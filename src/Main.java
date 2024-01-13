import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
//        System.out.println("Wpisz rodzaj ksztaltu");
//        Scanner scanner = new Scanner(System.in);
//        String option = scanner.nextLine();

        // Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Reading the source image in to a Mat object
        Mat src = Imgcodecs.imread("C:\\Users\\Apolonia\\Pictures\\white.jpg");
        //Drawing a Rectangle
        Point point1 = new Point(100, 100);
        Point point2 = new Point(50, 300);
        Scalar color = new Scalar(64, 64, 64);
        int thickness = 4;
        Imgproc.rectangle (src, point1, point2, color, thickness);
        //Saving and displaying the image
        Imgcodecs.imwrite("arrowed_line.jpg", src);
        HighGui.imshow("Drawing a rectangle", src);
        HighGui.waitKey();
    }
}