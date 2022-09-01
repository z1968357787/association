package controller;

import entity.Acility;
import entity.Association;
import entity.JDBCDemo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivilityController {
    private int select;
    public static final int init_activility=0;
    public static final int activility_request=1;
    public static final int activility_approval=2;
    public static final int activility_query=3;
    public static final int activility_update_or_delete=4;
    public static final int activility_count=5;

    public ActivilityController() {
        select=init_activility;
    }
    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_activility:Init(associationController);break;
            case activility_request:ProcessRequest(associationController);break;
            case activility_approval:ProcessApproval(associationController);break;
            case activility_query:ProcessQuery(associationController);break;
            case activility_update_or_delete:ProcessUpdateAndDelete(associationController);break;
            case activility_count:ProcessCount(associationController);break;
        }
    }

    private void ProcessCount(AssociationController associationController) {
        associationController.getInitDemo().ActivilityCount(associationController);
    }

    public void setSelect(int select) {
        this.select = select;
    }

    private void Init(AssociationController associationController){
        associationController.getInitDemo().InitActivility(associationController);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        GetAsnos(associationController,"");
        associationController.getInitDemo().ActivilityRequeset(associationController);
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
        GetActivilities(associationController,"申请中","");
        associationController.getInitDemo().ActivilityApproval(associationController);
    }
    private void ProcessQuery(AssociationController associationController) {
        associationController.getInitDemo().ActivilityQuery(associationController);
    }
    private void ProcessUpdateAndDelete(AssociationController associationController) throws SQLException {
        GetAcnos(associationController,"");
        associationController.getInitDemo().ActivilityUpdate(associationController);
    }

    public int ActivilityRequest(AssociationController associationController,int asno,String acname,String introduction) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        Association association=new Association();
        association.setAsno(asno);
        association.getAcilitiy().setAcname(acname);
        association.getAcilitiy().setIntroduction(introduction);
        //if(JDBCDemo.IsAssociationExists(association.getAsno())){
        if(JDBCDemo.IsAssociationLeader(association.getAsno(),mgn_sno)){
            return JDBCDemo.InsertActivility(association);
        }
        else{
            System.out.println("you are no the member of the association");
            return -1;
        }

    }

    public void GetActivilities(AssociationController associationController,String state,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetActivilities(associations,state,name);
        for(Association association:associations){
            associationController.getData().add(association.GetActivilityInformation());
        }
    }
    public int ActivilityApproval(AssociationController associationController, int asno, int acno, String approval_select) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(!JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            System.out.println("you are no the management teacher of the association");
            return -1;
        }
        if(approval_select.equals("已通过")){
            return JDBCDemo.ActivilityApproval(asno,acno);
        }
        else{
            return JDBCDemo.DeleteActivility(asno,acno);
        }
    }
    public int ProcessUpdate(int asno,int acno,String name, String introduction,AssociationController associationController) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)) {
            Association association=JDBCDemo.GetActivility(asno,acno);
            if(association==null){
                return 0;
            }
            if(!name.equals("")){
                association.getAcilitiy().setAcname(name);
            }
            if(!introduction.equals("")){
                association.getAcilitiy().setIntroduction(introduction);
            }
            return JDBCDemo.UpdateActivility(association);
        }
        else{
            System.out.println("you are no the member of association");
            return -1;
        }

    }
    public int ProcessDelete(int asno,int acno,AssociationController associationController) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            return JDBCDemo.DeleteActivility(asno,acno);
        }
        else{

            System.out.println("you are no the member of association");
            return -1;
        }
    }
    public void ActivlityCount(AssociationController associationController) throws SQLException {
        Acility acility=new Acility();
        associationController.getData().clear();
        JDBCDemo.ActivilityCount(acility);
        associationController.getData().add(acility.GetActivilityCount1());
    }
    public void ActivlityCount1(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.ActiviltyCount1(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetActivilityCount1());
        }
    }
    public void ActivlityCount2(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.ActiviltyCount2(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetActivilityCount2());
        }
    }
    public void GetAsnos(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetAsnos(associations,name,"已通过");
        for(Association association:associations){
            associationController.getData().add(association.GetAsnos());
        }
    }
    public void GetAcnos(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetAcnos(associations,name,"");
        for(Association association:associations){
            associationController.getData().add(association.GetAcnos());
        }
    }
}
