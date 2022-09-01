package controller;

import entity.Association;
import entity.JDBCDemo;
import entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssociationManagementController {

    private int select;
    public static final int init_association=0;
    public static final int association_request=1;
    public static final int process_approval=2;
    public static final int process_query=3;
    public static final int process_count=4;
    public static final int process_update_and_delete=5;

    public AssociationManagementController() {
        select=init_association;
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_association:Init(associationController);break;
            case association_request: ProcessRequest(associationController);break;
            case process_approval:ProcessApproval(associationController);break;
            case process_query:ProcessQuery(associationController);break;
            case process_update_and_delete:ProcessUpdateAndDelete(associationController);break;
            case process_count:ProcessCount(associationController);break;
        }
    }

    private void Init(AssociationController associationController){
        associationController.getInitDemo().InitAssociation(associationController);
    }

    private void ProcessRequest(AssociationController associationController) throws SQLException {
        GetTeacher(associationController,"");
        associationController.getInitDemo().AssociationRequest(associationController);
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().GetAssociations(associationController,"","申请中");
        associationController.getInitDemo().AssociationApproval(associationController);
    }
    private void ProcessQuery(AssociationController associationController){
        associationController.getInitDemo().AssociationQuery(associationController);
    }
    private void ProcessUpdateAndDelete(AssociationController associationController) throws SQLException {
        GetTeacher(associationController,"");
        GetAsnos(associationController,"");
        associationController.getInitDemo().AssociationProcessUpdateAndDelete(associationController);
    }
    private void ProcessCount(AssociationController associationController){
        associationController.getInitDemo().AssociationCount(associationController);
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int AssociationRequest(AssociationController associationController,String name,String introduction,int mgn_tno) throws SQLException {
        Association association=new Association();
        association.setAsname(name);
        association.setIntroduction(introduction);
        association.setMgn_sno(associationController.getUser().getId());
        association.setMgn_tno(mgn_tno);
        return JDBCDemo.AssociationRequest(association);
    }
    public void GetTeacher(AssociationController associationController,String name) throws SQLException {
        List<Teacher> teachers=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetTeachers(teachers,name);
        for(Teacher teacher:teachers){
            associationController.getData().add(teacher.GetTeachersInformation());
        }
    }
    public int UpdateApproval(AssociationController associationController,int asno,String state) throws SQLException {
        int tno=associationController.getUser().getId();
        if(JDBCDemo.IsTeacher(tno)){
            if(state.equals("已通过")){
                System.out.println("??????");
                return JDBCDemo.UpdateApproval(asno,state);
            }
            else{
                return JDBCDemo.AssociationDelete(asno);
            }
        }
        else{
            return -1;
        }
    }

    public void GetAssociations(AssociationController associationController, String name, String state) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetAssociations(associations,name,state);
        for(Association association:associations){
            associationController.getData().add(association.GetAssociationInformation());
        }
        System.out.println(associationController.getData().size());
    }

    public boolean IsAssociationExists(int asno) throws SQLException {
        if(JDBCDemo.IsAssociationExists(asno)){
            return true;
        }
        else{
            return false;
        }
    }

    public int AssociationUpadte(AssociationController associationController,int asno,String name,String introduction,int mgn_tno) throws SQLException {
        int tno=associationController.getUser().getId();
        Association association=JDBCDemo.GetAssociation(asno);
        if(!name.equals("")){
            association.setAsname(name);
        }
        if(!introduction.equals("")){
            association.setIntroduction(introduction);
        }
        if(mgn_tno!=-1){
            association.setMgn_tno(mgn_tno);
        }
        if(JDBCDemo.IsManagementTeacher(asno,tno)){
            return JDBCDemo.AssociationUpadte(association);
        }
        else{
            System.out.println("you are no the association leader");
            return -1;
        }

    }

    public int AssociationDelete(AssociationController associationController,int asno) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            return JDBCDemo.AssociationDelete(asno);
        }
        else{
            System.out.println("you are no the association leader");
            return -1;
        }
    }
    public void AssociationCount(AssociationController associationController) throws SQLException {
        Association association=new Association();
        associationController.getData().clear();
        JDBCDemo.AssociationCount(association);
        associationController.getData().add(association.GetAssociationCount());
    }
    public void AssociationCount1(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.AssociationCount1(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetAssociationCount1());
        }
    }
    public void AssociationCount2(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.AssociationCount2(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetAssociationCount2());
        }
    }
    public void AssociationCount3(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.AssociationCount3(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetAssociationCount3());
        }
    }
    public void GetAsnos(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData2().clear();
        JDBCDemo.GetAsnos(associations,name,"");
        for(Association association:associations){
            associationController.getData2().add(association.GetAsnos());
        }
    }

}
