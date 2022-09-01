package controller;

import boundary.InitDemo;
import entity.JDBCDemo;
import entity.Users;

import java.sql.SQLException;
import java.util.Vector;

public class AssociationController {
    private LoginController loginController;
    private AssociationManagementController associationManagementController;
    private SelectController selectController;
    private MemberController memberController;
    private ActivilityController activilityController;
    private FieldController fieldController;
    private FinanceController financeController;
    private VolunteerController volunteerController;
    private VoteController voteController;
    private InvestigationController investigationController;
    private BBSController bbsController;
    private InitDemo initDemo;
    private int select;

    private Users user;
    private Vector<Vector<Object>> Data=new Vector<>();
    private Vector<Vector<Object>> Data2=new Vector<>();

    public static final int login=1;
    public static final int select_mode=2;
    public static final int association_management=3;
    public static final int member_management=4;
    public static final int activility_management=5;
    public static final int field_management=6;
    public static final int finance_management=7;
    public static final int volunteer_management =8;
    public static final int vote_management=9;
    public static final int investigation_management=10;
    public static final int bbs_management=11;

    public Vector<Vector<Object>> getData() {
        return Data;
    }

    public Vector<Vector<Object>> getData2() {
        return Data2;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public AssociationManagementController getAssociationManagementController() {
        return associationManagementController;
    }

    public MemberController getMemberController() {
        return memberController;
    }

    public FieldController getFieldController() {return fieldController;}

    public ActivilityController getActivilityController() {
        return activilityController;
    }

    public FinanceController getFinanceController() {
        return financeController;
    }

    public VolunteerController getVolunteerController() {
        return volunteerController;
    }

    public VoteController getVoteController() {
        return voteController;
    }

    public InvestigationController getInvestigationController() {
        return investigationController;
    }

    public BBSController getBbsController() {
        return bbsController;
    }

    public InitDemo getInitDemo() {
        return initDemo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public AssociationController() throws SQLException, ClassNotFoundException {
        JDBCDemo.start();
        initDemo=new InitDemo();
        loginController=new LoginController();
        selectController=new SelectController();
        associationManagementController=new AssociationManagementController();
        memberController=new MemberController();
        activilityController=new ActivilityController();
        fieldController=new FieldController();
        financeController=new FinanceController();
        volunteerController=new VolunteerController();
        voteController=new VoteController();
        investigationController=new InvestigationController();
        bbsController=new BBSController();
        select=login;
        HandleMode();
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    private void ControllLogin() throws SQLException {
        loginController.HandleMode(this);
    }

    private void ControllSelect(){selectController.HandleMode(this);}

    private void ControllAssociationManagement() throws SQLException {associationManagementController.HandleMode(this);}

    private void ControllMemberManagement() throws SQLException {memberController.HandleMode(this);}

    private void ControllActivilityManagement() throws SQLException {activilityController.HandleMode(this);}

    private void ControllFieldManagement() throws SQLException {fieldController.HandleMode(this);}

    private void ControllFinanceManagement() throws SQLException {financeController.HandleMode(this);}

    private void ControllVolunteerManagement() throws SQLException {volunteerController.HandleMode(this);}

    private void ControllVoteManagement() throws SQLException {voteController.HandleMode(this);}

    private void ControllInvestigationManagement() throws SQLException {investigationController.HandleMode(this);}

    private void ControllBBSManagement() throws SQLException {bbsController.HandleMode(this);}

    public void HandleMode() throws SQLException {
        switch (select){
            case login:ControllLogin();break;
            case select_mode:ControllSelect();break;
            case association_management:ControllAssociationManagement();break;
            case member_management: ControllMemberManagement();break;
            case activility_management:ControllActivilityManagement();break;
            case field_management:ControllFieldManagement();break;
            case finance_management:ControllFinanceManagement();break;
            case volunteer_management: ControllVolunteerManagement();break;
            case vote_management:ControllVoteManagement();break;
            case investigation_management:ControllInvestigationManagement();break;
            case bbs_management:;ControllBBSManagement();break;
        }
    }
}
