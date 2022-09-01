package entity;

import java.sql.Date;
import java.util.Vector;

public class Located {
    private Association association=new Association();
    private ActilityLoaction actilityLoaction=new ActilityLoaction();
    private Date use_date;
    private String state;
    private int count;

    public Association getAssociation() {
        return association;
    }

    public ActilityLoaction getActilityLoaction() {
        return actilityLoaction;
    }

    public Date getUse_date() {
        return use_date;
    }

    public void setUse_date(Date use_date) {
        this.use_date = use_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Vector<Object> GetLocatedInformation(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(getAssociation().GetLocatedInformation());
        objects.addAll(getActilityLoaction().GetLocatedInformation());
        objects.add(getUse_date());
        objects.add(getState());
        return objects;
    }
    public Vector<Object> GetFieldUseCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetFieldUseCount1(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(actilityLoaction.GetFieldUseCount());
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetFieldUseCount2(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetFieldUseCount1());
        objects.addAll(actilityLoaction.GetFieldUseCount());
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetFieldUseCount3(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetFieldUseCount2());
        objects.addAll(actilityLoaction.GetFieldUseCount());
        objects.add(count);
        return objects;
    }
}
