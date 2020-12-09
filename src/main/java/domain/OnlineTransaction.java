package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OnlineTransaction extends Manipulation {

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    private Boolean isReported;


    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
