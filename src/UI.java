import java.util.LinkedList;
import java.util.Scanner;

public class UI {

    private final Scene scene;
    private final Scanner scanner;

    public UI(Scene scene) {
        this.scene = scene;
        scanner = new Scanner(System.in);
    }

    public void run(){
        boolean exit=false;
        displayMenu();
        int choice=scanner.nextInt();
        switch(choice){
            case 1:
                scene.addItem(addItem());
                break;
            case 2:
                System.out.println("lista obiektow");
                scene.showItems();
                break;
            case 3:
                scene.draw();
                break;
            case 4:
                int index=scanner.nextInt();
                scene.decorate(index);
                scene.draw();
                break;
            default:
                exit = true;
                break;
        }
        if(!exit) run();

    }
    public  void displayMenu(){
        System.out.println("Menu:");
        System.out.println("1-Dodaj nowy obiekt do sceny");
        System.out.println("2-wyswietl liste obiektiw dodanych do sceny");
        System.out.println("3-Rysuj scene");
        System.out.println("4-Rysuj prostokat ograniczajacy dla obiektu o wybranym indkesie");
        System.out.println("5-Wyjdz");

    }
    public Item addItem() {
        System.out.println("Wybierz obiekt:");
        System.out.println("1-Kolo");
        System.out.println("2-Prostokat");
        System.out.println("3-Odcinek");
        System.out.println("4-Gwiazda");
        System.out.println("5-Obiekt zlozony");
        System.out.println("6-Tekst");
        System.out.println("7-Trojkat");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return addCircle();
            case 2:
                return addRect();
            case 3:
                return addSegment();
            case 4:
                return addStar();
            case 5:
                return addComplex();
            case 6:
                return addText();
            case 7:
                return addTriangle();
            default:
                System.out.println("nieprawidlowy wybor");
                return addItem();


        }
    }

    public Item addCircle(){
        System.out.println("Obiket wypelniony? false/true");
        boolean filled = scanner.nextBoolean();
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Podaj promien");
        int radius = scanner.nextInt();
        return new Circle(filled, new Point(x,y), radius);

    }
    public Item addRect(){
        System.out.println("Obiket wypelniony? false/true");
        boolean filled = scanner.nextBoolean();
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Podaj wysokosc");
        int height = scanner.nextInt();
        System.out.println("Podaj szerokosc");
        int width = scanner.nextInt();
        return new Rect(filled,width, height, new Point(x,y));

    }
    public Item addSegment(){
        System.out.println("Punkt startowy:");
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Punkt koncowy");
        System.out.println("Podaj wspolrzedna x");
        int x1 = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y1 = scanner.nextInt();
        return new Segment(new Point(x,y),new Point(x1,y1));

    }
    public Item addStar(){
        System.out.println("Obiket wypelniony? false/true");
        boolean filled = scanner.nextBoolean();
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Podaj promien");
        int radius = scanner.nextInt();
        return new Star(filled, new Point(x,y), radius);

    }
    public Item addComplex(){
        System.out.println("Ile obiektow chcesz dodac?");
        int choice = scanner.nextInt();
        ComplexItem complex = new ComplexItem(new LinkedList<>());
        for(int i = 0; i<choice; i++){
            complex.add(addItem());
        }
        return complex;

    }
    public Item addText(){
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Podaj tresc");
        scanner.nextLine();
        String sText = scanner.nextLine();
        return new TextItem(new Point(x,y),sText);

    }
    public Item addTriangle(){
        System.out.println("Obiket wypelniony? false/true");
        boolean filled = scanner.nextBoolean();
        System.out.println("Punkt pierwszy:");
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Punkt drugi");
        System.out.println("Podaj wspolrzedna x");
        int x1 = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y1 = scanner.nextInt();
        System.out.println("Punkt trzeci:");
        System.out.println("Podaj wspolrzedna x");
        int x3 = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y3 = scanner.nextInt();
        return new Triangle(filled, new Point(x,y), new Point(x1,y1), new Point(x3,y3));
    }

}

