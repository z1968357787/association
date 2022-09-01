package entity;

import java.util.Vector;

public class Participate {

    private Student student;
    private Association association;
    private String state;

    private int count;

    public Participate() {
        student=new Student();
        association=new Association();
    }

    public Student getStudent() {
        return student;
    }

    public Association getAssociation() {
        return association;
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

    public Vector<Object> GetMembersInformation(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetMembersInformation());
        objects.addAll(student.GetMembersInformation());
        objects.add(state);
        return objects;
    }
    public Vector<Object> GetMembersRequest(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetMembersRequest());
        objects.addAll(student.GetMembersRequest());
        objects.add(state);
        return objects;
    }
    public Vector<Object> GetMembersCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetMembersCount1(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetMembersCount());
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetMembersCount2(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetMembersCount());
        objects.addAll(student.GetMembersCount());
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetMembersCount3(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetMembersCount());
        objects.add(state);
        objects.add(count);
        return objects;
    }
}
