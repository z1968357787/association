package entity;


import java.sql.Date;
import java.util.Vector;

public class VoteEvents {
    private int eno;
    private String title;
    private Date ddl_date;
    private int max_poll;

    private VoteOptions voteOptions=new VoteOptions();

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDdl_date() {
        return ddl_date;
    }

    public void setDdl_date(Date ddl_date) {
        this.ddl_date = ddl_date;
    }

    public int getMax_poll() {
        return max_poll;
    }

    public void setMax_poll(int max_poll) {
        this.max_poll = max_poll;
    }

    public VoteOptions getVoteOptions() {
        return voteOptions;
    }
    public Vector<Object> GetEventsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(eno);
        objects.add(title);
        objects.add(max_poll);
        objects.add(ddl_date);
        return objects;
    }
    public Vector<Object> GetOptionsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(eno);
        objects.add(title);
        objects.addAll(voteOptions.GetOptionsInformation());
        objects.add(max_poll);
        objects.add(ddl_date);
        return objects;
    }
    public Vector<Object> GetVoteInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(title);
        objects.addAll(voteOptions.GetVoteInformation());
        return objects;
    }
    public Vector<Object> GetOnos(){
        Vector<Object> objects=new Vector<>();
        objects.add(eno);
        objects.add(title);
        objects.addAll(voteOptions.GetOnos());
        return objects;
    }
}
