
public abstract class Shape extends PrimitiveItem{

    protected boolean filled;

    public Shape(boolean filled) {
        this.filled = filled;
    }

    public Shape(boolean filled, Point position) {
        super(position);
        this.filled=filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
