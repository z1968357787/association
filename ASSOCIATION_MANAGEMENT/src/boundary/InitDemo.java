package boundary;


import controller.AssociationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class InitDemo extends JFrame{
    private JPanel contentPane;
    private CardLayout cardLayout;

    public static final int error=-10086;
    public static final int width=1000;
    public static final int height=700;
    public InitDemo() throws HeadlessException {
        setTitle("高校社团管理系统");
        setBounds(100,100,width,height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cardLayout=new CardLayout();
        contentPane = new JPanel();
        contentPane.setBounds(0,0,width,height);
        setContentPane(contentPane);
        contentPane.setLayout(cardLayout);
        setVisible(true);

    }
    public void InitInterface(AssociationController associationController){
        InitPanel initPanel=new InitPanel();
        contentPane.add(initPanel,"initPanel");
        cardLayout.show(contentPane,"initPanel");
        initPanel.InitInterface(associationController);

    }
    public void DirectLogin(AssociationController associationController){
        LoginPanel loginPanel=new LoginPanel();
        contentPane.add(loginPanel,"loginPanel");
        cardLayout.show(contentPane,"loginPanel");
        loginPanel.DirectLogin(associationController);

    }
    public void RegisterLogin(AssociationController associationController){
        RegisterPanel registerPanel=new RegisterPanel();
        contentPane.add(registerPanel,"registerPanel");
        cardLayout.show(contentPane,"registerPanel");
        registerPanel.RegisterLogin(associationController);

    }

    public void InitAssociation(AssociationController associationController){
        InitAssociationManagement initAssociationManagement=new InitAssociationManagement();
        contentPane.add(initAssociationManagement,"initAssociationManagement");
        cardLayout.show(contentPane,"initAssociationManagement");
        initAssociationManagement.ShowAssociation(associationController);
    }
    public void AssociationRequest(AssociationController associationController){
        AssociationRequestPanel associationRequestPanel=new AssociationRequestPanel();
        contentPane.add(associationRequestPanel,"associationRequestPanel");
        cardLayout.show(contentPane,"associationRequestPanel");
        associationRequestPanel.AssociationRequest(associationController);
    }

    public void AssociationApproval(AssociationController associationController){
        AssociationApprovalPanel associationApprovalPanel= new AssociationApprovalPanel();
        contentPane.add(associationApprovalPanel,"associationApprovalPanel");
        cardLayout.show(contentPane,"associationApprovalPanel");
        associationApprovalPanel.AssociationApproval(associationController);
    }
    public void AssociationQuery(AssociationController associationController){
        AssociationQueryPanel associationQueryPanel=new AssociationQueryPanel();
        contentPane.add(associationQueryPanel,"associationQueryPanel");
        cardLayout.show(contentPane,"associationQueryPanel");
        associationQueryPanel.AssociationQuery(associationController);
    }
    public void AssociationProcessUpdateAndDelete(AssociationController associationController){
        AssociationUpdatePanel associationUpdatePanel=new AssociationUpdatePanel();
        contentPane.add(associationUpdatePanel,"associationUpdatePanel");
        cardLayout.show(contentPane,"associationUpdatePanel");
        associationUpdatePanel.AssociationUpdate(associationController);
    }
    public void SelectMode(AssociationController associationController){
        SelectPanel selectPanel=new SelectPanel();
        contentPane.add(selectPanel,"selectPanel");
        cardLayout.show(contentPane,"selectPanel");
        selectPanel.SelectMode(associationController);
    }
    public void InitMember(AssociationController associationController){
        InitMemberManagement initMemberManagement=new InitMemberManagement();
        contentPane.add(initMemberManagement,"initMemberManagement");
        cardLayout.show(contentPane,"initMemberManagement");
        initMemberManagement.InitMember(associationController);
    }
    public void MemberRequest(AssociationController associationController){
        MemberRequestPanel memberRequestPanel=new MemberRequestPanel();
        contentPane.add(memberRequestPanel,"memberRequestPanel");
        cardLayout.show(contentPane,"memberRequestPanel");
        memberRequestPanel.MemberRequest(associationController);
    }
    public void MemberApproval(AssociationController associationController){
        MemberApprovalPanel memberApprovalPanel=new MemberApprovalPanel();
        contentPane.add(memberApprovalPanel,"memberApprovalPanel");
        cardLayout.show(contentPane,"memberApprovalPanel");
        memberApprovalPanel.MemberApproval(associationController);
    }
    public void MemberQuery(AssociationController associationController){
        MemberQueryPanel memberQueryPanel=new MemberQueryPanel();
        contentPane.add(memberQueryPanel,"memberQueryPanel");
        cardLayout.show(contentPane,"memberQueryPanel");
        memberQueryPanel.MemberQuery(associationController);
    }
    public void InitActivility(AssociationController associationController){
        InitActivilityManagement initActivilityManagement=new InitActivilityManagement();
        contentPane.add(initActivilityManagement,"initActivilityManagement");
        cardLayout.show(contentPane,"initActivilityManagement");
        initActivilityManagement.InitActivility(associationController);
    }
    public void ActivilityRequeset(AssociationController associationController){
        ActivilityRequesetPanel activilityRequesetPanel=new ActivilityRequesetPanel();
        contentPane.add(activilityRequesetPanel,"activilityRequesetPanel");
        cardLayout.show(contentPane,"activilityRequesetPanel");
        activilityRequesetPanel.ActivilityRequeset(associationController);
    }
    public void ActivilityApproval(AssociationController associationController){
        ActivilityApprovalPanel activilityApprovalPanel=new ActivilityApprovalPanel();
        contentPane.add(activilityApprovalPanel,"activilityApprovalPanel");
        cardLayout.show(contentPane,"activilityApprovalPanel");
        activilityApprovalPanel.ActivilityApproval(associationController);
    }
    public void ActivilityQuery(AssociationController associationController){
        ActivilityQueryPanel activilityQueryPanel=new ActivilityQueryPanel();
        contentPane.add(activilityQueryPanel,"activilityQueryPanel");
        cardLayout.show(contentPane,"activilityQueryPanel");
        activilityQueryPanel.ActivilityQuery(associationController);
    }
    public void ActivilityUpdate(AssociationController associationController){
        ActivilityUpdatePanel activilityUpdatePanel=new ActivilityUpdatePanel();
        contentPane.add(activilityUpdatePanel,"activilityUpdatePanel");
        cardLayout.show(contentPane,"activilityUpdatePanel");
        activilityUpdatePanel.ActivilityUpdate(associationController);
    }
    public void InitField(AssociationController associationController){
        InitFieldManagement initFieldManagement=new InitFieldManagement();
        contentPane.add(initFieldManagement,"initFieldManagement");
        cardLayout.show(contentPane,"initFieldManagement");
        initFieldManagement.InitField(associationController);
    }
    public void FieldAdd(AssociationController associationController){
        FieldAddPanel fieldAddPanel =new FieldAddPanel();
        contentPane.add(fieldAddPanel,"fieldRequestPanel");
        cardLayout.show(contentPane,"fieldRequestPanel");
        fieldAddPanel.FieldAdd(associationController);
    }
    public void FieldUpdateOrDelete(AssociationController associationController){
        FieldUpdateOrDeletePanel fieldUpdateOrDeletePanel=new FieldUpdateOrDeletePanel();
        contentPane.add(fieldUpdateOrDeletePanel,"fieldUpdateOrDeletePanel");
        cardLayout.show(contentPane,"fieldUpdateOrDeletePanel");
        fieldUpdateOrDeletePanel.FieldUpdateOrDelete(associationController);
    }
    public void FieldRequest(AssociationController associationController){
        FieldRequestPanel fieldRequestPanel=new FieldRequestPanel();
        contentPane.add(fieldRequestPanel,"fieldRequestPanel");
        cardLayout.show(contentPane,"fieldRequestPanel");
        fieldRequestPanel.FieldRequest(associationController);
    }
    public void FieldApproval(AssociationController associationController){
        FieldApprovalPanel fieldApprovalPanel=new FieldApprovalPanel();
        contentPane.add(fieldApprovalPanel,"fieldApprovalPanel");
        cardLayout.show(contentPane,"fieldApprovalPanel");
        fieldApprovalPanel.FieldApproval(associationController);
    }
    public void FieldUseQuery(AssociationController associationController){
        FieldUseQueryPanel fieldUseQueryPanel=new FieldUseQueryPanel();
        contentPane.add(fieldUseQueryPanel,"fieldUseQueryPanel");
        cardLayout.show(contentPane,"fieldUseQueryPanel");
        fieldUseQueryPanel.FieldUseQuery(associationController);
    }
    public void InitFinance(AssociationController associationController){
        InitFinanceManagement initFinanceManagement=new InitFinanceManagement();
        contentPane.add(initFinanceManagement,"initFinanceManagement");
        cardLayout.show(contentPane,"initFinanceManagement");
        initFinanceManagement.InitFinance(associationController);
    }
    public void FinanceAdd(AssociationController associationController){
        FinanceAddPanel financeAddPanel=new FinanceAddPanel();
        contentPane.add(financeAddPanel,"financeAddPanel");
        cardLayout.show(contentPane,"financeAddPanel");
        financeAddPanel.FinanceAdd(associationController);
    }
    public void FinanceUpdate(AssociationController associationController){
        FinanceUpdatePanel financeUpdatePanel=new FinanceUpdatePanel();
        contentPane.add(financeUpdatePanel,"financeUpdatePanel");
        cardLayout.show(contentPane,"financeUpdatePanel");
        financeUpdatePanel.FinanceUpdate(associationController);
    }
    public void FinanceQuery(AssociationController associationController){
        FinanceQueryPanel financeQueryPanel=new FinanceQueryPanel();
        contentPane.add(financeQueryPanel,"financeQueryPanel");
        cardLayout.show(contentPane,"financeQueryPanel");
        financeQueryPanel.FinanceQuery(associationController);
    }
    public void InitVolunteer(AssociationController associationController){
        InitVolunteerManagement initVolunteerManagement=new InitVolunteerManagement();
        contentPane.add(initVolunteerManagement,"initVolunteerManagement");
        cardLayout.show(contentPane,"initVolunteerManagement");
        initVolunteerManagement.InitVolunteer(associationController);
    }
    public void TaskAdd(AssociationController associationController){
        TaskAddPanel taskAddPanel=new TaskAddPanel();
        contentPane.add(taskAddPanel,"taskAddPanel");
        cardLayout.show(contentPane,"taskAddPanel");
        taskAddPanel.TaskAdd(associationController);
    }
    public void TaskUpdate(AssociationController associationController){
        TaskUpdatePanel taskUpdatePanel=new TaskUpdatePanel();
        contentPane.add(taskUpdatePanel,"taskUpdatePanel");
        cardLayout.show(contentPane,"taskUpdatePanel");
        taskUpdatePanel.TaskUpdate(associationController);
    }
    public void VolunteerRequest(AssociationController associationController){
        VolunteerRequestPanel volunteerRequestPanel=new VolunteerRequestPanel();
        contentPane.add(volunteerRequestPanel,"volunteerRequestPanel");
        cardLayout.show(contentPane,"volunteerRequestPanel");
        volunteerRequestPanel.VolunteerRequest(associationController);
    }
    public void VolunteerQuery(AssociationController associationController){
        VolunteerQueryPanel volunteerQueryPanel=new VolunteerQueryPanel();
        contentPane.add(volunteerQueryPanel,"volunteerQueryPanel");
        cardLayout.show(contentPane,"volunteerQueryPanel");
        volunteerQueryPanel.VolunteerQuery(associationController);
    }
    public void InitVote(AssociationController associationController){
        InitVoteManagement initVoteManagement=new InitVoteManagement();
        contentPane.add(initVoteManagement,"initVoteManagement");
        cardLayout.show(contentPane,"initVoteManagement");
        initVoteManagement.InitVote(associationController);
    }
    public void EventsAddOrDelete(AssociationController associationController){
        EventsAddOrDeletePanel eventsAddOrDeletePanel=new EventsAddOrDeletePanel();
        contentPane.add(eventsAddOrDeletePanel,"eventsAddOrDeletePanel");
        cardLayout.show(contentPane,"eventsAddOrDeletePanel");
        eventsAddOrDeletePanel.EventsAddOrDelete(associationController);
    }
    public void OptionsAddOrDelete(AssociationController associationController){
        OptionsAddOrDeletePanel optionsAddOrDeletePanel=new OptionsAddOrDeletePanel();
        contentPane.add(optionsAddOrDeletePanel,"optionsAddOrDeletePanel");
        cardLayout.show(contentPane,"optionsAddOrDeletePanel");
        optionsAddOrDeletePanel.OptionsAddOrDelete(associationController);
    }
    public void VoteForOptions(AssociationController associationController){
        VoteForOptionsPanel voteForOptionsPanel=new VoteForOptionsPanel();
        contentPane.add(voteForOptionsPanel,"voteForOptionsPanel");
        cardLayout.show(contentPane,"voteForOptionsPanel");
        voteForOptionsPanel.VoteForOptions(associationController);
    }
    public void VoteQuery(AssociationController associationController){
        VoteQueryPanel voteQueryPanel=new VoteQueryPanel();
        contentPane.add(voteQueryPanel,"voteQueryPanel");
        cardLayout.show(contentPane,"voteQueryPanel");
        voteQueryPanel.VoteQuery(associationController);
    }
    public void InitInvestigation(AssociationController associationController){
        InitInvestigationManagement initInvestigationManagement=new InitInvestigationManagement();
        contentPane.add(initInvestigationManagement,"initInvestigationManagement");
        cardLayout.show(contentPane,"initInvestigationManagement");
        initInvestigationManagement.InitInvestigation(associationController);
    }
    public void QuestionAddOrDelete(AssociationController associationController){
        QuestionAddOrDeletePanel questionAddOrDeletePanel=new QuestionAddOrDeletePanel();
        contentPane.add(questionAddOrDeletePanel,"questionAddOrDeletePanel");
        cardLayout.show(contentPane,"questionAddOrDeletePanel");
        questionAddOrDeletePanel.QuestionAddOrDelete(associationController);
    }
    public void AnswerForQuestion(AssociationController associationController){
        AnswerForQuestionPanel answerForQuestionPanel=new AnswerForQuestionPanel();
        contentPane.add(answerForQuestionPanel,"answerForQuestionPanel");
        cardLayout.show(contentPane,"answerForQuestionPanel");
        answerForQuestionPanel.AnswerForQuestion(associationController);
    }
    public void InvestigationQuery(AssociationController associationController){
        InvestigationQueryPanel investigationQueryPanel=new InvestigationQueryPanel();
        contentPane.add(investigationQueryPanel,"investigationQueryPanel");
        cardLayout.show(contentPane,"investigationQueryPanel");
        investigationQueryPanel.InvestigationQuery(associationController);
    }
    public void BBSControll(AssociationController associationController){
        BBSControllPanel bbsControllPanel=new BBSControllPanel();
        contentPane.add(bbsControllPanel,"bbsControllPanel");
        cardLayout.show(contentPane,"bbsControllPanel");
        bbsControllPanel.BBSControll(associationController);
    }
    public void MenberUpdate(AssociationController associationController){
        MenberUpdatePanel menberUpdatePanel=new MenberUpdatePanel();
        contentPane.add(menberUpdatePanel,"menberUpdatePanel");
        cardLayout.show(contentPane,"menberUpdatePanel");
        menberUpdatePanel.MenberUpdate(associationController);
    }
    public void AssociationCount(AssociationController associationController){
        AssociationCountPanel associationCountPanel=new AssociationCountPanel();
        contentPane.add(associationCountPanel,"associationCountPanel");
        cardLayout.show(contentPane,"associationCountPanel");
        associationCountPanel.AssociationCount(associationController);
    }
    public void MemberCount(AssociationController associationController){
        MemberCountPanel memberCountPanel=new MemberCountPanel();
        contentPane.add(memberCountPanel,"memberCountPanel");
        cardLayout.show(contentPane,"memberCountPanel");
        memberCountPanel.MemberCount(associationController);
    }
    public void ActivilityCount(AssociationController associationController){
        ActivilityCountPanel activilityCountPanel=new ActivilityCountPanel();
        contentPane.add(activilityCountPanel,"activilityCountPanel");
        cardLayout.show(contentPane,"activilityCountPanel");
        activilityCountPanel.ActivilityCount(associationController);
    }
    public void FieldUseCount(AssociationController associationController){
        FieldUseCountPanel fieldUseCountPanel=new FieldUseCountPanel();
        contentPane.add(fieldUseCountPanel,"fieldUseCountPanel");
        cardLayout.show(contentPane,"fieldUseCountPanel");
        fieldUseCountPanel.FieldUseCount(associationController);
    }
    public void TaskCount(AssociationController associationController){
        TaskCountPanel taskCountPanel=new TaskCountPanel();
        contentPane.add(taskCountPanel,"taskCountPanel");
        cardLayout.show(contentPane,"taskCountPanel");
        taskCountPanel.TaskCount(associationController);
    }
    public static void ProcessError(String str,int x){
        JDialog jDialog=new JDialog();
        jDialog.setBounds(300,300,500,100);
        jDialog.setVisible(true);
        JLabel jLabel=new JLabel(str);
        jDialog.setLayout(null);
        jLabel.setBounds(x,20,500,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));
        jDialog.add(jLabel);
    }
    public static void ProcessError(String str,int x,int width){
        JDialog jDialog=new JDialog();
        jDialog.setBounds(300,300,width,100);
        jDialog.setVisible(true);
        JLabel jLabel=new JLabel(str);
        jDialog.setLayout(null);
        jLabel.setBounds(x,20,width,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));
        jDialog.add(jLabel);
    }
    public static void ShowTable(Vector<Vector<Object>> rowData,Vector<String> columnName,int y,int height,JPanel jPanel){

        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.setDataVector(rowData,columnName);
        JTable jTable=new JTable(tableModel);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, r);

        JScrollPane jScrollPane=new JScrollPane(jTable);
        jScrollPane.setBounds(0,y,990,height-20);
        jPanel.add(jScrollPane);
    }
}
