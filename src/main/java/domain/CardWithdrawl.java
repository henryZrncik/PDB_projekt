package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




@Entity(name = "card_withdrawl")
public class CardWithdrawl extends Manipulation {
    public CardWithdrawl() {
        super();
    }

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "card_payable_obj_id")
    private CardObject cardObjectOfWithdrawl;

    public CardObject getCardObjectOfWithdrawl() {
        return cardObjectOfWithdrawl;
    }

    public void setCardObjectOfWithdrawl(CardObject cardObjectOfWithdrawl) {
        this.cardObjectOfWithdrawl = cardObjectOfWithdrawl;
    }
}