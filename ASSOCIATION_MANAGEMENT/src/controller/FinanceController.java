package controller;

import entity.Association;
import entity.JDBCDemo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanceController {
    private int select;
    public static final int init_finance=0;
    public static final int finance_add =1;
    public static final int finance_update_or_delete=2;
    public static final int finance_query=3;
    public FinanceController() {
        select=init_finance;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_finance:Init(associationController);break;
            case finance_add: ProcessAdd(associationController);break;
            case finance_update_or_delete:ProcessUpdateOrDelete(associationController);break;
            case finance_query: ProcessQuery(associationController);break;
        }
    }

    private void ProcessQuery(AssociationController associationController) {
        associationController.getInitDemo().FinanceQuery(associationController);
    }

    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        FinanceQuery1(associationController,1,"");
        associationController.getInitDemo().FinanceUpdate(associationController);
    }

    private void ProcessAdd(AssociationController associationController) throws SQLException {
        GetAsnos(associationController,"");
        associationController.getInitDemo().FinanceAdd(associationController);
    }

    private void Init(AssociationController associationController) {
        associationController.getInitDemo().InitFinance(associationController);
    }

    public int FinanceAdd(AssociationController associationController, int asno, double money, String reason, Date date) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        Association association=new Association();
        association.setAsno(asno);
        association.getFinance().setMoney(money);
        association.getFinance().setReason(reason);
        association.getFinance().setRecord_date(date);
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            return JDBCDemo.FinanceAdd(association);
        }
        else{
            System.out.println("you are no management teacher");
            return -1;
        }
    }
    public int FinanceUpdate(int asno,int fno,double money,String reason,Date date,AssociationController associationController) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            Association association=JDBCDemo.GetFinance(asno,fno);
            if(association==null){
                return 0;
            }
            if(money!=0){
                association.getFinance().setMoney(money);
            }
            if(!reason.equals("")){
                association.getFinance().setReason(reason);
            }
            if(date!=null){
                association.getFinance().setRecord_date(date);
            }
            return JDBCDemo.FinanceUpdate(association);
        }
        else{
            System.out.println("you are no management teacher");
            return -1;
        }
    }
    public int FinanceDelete(int asno,int fno,AssociationController associationController) throws SQLException {
        int mgn_tno=associationController.getUser().getId();
        if(JDBCDemo.IsManagementTeacher(asno,mgn_tno)){
            return JDBCDemo.FinanceDelete(asno,fno);
        }
        else{
            System.out.println("you are no management teacher");
            return -1;
        }
    }
    public void FinanceQuery1(AssociationController associationController,int select,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.FinanceQuery1(associations,select,name);
        for(Association association:associations){
            associationController.getData().add(association.GetFinanceInformation());
        }
    }
    public void FinanceQuery2(AssociationController associationController,int select,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.FinanceQuery2(associations,select,name);
        for(Association association:associations){
            associationController.getData().add(association.GetFinanceInformation2());
        }
    }
    public void FinanceQuery3(AssociationController associationController,Date date1,Date date2,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.FinanceQuery3(associations,date1,date2,name);
        for(Association association:associations){
            associationController.getData().add(association.GetFinanceInformation());
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
}
