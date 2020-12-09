package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "atm_withdrawl")
public class ATMWithdrawl extends Manipulation {
    public ATMWithdrawl() {
        super();
    }

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "atm_id")
    private ATMObject ATMofWithdraw;

    public ATMObject getATMofWithdraw() {
        return ATMofWithdraw;
    }

    public void setATMofWithdraw(ATMObject ATMofWithdraw) {
        this.ATMofWithdraw = ATMofWithdraw;
    }
}