package entity;

import java.util.Vector;

public class VoteOptions {
    private int ono;
    private String content;

    public int getOno() {
        return ono;
    }

    public void setOno(int ono) {
        this.ono = ono;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Vector<Object> GetOptionsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(ono);
        objects.add(content);
        return objects;
    }
    public Vector<Object> GetVoteInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(content);
        return objects;
    }
    public Vector<Object> GetOnos(){
        Vector<Object> objects=new Vector<>();
        objects.add(ono);
        objects.add(content);
        return objects;
    }
}
