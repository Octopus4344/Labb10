import java.util.LinkedList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Scene {
    //Reading the source image in to a Mat object
    private Mat src;

    private LinkedList<Item> items;
    private String name;
//    private Segment segment;
//    private Segment segment1;

    public Scene() {
        items= new LinkedList<>();
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        src = Imgcodecs.imread("C:\\Users\\Apolonia\\Pictures\\white.jpg");
        name = "drawing a scene";
//        segment= new Segment(new Point(100,300),new Point(100,400));
//        segment1= new Segment(new Point(300,800),new Point(30,400));
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void draw(){
        //Loading the OpenCV core library
        //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Loading the OpenCV core library
//        if(items.size()==0) {
//            System.out.println("Pusta scena");
//            return;
//        }
//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//        //Reading the source image in to a Mat object
//        Mat src = Imgcodecs.imread("C:\\Users\\Apolonia\\Pictures\\white.jpg");
        for (Item i: items) {
            i.draw(src);
        }
//        segment.draw(src);
//        segment1.draw(src);
        HighGui.imshow(name, src);
        HighGui.waitKey();
        name = name+ "1";


    }
    public void showItems(){
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i+": "+items.get(i).getClass().getName()+" x:" + items.get(i).getPosition().getX()+
                    " y: "+items.get(i).getPosition().getY());
        }
    }
    public void decorate(int index){
        Item decoratedItem = new DecoratedItem(items.get(index));
        items.remove(index);
        items.add(decoratedItem);

    }
}
