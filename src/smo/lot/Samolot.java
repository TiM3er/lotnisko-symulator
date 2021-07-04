package smo.lot;

public class Samolot {
    private int priority;
    private int id;

    public Samolot(int priority, int id) {
        this.priority = priority;
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Samolot{" +
                "priority=" + priority +
                ", id=" + id +
                '}';
    }
}
