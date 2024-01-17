public abstract class Decorator extends  Item{
    protected final Item item;

    public Decorator(Point position, Item item) {
        super(position);
        this.item = item;
    }

    public Decorator(Item item) {
        this.item = item;
    }
}
