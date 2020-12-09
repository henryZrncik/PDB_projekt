package domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BankObj")
public class BankObject extends GenericObject {
    private String bankCompany;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private List<BankManipulation> bankManipulationList = new ArrayList<>();


    public BankObject(String bankCompany) {
        this.bankCompany = bankCompany;
    }

    public BankObject() {
    }

    public String getBankCompany() {
        return bankCompany;
    }

    public void setBankCompany(String bankCompany) {
        this.bankCompany = bankCompany;
    }
}
