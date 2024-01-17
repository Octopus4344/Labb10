public abstract class Decorator implements IItem {
    protected final IItem item;

    public Decorator(IItem item) {
        this.item = item;
    }
}
