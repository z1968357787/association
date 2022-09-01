package entity;


import java.sql.Date;
import java.util.Vector;

public class Finance {
    private int fno;
    private String reason;
    private double money;
    private Date record_date;

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public Vector<Object> GetFinanceInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(fno);
        objects.add(money);
        objects.add(reason);
        objects.add(record_date);
        return objects;
    }
    public Vector<Object> GetFinanceInformation2(){
        Vector<Object> objects=new Vector<>();
        objects.add(money);
        return objects;
    }

}
