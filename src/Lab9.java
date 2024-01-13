import java.util.LinkedList;

public class Lab9 {
    public static void main(String[] args) {

        Item line = new Segment(new Point(100,300),new Point(500,800));
        Item line1 = new Segment(new Point(-100,-300),new Point(100,400));
        LinkedList<Item> list = new LinkedList<>();
//        System.out.println(complexItem.getPosition());
//        System.out.println(line.getPosition());
        Item rect = new Rect(false, 100, 300, new Point(1000,400));
        Item triangle = new Triangle(true,new Point(-10,-400),new Point(100,450),new Point(300,100));
        Item circle= new Circle(false, new Point(1000,1000),100);
        Item star= new Star(true, new Point(600,300),50);
        Item text = new TextItem(new Point(300,800), "wesrdftghjiuytrdhcgvhbtfdrthfcjgvhjuytfvabvch");
        list.add(rect);
        list.add(star);
        Item complexItem = new ComplexItem(list);
        Scene scene = new Scene();
        scene.addItem(line);
        scene.addItem(line1);
        scene.addItem(complexItem);
        //scene.addItem(rect);
        scene.addItem(triangle);
        scene.addItem(circle);
        scene.addItem(text);
        //scene.addItem(star);
        scene.draw();
    }
}
