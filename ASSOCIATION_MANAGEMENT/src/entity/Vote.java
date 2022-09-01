package entity;

import java.sql.Date;
import java.util.Vector;

public class Vote {

    private Student student=new Student();
    private Association association=new Association();
    private int poll;
    private Date vote_date;

    public Student getStudent() {
        return student;
    }

    public Association getAssociation() {
        return association;
    }

    public int getPoll() {
        return poll;
    }

    public void setPoll(int poll) {
        this.poll = poll;
    }

    public Date getVote_date() {
        return vote_date;
    }

    public void setVote_date(Date vote_date) {
        this.vote_date = vote_date;
    }

    public Vector<Object> GetVoteInformation1(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetVoteInformation());
        objects.addAll(student.GetVoteInformation());
        objects.add(poll);
        objects.add(vote_date);
        return objects;
    }
    public Vector<Object> GetVoteInformation2(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetVoteInformation());
        objects.add(poll);
        return objects;
    }
}
