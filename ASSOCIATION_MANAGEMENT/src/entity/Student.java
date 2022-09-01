package entity;

import java.util.Vector;

public class Student extends Users{
    private double creait;

    @Override
    public double getCreait_or_Salary() {
        return creait;
    }

    @Override
    public void setCreait_or_Salary(double creait_or_salary) {
        this.creait=creait_or_salary;

    }
    public Vector<Object> GetMembersInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(id);
        objects.add(name);
        objects.add(department);
        objects.add(creait);
        return objects;
    }
    public Vector<Object> GetVolunteerInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(name);
        return objects;
    }
    public Vector<Object> GetMembersRequest(){
        Vector<Object> objects=new Vector<>();
        objects.add(id);
        objects.add(name);
        return objects;
    }
    public Vector<Object> GetVoteInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(name);
        return objects;
    }
    public Vector<Object> GetInvestigationsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(name);
        return objects;
    }
    public Vector<Object> GetBBSInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(id);
        objects.add(name);
        return objects;
    }
    public Vector<Object> GetMembersCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(department);
        return objects;
    }
}
