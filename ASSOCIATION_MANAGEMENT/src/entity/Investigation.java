package entity;

import java.sql.Date;
import java.util.Vector;

public class Investigation {
    private Association association=new Association();
    private Student student=new Student();
    private String answer;
    private Date answer_date;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAnswer_date(Date answer_date) {
        this.answer_date = answer_date;
    }

    public Association getAssociation() {
        return association;
    }

    public Student getStudent() {
        return student;
    }

    public Date getAnswer_date() {
        return answer_date;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Vector<Object> GetInvestigationsInformation1(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetInvestigationsInformation());
        objects.addAll(student.GetInvestigationsInformation());
        objects.add(answer);
        objects.add(answer_date);
        return objects;
    }
    public Vector<Object> GetInvestigationsInformation2(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(association.GetInvestigationsInformation());
        objects.add(count);
        return objects;
    }
}
