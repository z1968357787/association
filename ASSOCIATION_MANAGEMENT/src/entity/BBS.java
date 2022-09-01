package entity;


import java.sql.Date;
import java.util.Vector;

public class BBS {
    private int bno;
    private String content;
    private Date speak_date;

    private Association association=new Association();
    private Student student=new Student();

    public Association getAssociation() {
        return association;
    }

    public Student getStudent() {
        return student;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSpeak_date() {
        return speak_date;
    }

    public void setSpeak_date(Date speak_date) {
        this.speak_date = speak_date;
    }

    public Vector<Object> GetBBSInformation(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetBBSInformation());
        objects.addAll(student.GetBBSInformation());
        objects.add(bno);
        objects.add(content);
        objects.add(speak_date);
        return objects;
    }
}
