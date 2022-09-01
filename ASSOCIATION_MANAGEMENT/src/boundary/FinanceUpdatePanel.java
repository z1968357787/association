package boundary;

import controller.ActivilityController;
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

public class FinanceUpdatePanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextArea jTextArea;
    private JTextField jTextField_year;
    private JTextField jTextField_month;
    private JTextField jTextField_day;
    private JComboBox<String> jComboBox;
    private JTextField jTextField_query;
    private Vector<String> columnName=new Vector<>();
    public FinanceUpdatePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FinanceUpdate(AssociationController associationController){
        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,220);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,220,1000,50);
        jPanel2.setLayout(null);

        JLabel label=new JLabel("请输入需要修改或删除的社团号:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(150,15,250,25);
        JLabel label2=new JLabel("财务号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(460,15,65,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(390,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(520,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox=new JComboBox<>();
        jComboBox.addItem("更新");
        jComboBox.addItem("删除");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(580,15,90,25);

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                //判断该号是否存在
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询":
                        try {
                            ProcessQuery(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
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

        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(760,15,75,25);
        jButton1.addActionListener(new MyActionListener());


        JButton jButton2=new JButton("返回");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(850,15,75,25);
        jButton2.addActionListener(new MyActionListener());

        jPanel1.add(label);
        jPanel1.add(label2);
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jComboBox);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);

        JLabel jLabel=new JLabel("注：如果要修改活动信息，则需要填写以下信息，若某部分不修改，可该部分不填");
        jLabel.setBounds(200,50,600,25);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        JLabel jLabel1=new JLabel("收支金额:");
        jLabel1.setBounds(150,80,200,25);
        JLabel jLabel2=new JLabel("收支原因:");
        jLabel2.setBounds(150,110,200,25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3=new JTextField(20);
        jTextField3.setBounds(220,80,80,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,110,500,80);

        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3.setText("");
        jTextArea.setText("");

        JLabel jLabel4=new JLabel("请输入使用日期:");
        jLabel4.setBounds(100,195,200,20);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year=new JTextField();
        jTextField_year.setBounds(220,195,40,25);
        JLabel jLabel_year=new JLabel("年");
        jLabel_year.setBounds(265,195,20,25);
        jTextField_month=new JTextField();
        jTextField_month.setBounds(290,195,25,25);
        JLabel jLabel_month=new JLabel("月");
        jLabel_month.setBounds(320,195,20,25);
        jTextField_day=new JTextField();
        jTextField_day.setBounds(345,195,25,25);
        JLabel jLabel_day=new JLabel("日");
        jLabel_day.setBounds(375,195,20,25);

        jTextField_year.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day.setFont(new Font("宋体", Font.BOLD, 15));

        jPanel1.add(jLabel);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField3);
        jPanel1.add(jLabel2);
        jPanel1.add(jScrollPane);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField_year);
        jPanel1.add(jLabel_year);
        jPanel1.add(jTextField_month);
        jPanel1.add(jLabel_month);
        jPanel1.add(jTextField_day);
        jPanel1.add(jLabel_day);

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
    private void ProcessUpdate(int asno, int fno, AssociationController associationController) throws SQLException {
        double money=0;
        if(!jTextField3.getText().equals("")){
            try {
                money= Double.parseDouble(jTextField3.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        String reason= jTextArea.getText();
        Date date;
        String jyear=jTextField_year.getText();
        String jmonth=jTextField_month.getText();
        String jday=jTextField_day.getText();
        if(SharedFunction.JudgeEmpty(jyear,jmonth,jday)){
            int year= 0;
            int month= 0;
            int day= 0;
            try {
                year = Integer.parseInt(jTextField_year.getText());
                month = Integer.parseInt(jTextField_month.getText());
                day = Integer.parseInt(jTextField_day.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
            if(SharedFunction.JudgeDate(year,month,day)){
                date=new Date(year-1900,month-1,day);
            }
            else {
                ProcessError(-2);
                return;
            }
        }
        else{
            date=null;
        }
        int count=associationController.getFinanceController().FinanceUpdate(asno,fno,money,reason,date,associationController);
        ProcessError(count);
    }
    private void ProcessDelete(int asno,int fno,AssociationController associationController) throws SQLException {
        int count=associationController.getFinanceController().FinanceDelete(asno,fno,associationController);
        ProcessError(count);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -2:str="日期输入错误";x=180;break;
            case -1:str="申请失败，你不是该社团主管教师";x=100;break;
            case 0:str="无此财务号";x=190;break;
            default:str= "修改或删除成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        int fno= 0;
        String select= null;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            fno = Integer.parseInt(jTextField2.getText());
            select = jComboBox.getSelectedItem().toString();
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        switch (select){
            case "更新":ProcessUpdate(asno,fno,associationController);break;
            case "删除":ProcessDelete(asno,fno,associationController);break;
        }
        MakeEmpty();
        ProcessQuery(associationController);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFinanceController().setSelect(FinanceController.init_finance);
        associationController.HandleMode();
    }
    private void MakeEmpty(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextArea.setText("");
        jTextField_year.setText("");
        jTextField_month.setText("");
        jTextField_day.setText("");
    }
    private void MakeTable(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("财务流水号");
        columnName.add("收支金额");
        columnName.add("收支原因");
        columnName.add("记录日期");
        InitDemo.ShowTable(associationController.getData(),columnName,270,430,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        jTextField_query.setText("");
        associationController.getFinanceController().FinanceQuery1(associationController,1,name);
        MakeTable(associationController);
    }
}
