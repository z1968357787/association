package controller;

import entity.BBS;
import entity.JDBCDemo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BBSController {
    public void HandleMode(AssociationController associationController) throws SQLException {
        ProcessBBS(associationController);
    }
    private void ProcessBBS(AssociationController associationController) throws SQLException {
        GetBBS(associationController,"");
        associationController.getInitDemo().BBSControll(associationController);
    }
    public int BBSAdd(AssociationController associationController, int asno, String content, Date speak_date) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            BBS bbs=new BBS();
            bbs.getAssociation().setAsno(asno);
            bbs.getStudent().setId(sno);
            bbs.setContent(content);
            bbs.setSpeak_date(speak_date);
            return JDBCDemo.BBSAdd(bbs);
        }
        else {
            System.out.println("you are no the member of the association");
            return -1;
        }
    }
    public int BBSDelete(AssociationController associationController, int asno,int bno) throws SQLException {
        int sno=associationController.getUser().getId();
        if(JDBCDemo.IsAssociationMember(asno,sno,"已通过")){
            return JDBCDemo.BBSDelete(asno,sno,bno);
        }
        else {
            System.out.println("you are no the member of the association");
            return -1;
        }
    }
    public void GetBBS(AssociationController associationController,String name) throws SQLException {
        List<BBS> bbsList=new ArrayList<>();
        associationController.getData().clear();
        JDBCDemo.GetBBS(bbsList,name);
        for(BBS bbs:bbsList){
            associationController.getData().add(bbs.GetBBSInformation());
        }
    }
}
