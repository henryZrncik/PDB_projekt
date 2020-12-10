package APIdomain;

public class Transaction {
    int id ;
    int sum;
    int destinationAccId;
    String date;
    boolean isIn;
    int sourceAccId;

    public Transaction(int id, int sum, int accountId, String date, boolean isIn, int source) {
        this.id = id;
        this.sum = sum;
        this.destinationAccId = accountId;
        this.date = date;
        this.isIn = isIn;
        this.sourceAccId = source;
    }
}
