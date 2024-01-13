import org.opencv.core.Mat;

import java.util.LinkedList;

public abstract class Item {
    protected Point position;

    public Item(Point position) {
        this.position = position;
    }
    public Item() {
        this.position = new Point();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public abstract void translate(Point p);
//    {
//        int x = p.getX()+ position.getX();
//        int y = p.getY()+position.getY();
//        Point point = new Point(x,y);
//        this.position=point;
//
//    }

    public abstract Point[] getBoundingBox();

    public abstract void draw(Mat src);

    public int compareToHigher(Object other){
        Item o = (Item) other;
        int y0 = this.position.getY();
        int y1= o.position.getY();
        if(y0>y1) return 1;
        if(y0==y1) return 0;
        return -1;
    }

    public int compareToCloser(Object other){
        Item o = (Item) other;
        int x0 = this.position.getX();
        int x1 = o.position.getX();
        if(x0>x1) return 1;
        if(x0==x1) return 0;
        return -1;
    }


}
