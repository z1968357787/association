package entity;


import java.sql.Date;
import java.util.Vector;

public class ActilityLoaction {
    private String address;
    private int lno;

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Vector<Object> GetFieldInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(lno);
        objects.add(address);
        return objects;
    }
    public Vector<Object> GetTaskInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(address);
        return objects;
    }
    public Vector<Object> GetVolunteerInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(address);
        return objects;
    }
    public Vector<Object> GetLocatedInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(lno);
        objects.add(address);
        return objects;
    }
    public Vector<Object> GetFieldUseCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(lno);
        objects.add(address);
        return objects;
    }
    public Vector<Object> GetTaskCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(address);
        return objects;
    }

}
