public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return "X: "+x+" Y: "+y;
    }

    public int compareToHigher(Object other){
        Point o = (Point) other;
        int y0 = this.getY();
        int y1= o.getY();
        if(y0>y1) return 1;
        if(y0==y1) return 0;
        return -1;
    }

    public int compareToCloser(Object other){
        Point o = (Point) other;
        int x0 = this.getX();
        int x1 = o.getX();
        if(x0>x1) return 1;
        if(x0==x1) return 0;
        return -1;
    }

    public void translate(Point p){
         x = p.getX()+ this.x;
         y = p.getY()+this.y;

    }



}
