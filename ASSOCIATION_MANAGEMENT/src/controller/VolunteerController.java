package controller;

import entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolunteerController {
    private int select;
    public static final int init_volunteer=0;
    public static final int task_add =1;
    public static final int task_update_or_delete=2;
    public static final int volunteer_request=3;
    public static final int volunteer_query=4;
    public static final int task_count=5;

    public VolunteerController() {
        select=init_volunteer;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_volunteer:Init(associationController);break;
            case task_add: ProcessAdd(associationController);break;
            case task_update_or_delete:ProcessUpdateOrDelete(associationController);break;
            case volunteer_request:ProcessRequest(associationController);break;
            case volunteer_query:ProcessQuery(associationController);break;
            case task_count:ProcessCount(associationController);break;
        }
    }

    private void ProcessCount(AssociationController associationController){
        associationController.getInitDemo().TaskCount(associationController);
    }

    private void ProcessQuery(AssociationController associationController) {
        associationController.getInitDemo().VolunteerQuery(associationController);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        GetTasks(associationController,"");
        associationController.getInitDemo().VolunteerRequest(associationController);
    }

    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        GetFieldsUseInformation(associationController,"");
        GetTnums(associationController,"");
        associationController.getInitDemo().TaskUpdate(associationController);
    }

    private void ProcessAdd(AssociationController associationController) throws SQLException {
        GetFieldsUseInformation(associationController,"");
        associationController.getInitDemo().TaskAdd(associationController);
    }

    private void Init(AssociationController associationController) {
        associationController.getInitDemo().InitVolunteer(associationController);
    }

    public int TaskAdd(AssociationController associationController, int asno, int acno, int duration, int people_needed, String assignment, Date date,int lno) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            Association association=new Association();
            association.setAsno(asno);
            association.getAcilitiy().setAcno(acno);
            association.getAcilitiy().getTask().setDuration(duration);
            association.getAcilitiy().getTask().setPeople_needed(people_needed);
            association.getAcilitiy().getTask().setAssignment(assignment);
            association.getAcilitiy().getTask().getActilityLoaction().setLno(lno);
            association.getAcilitiy().getTask().setVolunteer_date(date);
            return JDBCDemo.TaskAdd(association);
        }
        else {
            System.out.println("you are no the member of association");
            return -1;
        }
    }
    public int TaskUpdate(AssociationController associationController,int asno, int acno,int tnum,int duration, int people_needed, String assignment, Date date,int lno) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            Association association=JDBCDemo.GetTask(asno,acno,tnum);
            if(association==null){
                return 0;
            }
            association.setAsno(asno);
            association.getAcilitiy().setAcno(acno);
            association.getAcilitiy().getTask().setTnum(tnum);
            if(duration!=-1){
                association.getAcilitiy().getTask().setDuration(duration);
            }
            if(people_needed!=-1){
                association.getAcilitiy().getTask().setPeople_needed(people_needed);
            }
            if(!assignment.equals("")){
                association.getAcilitiy().getTask().setAssignment(assignment);
            }
            if(date!=null){
                association.getAcilitiy().getTask().setVolunteer_date(date);
            }
            if(lno!=-1){
                association.getAcilitiy().getTask().getActilityLoaction().setLno(lno);
            }
            return JDBCDemo.TaskUpdate(association);
        }
        else{
            System.out.println("you are no the member of association");
            return -1;
        }
    }
    public int TaskDelete(AssociationController associationController,int asno, int acno,int tnum) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            return JDBCDemo.TaskDelete(asno,acno,tnum);
        }
        else{
            System.out.println("you are no the member of association");
            return -1;
        }
    }
    public int VolunteerAdd(AssociationController associationController,int asno,int acno,int tnum) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            VolunteerFor volunteerFor=new VolunteerFor();
            volunteerFor.getAssociation().setAsno(asno);
            volunteerFor.getStudent().setId(sno);
            volunteerFor.getAssociation().getAcilitiy().setAcno(acno);
            volunteerFor.getAssociation().getAcilitiy().getTask().setTnum(tnum);
            return JDBCDemo.VolunteerAdd(volunteerFor);
        }
        else{
            System.out.println("you are no the member of association");
            return -1;
        }
    }
    public int VolunteerDelete(AssociationController associationController,int asno,int acno,int tnum) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            return JDBCDemo.VolunteerDelete(asno,sno,acno,tnum);
        }
        else{
            System.out.println("you are no the member of association");
            return -1;
        }
    }
    public void GetTasks(AssociationController associationController,String name) throws SQLException {
        associationController.getData().clear();
        List<Association> associations=new ArrayList<>();
        JDBCDemo.GetTasks(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetTaskInformation());
        }
    }
    public void GetVolunteers(AssociationController associationController,String name) throws SQLException {
        associationController.getData().clear();
        List<VolunteerFor> volunteerFors=new ArrayList<>();
        JDBCDemo.GetVolunteers(volunteerFors,name);
        for(VolunteerFor volunteerFor:volunteerFors){
            associationController.getData().add(volunteerFor.GetVolunteerInformation());
        }
    }
    public void GetVolunteers(AssociationController associationController,String name,Date date1,Date date2) throws SQLException {
        associationController.getData().clear();
        List<VolunteerFor> volunteerFors=new ArrayList<>();
        JDBCDemo.GetVolunteers(volunteerFors,name,date1,date2);
        for(VolunteerFor volunteerFor:volunteerFors){
            associationController.getData().add(volunteerFor.GetVolunteerInformation());
        }
    }
    public void TaskCount(AssociationController associationController) throws SQLException {
        Task task=new Task();
        associationController.getData().clear();
        JDBCDemo.TaskCount(task);
        associationController.getData().add(task.GetTaskCount1());
    }
    public void TaskCount1(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.TaskCount1(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetTaskCount1());
        }
    }
    public void TaskCount2(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.TaskCount2(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetTaskCount2());
        }
    }
    public void TaskCount3(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.TaskCount3(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetTaskCount3());
        }
    }
    public void GetFieldsUseInformation(AssociationController associationController,String name) throws SQLException {
        List<Located> locateds=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetFieldsUseInformation(locateds,"已通过",name);
        for(Located located:locateds){
            associationController.getData().add(located.GetLocatedInformation());
        }
    }
    public void GetTnums(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData2().clear();
        JDBCDemo.GetTnums(associations,name);
        for(Association association:associations){
            associationController.getData2().add(association.GetTnums());
        }
    }
}
