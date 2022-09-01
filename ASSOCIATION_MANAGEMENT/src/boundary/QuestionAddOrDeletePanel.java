package boundary;

import controller.AssociationController;
import controller.InvestigationController;
import entity.SharedFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class QuestionAddOrDeletePanel extends JPanel {
    private JTextField jTextField1;//社团号
    private JTextArea jTextArea;//事件标题
    private JTextField jTextField_year;
    private JTextField jTextField_month;
    private JTextField jTextField_day;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField_query1;
    private JTextField jTextField_query2;
    private Vector<String> columnName=new Vector<>();
    public QuestionAddOrDeletePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void QuestionAddOrDelete(AssociationController associationController){
        JPanel jPanel1=new JPanel();
        JPanel jPanel2=new JPanel();
        JPanel jPanel3=new JPanel();
        JPanel jPanel4=new JPanel();
        jPanel1.setBounds(0,0,1000,160);
        jPanel2.setBounds(0,160,1000,80);
        jPanel3.setBounds(0,240,1000,50);
        jPanel4.setBounds(0,470,1000,50);
        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel3.setLayout(null);
        jPanel4.setLayout(null);

        JLabel jLabel=new JLabel("添加问题");
        jLabel.setBounds(480,15,580,50);
        jLabel.setFont(new Font("宋体", Font.BOLD, 25));
        JLabel jLabel0=new JLabel("社团号:");
        jLabel0 .setBounds(165,50,200,25);
        JLabel jLabel1=new JLabel("调查问题:");
        jLabel1.setBounds(150,80,200,25);
        jLabel0.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField1=new JTextField(20);
        jTextField1.setBounds(220,50,50,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,80,500,50);
        JButton jButton1=new JButton("添加");
        jButton1.setBounds(760,135,75,25);
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(850,135,75,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "添加":
                        try {
                            ProcessAdd(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-3);
                            ex.printStackTrace();
                        }
                        break;
                    case "删除":
                        try {
                            ProcessDelete(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询1":
                        try {
                            ProcessQuery1(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询2":
                        try {
                            ProcessQuery2(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "返回":
                        try {
                            ProcessReturn(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        }

        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());

        JLabel jLabel2=new JLabel("请输入截止日期:");
        jLabel2.setBounds(100,135,200,25);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year=new JTextField();
        jTextField_year.setBounds(220,135,40,25);
        JLabel jLabel_year=new JLabel("年");
        jLabel_year.setBounds(265,135,20,25);
        jTextField_month=new JTextField();
        jTextField_month.setBounds(290,135,25,25);
        JLabel jLabel_month=new JLabel("月");
        jLabel_month.setBounds(320,135,20,25);
        jTextField_day=new JTextField();
        jTextField_day.setBounds(345,135,25,25);
        JLabel jLabel_day=new JLabel("日");
        jLabel_day.setBounds(375,135,20,25);

        jTextField_year.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day.setFont(new Font("宋体", Font.BOLD, 15));

        jPanel1.add(jLabel);
        jPanel1.add(jLabel0);
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField1);
        jPanel1.add(jScrollPane);
        jPanel1.add(jLabel_year);
        jPanel1.add(jTextField_year);
        jPanel1.add(jLabel_month);
        jPanel1.add(jTextField_month);
        jPanel1.add(jLabel_day);
        jPanel1.add(jTextField_day);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        add(jPanel1);


        JLabel jLabell=new JLabel("删除问题");
        jLabell.setBounds(480,15,580,50);
        jLabell.setFont(new Font("宋体", Font.BOLD, 25));
        JLabel jLabel3=new JLabel("社团号:");
        jLabel3 .setBounds(165,50,100,25);
        JLabel jLabel4=new JLabel("问题号:");
        jLabel4.setBounds(290,50,100,25);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2 =new JTextField(20);
        jTextField2.setBounds(220,50,50,25);
        jTextField3 =new JTextField(20);
        jTextField3.setBounds(350,50,50,25);
        JButton jButton3=new JButton("删除");
        jButton3.setBounds(760,50,75,25);
        JButton jButton4=new JButton("返回");
        jButton4.setBounds(850,50,75,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.addActionListener(new MyActionListener());
        jButton4.addActionListener(new MyActionListener());
        jPanel2.add(jLabell);
        jPanel2.add(jLabel3);
        jPanel2.add(jLabel4);
        jPanel2.add(jTextField2);
        jPanel2.add(jTextField3);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        JLabel label4=new JLabel("请输入需要查询的社团名或问题名:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(100,15,300,25);
        jTextField_query1 =new JTextField(10);
        jTextField_query1.setBounds(350,15,100,25);
        jTextField_query1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton5=new JButton("查询1");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(760,15,75,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("返回");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(850,15,75,25);
        jButton6.addActionListener(new MyActionListener());

        jPanel3.add(label4);
        jPanel3.add(jTextField_query1);
        jPanel3.add(jButton5);
        jPanel3.add(jButton6);
        add(jPanel3);

        JLabel label5=new JLabel("请输入需要查询的社团名:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(100,15,300,25);
        jTextField_query2=new JTextField(10);
        jTextField_query2.setBounds(300,15,100,25);
        jTextField_query2.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton7=new JButton("查询2");
        jButton7.setFont(new Font("宋体", Font.BOLD, 15));
        jButton7.setBounds(760,15,75,25);
        jButton7.addActionListener(new MyActionListener());

        JButton jButton8=new JButton("返回");
        jButton8.setFont(new Font("宋体", Font.BOLD, 15));
        jButton8.setBounds(850,15,75,25);
        jButton8.addActionListener(new MyActionListener());

        jPanel4.add(label5);
        jPanel4.add(jTextField_query2);
        jPanel4.add(jButton7);
        jPanel4.add(jButton8);
        add(jPanel4);

        MakeTable1(associationController);
        MakeTable2(associationController);
    }

    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getInvestigationController().setSelect(InvestigationController.init_investigation);
        associationController.HandleMode();
    }

    private void ProcessDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        int qno= 0;
        try {
            asno = Integer.parseInt(jTextField2.getText());
            qno = Integer.parseInt(jTextField3.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        MakeEmpty();
        int count=associationController.getInvestigationController().QuestionDelete(associationController,asno,qno);
        ProcessError(count);
        ProcessQuery1(associationController);
    }

    private void ProcessAdd(AssociationController associationController) throws SQLException {
        int asno= 0;
        String content= null;
        int year= 0;
        int month= 0;
        int day= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            content = jTextArea.getText();
            year = Integer.parseInt(jTextField_year.getText());
            month = Integer.parseInt(jTextField_month.getText());
            day = Integer.parseInt(jTextField_day.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        MakeEmpty();
        if(SharedFunction.JudgeDate(year,month,day)){
            Date date=new Date(year-1900,month-1,day);
            int count=associationController.getInvestigationController().QuestionAdd(associationController,asno,content,date);
            ProcessError(count);
        }
        else{
            ProcessError(-2);
        }
        ProcessQuery1(associationController);
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField_year.setText(null);
        jTextField_month.setText(null);
        jTextField_day.setText(null);
        jTextArea.setText(null);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -3:str="该社团处于审批状态";x=150;break;
            case -2:str="日期输入错误";x=180;break;
            case -1:str="申请失败，你不是该社团社长";x=120;break;
            case 0:str="无此问题";x=200;break;
            case 1:str="删除成功";x=200;break;
            default:str= "申请成功，问题号为:"+count;x=120;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeTable1(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("问题号");
        columnName.add("问题内容");
        columnName.add("截止日期");
        InitDemo.ShowTable(associationController.getData(),columnName,290,180,this);
    }
    private void MakeTable2(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        InitDemo.ShowTable(associationController.getData2(),columnName,520,180,this);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name=jTextField_query1.getText();
        associationController.getInvestigationController().GetQuestions(associationController,name);
        MakeTable1(associationController);
        jTextField_query1.setText(null);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        String name=jTextField_query2.getText();
        associationController.getInvestigationController().GetAsnos(associationController,name);
        MakeTable2(associationController);
        jTextField_query2.setText(null);
    }
}
