package domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(
            cascade = {CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.PERSIST,
                        CascadeType.REFRESH},
            mappedBy = "accounts_disponent")
    private List<User> disponents = new LinkedList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manipulatedAccount")
    private List<Manipulation> manipulations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationAccount")
    private List<OnlineTransaction> incomedTransactions = new ArrayList<>();

    public List<OnlineTransaction> getIncomedTransactions() {
        return incomedTransactions;
    }

    private int balance;

    @Transactional
    public void addDisponent(User newDisponent){
        this.getDisponents().add(newDisponent);
        newDisponent.getAccounts_disponent().add(this);
    }










    public Account() {
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getDisponents() {
        return disponents;
    }

    public void setDisponents(List<User> disponents) {
        this.disponents = disponents;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
