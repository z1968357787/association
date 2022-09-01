package boundary;

import controller.AssociationController;
import controller.FinanceController;
import entity.JDBCDemo;
import entity.SharedFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class FinanceAddPanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextArea jTextArea;
    private JTextField jTextField_year;
    private JTextField jTextField_month;
    private JTextField jTextField_day;
    private JTextField jTextField_query;
    private Vector<String> columnName=new Vector<>();
    public FinanceAddPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FinanceAdd(AssociationController associationController){

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,190);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,190,1000,50);
        jPanel2.setLayout(null);

        JLabel jLabel=new JLabel("社团号:");
        jLabel .setBounds(165,20,200,25);
        JLabel jLabel1=new JLabel("收支金额:");
        jLabel1.setBounds(150,50,200,25);
        JLabel jLabel2=new JLabel("收支原因:");
        jLabel2.setBounds(150,80,200,25);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField1=new JTextField(20);
        jTextField1.setBounds(220,20,80,25);
        jTextField2=new JTextField(20);
        jTextField2.setBounds(220,50,80,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,80,500,80);
        JButton jButton1=new JButton("添加");
        jButton1.setBounds(845,20,80,35);
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(845,155,80,35);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setFont(new Font("宋体", Font.BOLD, 20));
        jButton2.setFont(new Font("宋体", Font.BOLD, 20));
        JLabel jLabel3=new JLabel("备注：收入填正数，支出填负数");
        jLabel3.setBounds(310,50,300,20);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "添加":
                        try {
                            ProcessRequest(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-3);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询":
                        try {
                            ProcessQuery(associationController);
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

        JLabel jLabel4=new JLabel("请输入财政日期:");
        jLabel4.setBounds(100,165,200,20);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year=new JTextField();
        jTextField_year.setBounds(220,165,40,25);
        JLabel jLabel_year=new JLabel("年");
        jLabel_year.setBounds(265,165,20,25);
        jTextField_month=new JTextField();
        jTextField_month.setBounds(290,165,25,25);
        JLabel jLabel_month=new JLabel("月");
        jLabel_month.setBounds(320,165,20,25);
        jTextField_day=new JTextField();
        jTextField_day.setBounds(345,165,25,25);
        JLabel jLabel_day=new JLabel("日");
        jLabel_day.setBounds(375,165,20,25);

        jTextField_year.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day.setFont(new Font("宋体", Font.BOLD, 15));

        jPanel1.add(jLabel);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField2);
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(jScrollPane);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField_year);
        jPanel1.add(jLabel_year);
        jPanel1.add(jTextField_month);
        jPanel1.add(jLabel_month);
        jPanel1.add(jTextField_day);
        jPanel1.add(jLabel_day);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);

        add(jPanel1);

        JLabel label4=new JLabel("请输入需要查询的社团名称:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(100,15,250,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(300,15,210,25);
        jTextField_query.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label4);
        jPanel2.add(jTextField_query);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        MakeTable(associationController);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        int asno= 0;
        double money= 0;
        String reason= null;
        int year= 0;
        int month= 0;
        int day= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            money = Double.parseDouble(jTextField2.getText());
            reason = jTextArea.getText();
            year = Integer.parseInt(jTextField_year.getText());
            month = Integer.parseInt(jTextField_month.getText());
            day = Integer.parseInt(jTextField_day.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        MakeEmpty();
        Date date;
        if(SharedFunction.JudgeDate(year,month,day)){
            date=new Date(year-1900,month-1,day);
        }
        else{
            ProcessError(-2);
            return;
        }
        int count=associationController.getFinanceController().FinanceAdd(associationController,asno,money,reason,date);
        ProcessError(count);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFinanceController().setSelect(FinanceController.init_finance);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -3:str="该社团处于审批状态";x=150;break;
            case -2:str="日期输入错误";x=180;break;
            case -1:str="申请失败，你不是该社团主管教师";x=100;break;
            default:str= "申请成功，财务号为:"+count;x=100;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeEmpty(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextArea.setText("");
        jTextField_year.setText("");
        jTextField_month.setText("");
        jTextField_day.setText("");
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        //rowData=associationController.getData();
        columnName.add("社团号");
        columnName.add("社团名");
        InitDemo.ShowTable(associationController.getData(),columnName,240,460,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        associationController.getFinanceController().GetAsnos(associationController,name);
        MakeTable(associationController);
        jTextField_query.setText(null);
    }
}
