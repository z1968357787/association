package controller;

import entity.Association;
import entity.JDBCDemo;
import entity.Vote;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteController {
    private int select;
    public static final int init_vote=0;
    public static final int events_add_or_delete=1;
    public static final int options_add_or_delete=2;
    public static final int vote_for_options=3;
    public static final int vote_query=4;
    public VoteController() {
        select=init_vote;
    }
    public void setSelect(int select) {
        this.select = select;
    }
    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_vote:Init(associationController);break;
            case events_add_or_delete:ProcessEvents(associationController);break;
            case options_add_or_delete:ProcessOptions(associationController);break;
            case vote_for_options:ProcessVote(associationController);break;
            case vote_query:ProcessQuery(associationController);break;
        }
    }

    private void ProcessQuery(AssociationController associationController) {
        associationController.getInitDemo().VoteQuery(associationController);
    }

    private void ProcessVote(AssociationController associationController) throws SQLException {
        GetOptions(associationController,"");
        associationController.getInitDemo().VoteForOptions(associationController);
    }

    private void ProcessOptions(AssociationController associationController) throws SQLException {
        GetOnos(associationController,"");
        GetEvents(associationController,"");
        associationController.getInitDemo().OptionsAddOrDelete(associationController);
    }

    private void ProcessEvents(AssociationController associationController) throws SQLException {
        GetEvents(associationController,"");
        GetAsnos(associationController,"");
        associationController.getInitDemo().EventsAddOrDelete(associationController);
    }

    private void Init(AssociationController associationController) {
        associationController.getInitDemo().InitVote(associationController);
    }

    public int EventAdd(AssociationController associationController,int asno, int max_poll, String title, Date date) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            Association association=new Association();
            association.setAsno(asno);
            association.getVoteEvents().setMax_poll(max_poll);
            association.getVoteEvents().setTitle(title);
            association.getVoteEvents().setDdl_date(date);
            return JDBCDemo.EventAdd(association);
        }
        else{
            System.out.println("you are no association leader");
            return -1;
        }
    }

    public int EventDelete(AssociationController associationController,int asno,int eno) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            return JDBCDemo.EventDelete(asno,eno);
        }
        else{
            System.out.println("you are no association leader");
            return -1;
        }
    }
    public int OptionAdd(AssociationController associationController,int asno, int eno,String content) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            Association association=new Association();
            association.setAsno(asno);
            association.getVoteEvents().setEno(eno);
            association.getVoteEvents().getVoteOptions().setContent(content);
            return JDBCDemo.OptionAdd(association);
        }
        else{
            System.out.println("you are no association leader");
            return -1;
        }
    }
    public int OptionDelete(AssociationController associationController,int asno,int eno,int ono) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            return JDBCDemo.OptionDelete(asno,eno,ono);
        }
        else{
            System.out.println("you are no association leader");
            return -1;
        }
    }
    public void GetEvents(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetEvents(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetEventsInformation());
        }
    }
    public int VoteAdd(AssociationController associationController,int asno,int eno,int ono,int poll,Date vote_date) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            Vote vote=new Vote();
            vote.getAssociation().setAsno(asno);
            vote.getStudent().setId(sno);
            vote.getAssociation().getVoteEvents().setEno(eno);
            vote.getAssociation().getVoteEvents().getVoteOptions().setOno(ono);
            vote.setPoll(poll);
            vote.setVote_date(vote_date);
            return JDBCDemo.VoteAdd(vote);
        }
        else{
            System.out.println("you are no the member of the association");
            return 0;
        }
    }
    /*public int VoteUpdate(AssociationController associationController,int asno,int eno,int ono,int poll) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            Vote vote=new Vote();
            vote.getAssociation().setAsno(asno);
            vote.getStudent().setId(sno);
            vote.getAssociation().getVoteEvents().setEno(eno);
            vote.getAssociation().getVoteEvents().getVoteOptions().setOno(ono);
            vote.setPoll(poll);
            return JDBCDemo.VoteUpdate(vote);
        }
        else{
            System.out.println("you are no the member of the association");
            return -1;
        }
    }*/
    public int VoteDelete(AssociationController associationController,int asno,int eno,int ono) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            return JDBCDemo.VoteDelete(asno,sno,eno,ono);
        }
        else{
            System.out.println("you are no the member of the association");
            return -1;
        }
    }
    public void GetOptions(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetOptions(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetOptionsInformation());
        }
    }
    public void GetVoteInformation(AssociationController associationController,String name) throws SQLException {
        List<Vote> votes=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetVotes(votes,name);
        for(Vote vote:votes){
            associationController.getData().add(vote.GetVoteInformation1());
        }
    }
    public void GetVoteInformation(AssociationController associationController,String name,String select) throws SQLException {
        List<Vote> votes=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetVotes(votes,name,select);
        for(Vote vote:votes){
            associationController.getData().add(vote.GetVoteInformation2());
        }
    }
    public void GetAsnos(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData2().clear();
        JDBCDemo.GetAsnos(associations,name,"已通过");
        for(Association association:associations){
            associationController.getData2().add(association.GetAsnos());
        }
    }
    public void GetOnos(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData2().clear();
        JDBCDemo.GetOnos(associations,name);
        for(Association association:associations){
            associationController.getData2().add(association.GetOnos());
        }
    }

}
