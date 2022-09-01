package entity;

import java.sql.Date;
import java.util.Vector;

public class Task {
    private int tnum;
    private String assignment;
    private int duration;//服务时长
    private int people_haven;
    private int people_needed;
    private ActilityLoaction actilityLoaction=new ActilityLoaction();
    private Date volunteer_date;
    private int count;

    public int getTnum() {
        return tnum;
    }

    public void setTnum(int tnum) {
        this.tnum = tnum;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPeople_haven(int people_haven) {
        this.people_haven = people_haven;
    }

    public int getPeople_needed() {
        return people_needed;
    }

    public void setPeople_needed(int people_needed) {
        this.people_needed = people_needed;
    }

    public ActilityLoaction getActilityLoaction() {
        return actilityLoaction;
    }

    public Date getVolunteer_date() {
        return volunteer_date;
    }

    public void setVolunteer_date(Date volunteer_date) {
        this.volunteer_date = volunteer_date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Vector<Object> GetTaskInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(tnum);
        objects.add(assignment);
        objects.add(duration);
        objects.add(people_haven);
        objects.add(people_needed);
        objects.addAll(actilityLoaction.GetTaskInformation());
        objects.add(volunteer_date);
        return objects;
    }
    public Vector<Object> GetVolunteerInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(assignment);
        objects.addAll(actilityLoaction.GetVolunteerInformation());
        objects.add(volunteer_date);
        return objects;
    }
    public Vector<Object> GetTaskCount1(){
        Vector<Object> objects=new Vector<>();
        objects.add(count);
        objects.add(people_haven);
        objects.add(people_needed);
        return objects;
    }
    public Vector<Object> GetTaskCount2(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(actilityLoaction.GetTaskCount());
        objects.add(count);
        objects.add(people_haven);
        objects.add(people_needed);
        return objects;
    }
    public Vector<Object> GetTnums(){
        Vector<Object> objects=new Vector<>();
        objects.add(tnum);
        objects.add(assignment);
        return objects;
    }
}
