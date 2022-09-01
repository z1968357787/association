package controller;

import entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberController {
    private int select;
    public static final int init_member=0;
    public static final int member_request=1;
    public static final int member_approval=2;
    public static final int member_query=3;
    public static final int member_count=4;
    public static final int member_update=5;

    public MemberController() {
        select=init_member;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_member:Init(associationController);break;
            case member_request:ProcessRequest(associationController);break;
            case member_approval:ProcessApproval(associationController);break;
            case member_query:ProcessQuery(associationController);break;
            case member_count:ProcessCount(associationController);break;
            case member_update:ProcessUpdate(associationController);break;
        }
    }

    private void Init(AssociationController associationController){
        associationController.getInitDemo().InitMember(associationController);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        GetAssociations(associationController);
        associationController.getInitDemo().MemberRequest(associationController);
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
        GetMemberRequest(associationController,"");
        associationController.getInitDemo().MemberApproval(associationController);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        associationController.getInitDemo().MemberQuery(associationController);
    }

    public void GetAssociations(AssociationController associationController) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetAssociations(associations,"","已通过");
        for(Association association:associations){
            associationController.getData().add(association.GetAssociationInformation());
        }
    }
    public void ProcessCount(AssociationController associationController){
        associationController.getInitDemo().MemberCount(associationController);
    }
    public void ProcessUpdate(AssociationController associationController){
        associationController.getInitDemo().MenberUpdate(associationController);
    }

    public void GetMembersInformation(AssociationController associationController,String name) throws SQLException {
        List<Participate> participates=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetMembersInformation(participates,name);
        for(Participate participate:participates){
            associationController.getData().add(participate.GetMembersInformation());
        }
    }


    public int MemberRequest(AssociationController associationController, int asno, String request_select) throws SQLException {
        int sno=associationController.getUser().getId();
        if(request_select.equals("加入")){
            if(!(JDBCDemo.IsAssociationMember(asno,sno,"已通过")||JDBCDemo.IsAssociationMember(asno,sno,"申请中"))){
                Participate participate=new Participate();
                participate.getAssociation().setAsno(asno);
                participate.getStudent().setId(sno);
                System.out.println("insert ok");
                return JDBCDemo.InsertMember(participate);
            }
            else{
                System.out.println("you have in the association");
                return -1;
            }
        }
        else{
            if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")||JDBCDemo.IsAssociationMember(asno,sno,"申请中")){
                System.out.println("delete ok");
                return JDBCDemo.DeleteMember(asno,sno);
            }
            else{
                System.out.println("you are no in the association");
                return -2;
            }
        }
    }


    public void GetMemberRequest(AssociationController associationController,String name) throws SQLException {
        List<Participate> participates=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetRequestMembers(participates,name);
        for(Participate participate:participates){
            associationController.getData().add(participate.GetMembersRequest());
        }
    }
    public int ProcessMembersUpdate(AssociationController associationController,int asno,int sno,String approval_select) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(!JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            System.out.println("you are no the leader of the association");
            return -1;
        }
        if(approval_select.equals("已通过")){
            return JDBCDemo.UpdateMemberState(asno,sno);
        }
        else{
            return JDBCDemo.DeleteMember(asno,sno);
        }
    }

    public int MemberUpdate(AssociationController associationController,int id,String name,String department,double credit_or_salary) throws SQLException {
        Users users=associationController.getUser();
        if(id!=-1){
            users.setId(id);
        }
        if(!name.equals("")){
            users.setName(name);
        }
        if(!department.equals("")){
            users.setDepartment(department);
        }
        if(credit_or_salary!=-1){
            users.setCreait_or_Salary(credit_or_salary);
        }
        int count=JDBCDemo.MemberUpdate(users);
        associationController.setUser(users);
        return count;

    }
    public void MemberCount(AssociationController associationController) throws SQLException {
        Participate participate=new Participate();
        associationController.getData().clear();
        JDBCDemo.MemberCount(participate);
        associationController.getData().add(participate.GetMembersCount());
    }
    public void MemberCount1(AssociationController associationController,String name) throws SQLException {
        List<Participate> participates=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.MemberCount1(participates,name);
        for(Participate participate:participates){
            associationController.getData().add(participate.GetMembersCount1());
        }
    }
    public void MemberCount2(AssociationController associationController,String name) throws SQLException {
        List<Participate> participates=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.MemberCount2(participates,name);
        for(Participate participate:participates){
            associationController.getData().add(participate.GetMembersCount2());
        }
    }
    public void MemberCount3(AssociationController associationController,String name) throws SQLException {
        List<Participate> participates=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.MemberCount3(participates,name);
        for(Participate participate:participates){
            associationController.getData().add(participate.GetMembersCount3());
        }
    }
}
