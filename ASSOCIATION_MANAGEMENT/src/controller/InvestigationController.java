package controller;

import entity.Association;
import entity.Investigation;
import entity.JDBCDemo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestigationController {
    private int select;
    public static final int init_investigation=0;
    public static final int questions_add_or_delete=1;
    public static final int answer_for_question=2;
    public static final int investigation_query=3;

    public InvestigationController() {
        select=init_investigation;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_investigation:Init(associationController);break;
            case questions_add_or_delete: ProcessAddOrDelete(associationController);break;
            case answer_for_question:ProcessAnswer(associationController);break;
            case investigation_query: ProcessQuery(associationController);break;
        }
    }

    private void ProcessQuery(AssociationController associationController) {
        associationController.getInitDemo().InvestigationQuery(associationController);
    }

    private void ProcessAnswer(AssociationController associationController) throws SQLException {
        GetQuestions(associationController,"");
        associationController.getInitDemo().AnswerForQuestion(associationController);
    }

    private void ProcessAddOrDelete(AssociationController associationController) throws SQLException {
        GetQuestions(associationController,"");
        GetAsnos(associationController,"");
        associationController.getInitDemo().QuestionAddOrDelete(associationController);
    }

    private void Init(AssociationController associationController) {
        associationController.getInitDemo().InitInvestigation(associationController);
    }
    public int QuestionAdd(AssociationController associationController, int asno, String content, Date date) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            Association association=new Association();
            association.setAsno(asno);
            association.getQuestions().setContent(content);
            association.getQuestions().setDdl_date(date);
            return JDBCDemo.QuestionAdd(association);
        }
        else{
            System.out.println("you are no the member of the association");
            return -1;
        }
    }
    public int QuestionDelete(AssociationController associationController, int asno, int qno) throws SQLException {
        int mgn_sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationLeader(asno,mgn_sno)){
            return JDBCDemo.QuestionDelete(asno,qno);
        }
        else{
            System.out.println("you are no the member of the association");
            return -1;
        }
    }
    public void GetQuestions(AssociationController associationController,String name) throws SQLException {
        List<Association> associations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetQuestions(associations,name);
        for(Association association:associations){
            associationController.getData().add(association.GetQuestionsInformation());
        }
    }
    public int InvestigationAdd(AssociationController associationController,int asno,int qno,String answer,Date answer_date) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            Investigation investigation=new Investigation();
            investigation.getAssociation().setAsno(asno);
            investigation.getStudent().setId(sno);
            investigation.getAssociation().getQuestions().setQno(qno);
            investigation.setAnswer(answer);
            investigation.setAnswer_date(answer_date);
            return JDBCDemo.InvestigationAdd(investigation);
        }
        else{
            System.out.println("you are no the member of the association");
            return 0;
        }
    }
    public int InvestigationDelete(AssociationController associationController,int asno,int qno) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){

            return JDBCDemo.InvestigationDelete(asno,sno,qno);
        }
        else{
            System.out.println("you are no the member of the association");
            return 0;
        }

    }
    public void GetInvestigations(AssociationController associationController,String name) throws SQLException {
        List<Investigation> investigations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetInvestigations(investigations,name);
        for(Investigation investigation:investigations){
            associationController.getData().add(investigation.GetInvestigationsInformation1());
        }
    }
    public void GetInvestigations(AssociationController associationController,String name,String select) throws SQLException {
        List<Investigation> investigations=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetInvestigations(investigations,name,select);
        for(Investigation investigation:investigations){
            associationController.getData().add(investigation.GetInvestigationsInformation2());
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

}
