package APIdomain;

public class Transaction {
    int id ;
    int sum;
    int destinationAccId;
    String date;
    boolean isIn;

    public Transaction(int id, int sum, int accountId, String date, boolean isIn) {
        this.id = id;
        this.sum = sum;
        this.destinationAccId = accountId;
        this.date = date;
        this.isIn = isIn;
    }
}
