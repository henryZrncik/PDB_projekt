package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BankManipulation extends Manipulation{
    private boolean isDeposit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "bank_id")
    private BankObject bank;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setBank(BankObject bank) {
        this.bank = bank;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public BankObject getBank() {
        return bank;
    }

    public User getUser() {
        return user;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }
}
