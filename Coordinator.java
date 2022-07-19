package Sweeper;

public class Coordinator {
    public int x;
    public int y;

    public Coordinator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinator) {
            Coordinator to = (Coordinator)o;
            return to.x == x && to.y == y;
        }
        return super.equals(o);
    }
}