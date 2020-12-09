package domain;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Manipulation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar calendarTimestamp = Calendar.getInstance();

    private int sum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manipulated_account_id")
    private Account manipulatedAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Account getManipulatedAccount() {
        return manipulatedAccount;
    }

    public void setManipulatedAccount(Account manipulatedAccount) {
        this.manipulatedAccount = manipulatedAccount;
    }

    public Calendar getCalendarTimestamp() {
        return calendarTimestamp;
    }

    public void setCustomTime_H_D_M( int hour, int day, int month ){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        this.calendarTimestamp = cal;

    }
    public String getTimeasString(){
        Calendar cal = this.getCalendarTimestamp();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        System.out.println("month: " + month);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayS = day < 10 ? ("0" + day  ) : ""+ day;
        String monthS = month < 9 ? ("0" + (month + 1  ) ): ""+ (month +1);
        System.out.println(year + "-" + monthS + "-" +dayS);
        return (year + "-" + monthS + "-" +dayS);

    }


    public Manipulation() {
    }


//    public int compareTo(Manipulation m2 ) {
//        int myId= this.getId();
//            /* For Ascending order*/
//            return myId - m2.getId();
//
//    }
}
