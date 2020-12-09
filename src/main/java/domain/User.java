package domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id ;

    private String firstName;
    private String lastName;

    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH} )
    private List<Account> accounts_disponent =  new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy= "owner")
    private List<Account> accounts_owner = new LinkedList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,
    CascadeType.MERGE, CascadeType.PERSIST,
    CascadeType.REFRESH})
    @JoinColumn(name= "id_from_city")
    private City fromCity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BankManipulation> bankManipulationList = new ArrayList<>();


    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Transactional
    public void addAccount(Account newAccount){
        this.getAccounts_owner().add(newAccount);
        newAccount.setOwner(this);
    }


    public List<Account> getAccounts_owner() {
        return accounts_owner;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts_disponent() {
        return accounts_disponent;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }
}
