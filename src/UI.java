import java.util.Scanner;

public class UI {

    private Scene scene;
    private Scanner scanner;

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
                addItem();
                break;
            case 2:
                System.out.println("lista obiektow");
                break;
            case 3:
                System.out.println("rysuje");
                scene.draw();
                break;
            case 4:
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
        System.out.println("4-Wyjdz");
    }
    public  void addItem() {
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
                addCircle();
                break;
            case 2:
                addRect();
                break;
            case 3:
                addSegment();
                break;
            case 4:
                addStar();
                break;
            case 5:
                addComplex();
                break;
            case 6:
                addText();
                break;
            case 7:
                addTriangle();
                break;


        }
    }

    public void addCircle(){
        System.out.println("Obiket wypelniony? false/true");
        boolean filled = scanner.nextBoolean();
        System.out.println("Podaj wspolrzedna x");
        int x = scanner.nextInt();
        System.out.println("Podaj wspolrzedna y");
        int y = scanner.nextInt();
        System.out.println("Podaj promien");
        int radius = scanner.nextInt();
        Item circle = new Circle(filled, new Point(x,y), radius);
        scene.addItem(circle);

    }
    public void addRect(){}
    public void addSegment(){}
    public void addStar(){}
    public void addComplex(){}
    public void addText(){}
    public void addTriangle(){}
}

