package domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CardObj")
public class CardObject extends GenericObject {
    private String nameOfPayableObject;

    public CardObject(String address, String cardPaymenttype) {
        super(address);
        this.nameOfPayableObject = cardPaymenttype;
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cardObjectOfWithdrawl")
    private List<CardWithdrawl> cardWithdrawlList = new ArrayList<>();

    public List<CardWithdrawl> getCardWithdrawlList() {
        return cardWithdrawlList;
    }

    public void setCardWithdrawlList(List<CardWithdrawl> cardWithdrawlList) {
        this.cardWithdrawlList = cardWithdrawlList;
    }

    public CardObject() {
    }

    public String getNameOfPayableObject() {
        return nameOfPayableObject;
    }

    public void setNameOfPayableObject(String type) {
        this.nameOfPayableObject = type;
    }
}
