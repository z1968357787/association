package entity;


import java.sql.Date;
import java.util.Vector;

public class Questions {
    private int qno;
    private String content;
    private Date ddl_date;

    public int getQno() {
        return qno;
    }

    public void setQno(int qno) {
        this.qno = qno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDdl_date() {
        return ddl_date;
    }

    public void setDdl_date(Date ddl_date) {
        this.ddl_date = ddl_date;
    }

    public Vector<Object> GetQuestionsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(qno);
        objects.add(content);
        objects.add(ddl_date);
        return objects;
    }
    public Vector<Object> GetInvestigationsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(content);
        return objects;
    }
}
