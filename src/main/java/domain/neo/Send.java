package domain.neo;

public class Send {
    private int transactionId;
    private int sum;
    private int sourceAccId;
    private int destinationAccId;


    public Send(int transactionId, int sum, int sourceAccId, int destinationAccId) {
        this.transactionId = transactionId;
        this.sourceAccId = sourceAccId;
        this.destinationAccId = destinationAccId;
        this.sum = sum;

    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getSum() {
        return sum;
    }

    public int getSourceAccId() {
        return sourceAccId;
    }

    public void setSourceAccId(int sourceAccId) {
        this.sourceAccId = sourceAccId;
    }

    public int getDestinationAccId() {
        return destinationAccId;
    }

    public void setDestinationAccId(int destinationAccId) {
        this.destinationAccId = destinationAccId;
    }
}
