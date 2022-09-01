package entity;

import java.util.Vector;

public class Acility {

    private int acno;
    private String acname;
    private String introduction;
    private String state;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private Task task=new Task();

    public int getAcno() {
        return acno;
    }

    public void setAcno(int acno) {
        this.acno = acno;
    }

    public String getAcname() {
        return acname;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Task getTask() {
        return task;
    }

    public Vector<Object> GetActivilityInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(acno);
        objects.add(acname);
        objects.add(state);
        objects.add(introduction);
        return objects;
    }
    public Vector<Object> GetTaskInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(acno);
        objects.add(acname);
        objects.addAll(task.GetTaskInformation());

        return objects;
    }
    public Vector<Object> GetVolunteerInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(acname);
        objects.addAll(task.GetVolunteerInformation());
        return objects;
    }
    public Vector<Object> GetLocatedInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(acno);
        objects.add(acname);
        return objects;
    }
    public Vector<Object> GetActivilityCount1(){
        Vector<Object> objects=new Vector<>();
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetActivilityCount2(){
        Vector<Object> objects=new Vector<>();
        objects.add(state);
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetFieldUseCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(acno);
        objects.add(acname);
        return objects;
    }
    public Vector<Object> GetTaskCount1(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(task.GetTaskCount1());
        return objects;
    }
    public Vector<Object> GetTaskCount2(){
        Vector<Object> objects=new Vector<>();
        objects.add(acname);
        objects.addAll(task.GetTaskCount1());
        return objects;
    }
    public Vector<Object> GetTaskCount3(){
        Vector<Object> objects=new Vector<>();
        objects.add(acname);
        objects.addAll(task.GetTaskCount2());
        return objects;
    }
    public Vector<Object> GetAcnos(){
        Vector<Object> objects=new Vector<>();
        objects.add(acno);
        objects.add(acname);
        return objects;
    }
    public Vector<Object> GetTnums(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(GetAcnos());
        objects.addAll(task.GetTnums());
        return objects;
    }
}
