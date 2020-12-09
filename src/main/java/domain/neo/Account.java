package domain.neo;

public class Account {
    private int accountId;

    private String ownerIsFrom;
    private String ownerFirstName;
    private String ownerSecondName;


    public Account() {
    }


    public String getOwnerIsFrom() {
        return ownerIsFrom;
    }

    public void setOwnerIsFrom(String ownerIsFrom) {
        this.ownerIsFrom = ownerIsFrom;
    }

    public String getOwnerSecondName() {
        return ownerSecondName;
    }

    public void setOwnerSecondName(String ownerSecondName) {
        this.ownerSecondName = ownerSecondName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public Account(int accountId,  String ownerFirstName, String ownerSecondName,String ownerIsFrom) {
        this.accountId = accountId;
        this.ownerIsFrom = ownerIsFrom;
        this.ownerFirstName = ownerFirstName;
        this.ownerSecondName = ownerSecondName;
    }
}
