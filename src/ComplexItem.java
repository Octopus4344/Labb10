import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.*;

public class ComplexItem extends Item implements Singleton{
    private Boolean containsSingleton;

    LinkedList<IItem> children;

    public ComplexItem(Point position) {
        super(position);
        //this.children = children;

    }

//    public ComplexItem(LinkedList<Item> children) {
//        this.children = children;
//        this.position=calculatePosition();
//
//    }
    public ComplexItem() {
        children= new LinkedList<>();
    }
    public void add(IItem child){
        if(child instanceof ComplexItem){
            if(((ComplexItem) child).getContainsSingleton()) {
                ((Singleton) child).removeItem(children);
                containsSingleton = true;
            }
        }
        else if(child instanceof Singleton){
            ((Singleton) child).removeItem(children);
            containsSingleton=true;
        }
        children.add(child);
        this.position=calculatePosition();
    }

    public Boolean getContainsSingleton() {
        return containsSingleton;
    }

    public LinkedList<IItem> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<IItem> children) {
        this.children = children;
    }

    @Override
    public void translate(Point p) {
        position.translate(p);
        for (IItem i: children) {
            i.translate(p);
        }
    }

    @Override
    public Point[] getBoundingBox() {
        if (children.isEmpty()) return null;
        children.sort(IItem::compareToHigher);
        //int y3 = children.get(0).position.getY();
        LinkedList<Point> list =new LinkedList<Point>(Arrays.asList(children.getLast().getBoundingBox()));
        LinkedList<Point> list1 =new LinkedList<Point>(Arrays.asList(children.getFirst().getBoundingBox()));
        children.sort(IItem::compareToCloser);
        LinkedList<Point> list2 =new LinkedList<Point>(Arrays.asList(children.getLast().getBoundingBox()));
        LinkedList<Point> list3 =new LinkedList<Point>(Arrays.asList(children.getFirst().getBoundingBox()));
        list.sort(Point::compareToHigher);
        list1.sort(Point::compareToHigher);
        list2.sort(Point::compareToCloser);
        list3.sort(Point::compareToCloser);
        int y3 = list1.getFirst().getY();
        int y2=list.getLast().getY();
        int y1= y2;
        int x1 = list3.getFirst().getX();
        int x2 = list2.getLast().getX();
        int x3 =x2;
        Point[] arr = {new Point(x1,y1), new Point(x2,y2), new Point(x3,y3), position};
        return arr;
    }

    @Override
    public void draw(Mat src) {
        if (children.isEmpty()) return;
        for (IItem i: children) {
            i.draw(src);
        }
        Point[] arr = getBoundingBox();
        org.opencv.core.Point point4 = new org.opencv.core.Point(position.getX(), position.getY());
        org.opencv.core.Point point5 = new org.opencv.core.Point(arr[1].getX(), arr[1].getY());
        Scalar color1 = new Scalar(64, 64, 64);
//        int thickness1 = 1;
//        Imgproc.rectangle (src, point4, point5, color1, thickness1);
//        //Saving and displaying the image
//        Imgcodecs.imwrite("arrowed_line.jpg", src);
        //HighGui.imshow("Drawing a rectangle", src);


    }
    public Point calculatePosition(){
        if (children.isEmpty()) return null;
        children.sort(IItem::compareToHigher);
        LinkedList<Point> list1 =new LinkedList<Point>(Arrays.asList(children.getFirst().getBoundingBox()));
        children.sort(IItem::compareToCloser);
        LinkedList<Point> list3 =new LinkedList<Point>(Arrays.asList(children.getFirst().getBoundingBox()));
        list1.sort(Point::compareToHigher);
        list3.sort(Point::compareToCloser);
        int x = list3.getFirst().getX();
        int y = list1.getFirst().getY();
//        children.sort(Item::compareToHigher);
//        int y = children.get(0).position.getY();
//        children.sort(Item::compareToCloser);
//        int x = children.get(0).position.getX();
        return  new Point(x,y);
    }

    @Override
    public void removeItem(LinkedList<IItem> list) {
        for(int i =0; i< list.size(); i++){
            if(list.get(i) instanceof ComplexItem){
                removeItem(((ComplexItem) list.get(i) ).getChildren());
            }
            else if(list.get(i) instanceof Singleton){
                list.remove(i);
            }

            else if(list.get(i) instanceof DecoratedItem){
                if (((DecoratedItem) list.get(i)).item instanceof Singleton ) list.remove(list.get(i));
//                else if ((((DecoratedItem) myItem).item instanceof ComplexItem ))
//                    removeItem(((ComplexItem) (((DecoratedItem) myItem)).item).getChildren());
            }

        }
        this.position=calculatePosition();
    }
}
