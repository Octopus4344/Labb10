import java.util.LinkedList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class Scene {
    //Reading the source image in to a Mat object
    private Mat src;

    private LinkedList<IItem> items;
    private String name;

    public Scene() {
        items= new LinkedList<>();
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        src = Imgcodecs.imread("C:\\Users\\Apolonia\\Pictures\\white.jpg");
        name = "drawing a scene";
    }
    public void showItems(){
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i+":"+items.get(i).getClass().getName());
        }
    }

    public void addItem(IItem item){
        if(item instanceof Singleton){
            ((Singleton) item).removeItem(items);
        }
         items.add(item);
    }

    public LinkedList<IItem> getItems() {
        return items;
    }

    public void draw(){
        src = Imgcodecs.imread("C:\\Users\\Apolonia\\Pictures\\white.jpg");
        for (IItem i: items) {
            i.draw(src);
        }
        HighGui.imshow(name, src);
        HighGui.waitKey();
        name = name+ "1";


    }

    public void decorate(int index){
        IItem decoratedItem = new DecoratedItem(items.get(index));
        items.remove(index);
        items.add(decoratedItem);

    }
}
