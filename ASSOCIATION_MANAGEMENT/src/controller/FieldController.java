package controller;

import entity.ActilityLoaction;
import entity.Association;
import entity.JDBCDemo;
import entity.Located;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FieldController {
    private int select;
    public static final int init_field=0;
    public static final int field_add =1;
    public static final int field_update_or_delete=2;
    public static final int field_request=3;
    public static final int field_approval=4;
    public static final int field_query=5;
    public static final int field_count=6;

    public FieldController() {

        select=init_field;
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_field:Init(associationController);break;
            case field_add: ProcessAdd(associationController);break;
            case field_update_or_delete:ProcessUpdateOrDelete(associationController);break;
            case field_request:ProcessRequest(associationController);break;
            case field_approval:ProcessApproval(associationController);break;
            case field_query:ProcessQuery(associationController);break;
            case field_count:ProcessCount(associationController);break;
        }
    }

    public void setSelect(int select) {
        this.select = select;
    }

    private void Init(AssociationController associationController){
        associationController.getInitDemo().InitField(associationController);
    }
    private void ProcessAdd(AssociationController associationController){
        associationController.getInitDemo().FieldAdd(associationController);
    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        GetActilityLoactions(associationController,"");
        associationController.getInitDemo().FieldUpdateOrDelete(associationController);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        GetActilityLoactions(associationController,"");
        GetAcnos(associationController,"");
        associationController.getInitDemo().FieldRequest(associationController);
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
        GetFieldsUseInformation(associationController,"申请中","");
        associationController.getInitDemo().FieldApproval(associationController);
    }
    private void ProcessQuery(AssociationController associationController) {
        associationController.getInitDemo().FieldUseQuery(associationController);
    }
    private void ProcessCount(AssociationController associationController){
        associationController.getInitDemo().FieldUseCount(associationController);
    }
    public int FieldAdd(AssociationController associationController,String address) throws SQLException {

        int tno=associationController.getUser().getId();
        if(!JDBCDemo.IsTeacher(tno)){
            System.out.println("you are no a teacher");
            return -1;
        }
        //return JDBCDemo.FieldRequest(actilityLoaction);
        ActilityLoaction actilityLoaction=new ActilityLoaction();
        actilityLoaction.setAddress(address);
        return JDBCDemo.FieldAdd(actilityLoaction);

    }
    public int FieldUpdateOrDelete(AssociationController associationController,int lno,String address,String select) throws SQLException {
        int tno=associationController.getUser().getId();
        if(JDBCDemo.IsTeacher(tno)){
            switch (select){
                case "更新":return FieldUpdate(lno,address);
                case "删除":return FieldDelete(lno);
            }
            return 0;
        }
        else{
            System.out.println("you are no a teacher");
            return -1;
        }
    }
    public int FieldUpdate(int lno,String address) throws SQLException {
        ActilityLoaction actilityLoaction=new ActilityLoaction();
        actilityLoaction.setLno(lno);
        actilityLoaction.setAddress(address);
        return JDBCDemo.FieldUpdate(actilityLoaction);
    }
    public int FieldDelete(int lno) throws SQLException {
        return JDBCDemo.FieldDelete(lno);
    }
    public void GetActilityLoactions(AssociationController associationController,String name) throws SQLException {
        List<ActilityLoaction> actilityLoactions=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetActilityLoactions(actilityLoactions,name);
        for(ActilityLoaction actilityLoaction:actilityLoactions){
            associationController.getData().add(actilityLoaction.GetFieldInformation());
        }
    }
    public void GetFieldsUseInformation(AssociationController associationController,String state,String name) throws SQLException {
        List<Located> locateds=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetFieldsUseInformation(locateds,state,name);
        for(Located located:locateds){
            associationController.getData().add(located.GetLocatedInformation());
        }
    }
    public int FieldRequest(AssociationController associationController, int asno, int acno, int lno, Date date) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            Located located=new Located();
            located.getAssociation().setAsno(asno);
            located.getAssociation().getAcilitiy().setAcno(acno);
            located.getActilityLoaction().setLno(lno);
            located.setUse_date(date);
            return JDBCDemo.FieldRequest(located);
        }
        else{
            System.out.println("you are no association member");
            return -1;
        }

    }
    public int FieldCancel(AssociationController associationController, int asno, int acno, int lno,Date date) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            return JDBCDemo.FieldDelete(asno,acno,lno,date);
        }
        else{
            System.out.println("you are no association member");
            return -1;
        }
    }
    public int FieldApproval(AssociationController associationController, int asno, int acno, int lno, Date date) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            return JDBCDemo.FieldApproval(asno,acno,lno,date);
        }
        else{
            System.out.println("you are no management teacher");
            return -1;
        }
    }
    public int FieldRefuse(AssociationController associationController, int asno, int acno, int lno, Date date) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            return JDBCDemo.FieldDelete(asno,acno,lno,date);
        }
        else{
            System.out.println("you are no management teacher");
            return -1;
        }
    }
    public void GetFieldsUseInformation(AssociationController associationController,String state, String name, Date date1, Date date2) throws SQLException {
        List<Located> locateds=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetFieldsUseInformation(locateds,state,name,date1,date2);
        for(Located located:locateds){
            associationController.getData().add(located.GetLocatedInformation());
        }
    }
    public void FieldUseCount(AssociationController associationController) throws SQLException {
        Located located=new Located();
        associationController.getData().clear();
        JDBCDemo.FieldUseCount(located);
        associationController.getData().add(located.GetFieldUseCount());
    }
    public void FieldUseCount1(AssociationController associationController,String name) throws SQLException {
        List<Located> locateds=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.FieldUseCount1(locateds,name);
        for(Located located:locateds){
            associationController.getData().add(located.GetFieldUseCount1());
        }
    }
    public void FieldUseCount2(AssociationController associationController,String name) throws SQLException {
        List<Located> locateds=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.FieldUseCount2(locateds,name);
        for(Located located:locateds){
            associationController.getData().add(located.GetFieldUseCount2());
        }
    }
    public void FieldUseCount3(AssociationController associationController,String name) throws SQLException {
        List<Located> locateds=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.FieldUseCount3(locateds,name);
        for(Located located:locateds){
            associationController.getData().add(located.GetFieldUseCount3());
        }
    }
    public void GetAcnos(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData2().clear();
        JDBCDemo.GetAcnos(associations,name,"已通过");
        for(Association association:associations){
            associationController.getData2().add(association.GetAcnos());
        }
    }
}
