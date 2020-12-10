package APIdomain;

public class Acc {
    private String ownerName;
    private String ownerLastName;
    private int balance;

    public Acc(String ownerName, String ownerLastName, int balance) {
        this.ownerName = ownerName;
        this.ownerLastName = ownerLastName;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Acc{" +
                "ownerName='" + ownerName + '\'' +
                ", ownerLastName='" + ownerLastName + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Acc() {
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
