package entity;

import java.sql.Date;

import java.util.Vector;


public class Association {

    private int asno;
    private String asname;
    private String state;
    private Date create_date;
    private String introduction;
    private int mgn_tno;
    private int mgn_sno;
    private String mgn_tname;
    private String mgn_sname;
    private int count;

    /*
    * 实体集
    * */
    private Acility acilitiy=new Acility();
    private Finance finance=new Finance();
    private VoteEvents voteEvents =new VoteEvents();
    private Questions questions=new Questions();

     /*
     * 联系集
     * */

    public Questions getQuestions() {
        return questions;
    }

    public VoteEvents getVoteEvents() {
        return voteEvents;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getAsno() {
        return asno;
    }

    public void setAsno(int asno) {
        this.asno = asno;
    }

    public String getAsname() {
        return asname;
    }

    public void setAsname(String asname) {
        this.asname = asname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Acility getAcilitiy() {
        return acilitiy;
    }

    public Finance getFinance() {
        return finance;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getMgn_tno() {
        return mgn_tno;
    }

    public void setMgn_tno(int mgn_tno) {
        this.mgn_tno = mgn_tno;
    }

    public int getMgn_sno() {
        return mgn_sno;
    }

    public void setMgn_sno(int mgn_sno) {
        this.mgn_sno = mgn_sno;
    }

    public String getMgn_tname() {
        return mgn_tname;
    }

    public void setMgn_tname(String mgn_tname) {
        this.mgn_tname = mgn_tname;
    }

    public String getMgn_sname() {
        return mgn_sname;
    }

    public void setMgn_sname(String mgn_sname) {
        this.mgn_sname = mgn_sname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Vector<Object> GetMembersInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asname);
        return objects;
    }
    public Vector<Object> GetAsnos(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        return objects;
    }
    public Vector<Object> GetAcnos(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(GetAsnos());
        objects.addAll(acilitiy.GetAcnos());
        return objects;
    }
    public Vector<Object> GetTnums(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(GetAsnos());
        objects.addAll(acilitiy.GetTnums());
        return objects;
    }
    public Vector<Object> GetOnos(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(GetAsnos());
        objects.addAll(voteEvents.GetOnos());
        return objects;
    }
    public Vector<Object> GetMembersRequest(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        return objects;
    }

    public Vector<Object> GetVolunteerInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asname);
        objects.addAll(getAcilitiy().GetVolunteerInformation());
        return objects;
    }

    public Vector<Object> GetAssociationInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.add(state);
        objects.add(create_date);
        objects.add(introduction);
        objects.add(mgn_tname);
        objects.add(mgn_sname);
        return objects;
    }
    public Vector<Object> GetActivilityInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetActivilityInformation());

        return objects;
    }
    public Vector<Object> GetFinanceInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(finance.GetFinanceInformation());
        return objects;
    }
    public Vector<Object> GetFinanceInformation2(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(finance.GetFinanceInformation2());
        return objects;
    }
    public Vector<Object> GetTaskInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetTaskInformation());
        return objects;
    }
    public Vector<Object> GetLocatedInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetLocatedInformation());
        return objects;
    }
    public Vector<Object> GetEventsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(voteEvents.GetEventsInformation());
        return objects;
    }
    public Vector<Object> GetOptionsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(voteEvents.GetOptionsInformation());
        return objects;
    }
    public Vector<Object> GetVoteInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asname);
        objects.addAll(voteEvents.GetVoteInformation());
        return objects;
    }
    public Vector<Object> GetQuestionsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(questions.GetQuestionsInformation());
        return objects;
    }
    public Vector<Object> GetInvestigationsInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asname);
        objects.addAll(questions.GetInvestigationsInformation());
        return objects;
    }
    public Vector<Object> GetBBSInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        return objects;
    }
    public Vector<Object> GetAssociationCount1(){
        Vector<Object> objects=new Vector<>();
        objects.add(mgn_sname);
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetAssociationCount2(){
        Vector<Object> objects=new Vector<>();
        objects.add(mgn_tname);
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetAssociationCount3(){
        Vector<Object> objects=new Vector<>();
        objects.add(state);
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetAssociationCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(count);
        return objects;
    }
    public Vector<Object> GetMembersCount(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        return objects;
    }

    public Vector<Object> GetActivilityCount1(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetActivilityCount1());
        return objects;
    }
    public Vector<Object> GetActivilityCount2(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetActivilityCount2());
        return objects;
    }
    public Vector<Object> GetFieldUseCount1(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        return objects;
    }
    public Vector<Object> GetFieldUseCount2(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetFieldUseCount());
        return objects;
    }
    public Vector<Object> GetTaskCount1(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetTaskCount1());
        return objects;
    }
    public Vector<Object> GetTaskCount2(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetTaskCount2());
        return objects;
    }
    public Vector<Object> GetTaskCount3(){
        Vector<Object> objects=new Vector<>();
        objects.add(asno);
        objects.add(asname);
        objects.addAll(acilitiy.GetTaskCount3());
        return objects;
    }

}
