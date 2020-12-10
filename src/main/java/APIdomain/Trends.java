package APIdomain;

public class Trends {
    private int id;
    private int totalManipulation;

    public Trends(int id, int totalManipulation) {
        this.id = id;
        this.totalManipulation = totalManipulation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalManipulation() {
        return totalManipulation;
    }

    public void setTotalManipulation(int totalManipulation) {
        this.totalManipulation = totalManipulation;
    }
}
