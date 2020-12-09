package domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ATMObj")
public class ATMObject extends GenericObject {
    public ATMObject() {

    }

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "ATMofWithdraw")
    private List<ATMWithdrawl> atmWithdrawlList = new ArrayList<>();

    public List<ATMWithdrawl> getAtmWithdrawlList() {
        return atmWithdrawlList;
    }

    public void setAtmWithdrawlList(List<ATMWithdrawl> atmWithdrawlList) {
        this.atmWithdrawlList = atmWithdrawlList;
    }




}
