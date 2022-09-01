package boundary;

import controller.AssociationController;
import controller.FieldController;
import entity.Located;
import entity.SharedFunction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class FieldUseQueryPanel extends JPanel {

    private Vector<String> columnName=new Vector<>();
    private JTextField jTextField;
    private JTextField jTextField_year1;
    private JTextField jTextField_month1;
    private JTextField jTextField_day1;
    private JTextField jTextField_year2;
    private JTextField jTextField_month2;
    private JTextField jTextField_day2;
    public FieldUseQueryPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FieldUseQuery(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessQuery1(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询所有":
                        try {
                            ProcessQuery2(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "日期查询":
                        try {
                            ProcessQuery3(associationController);
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

        JLabel label=new JLabel("请输入需要查询的社团名或活动名或地址:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,300,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(390,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField.setText("");

        JLabel jLabel1=new JLabel("请输入使用日期:");
        jLabel1.setBounds(100,50,200,25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year1=new JTextField();
        jTextField_year1.setBounds(220,50,40,25);
        JLabel jLabel_year1=new JLabel("年");
        jLabel_year1.setBounds(265,50,20,25);
        jTextField_month1=new JTextField();
        jTextField_month1.setBounds(290,50,25,25);
        JLabel jLabel_month1=new JLabel("月");
        jLabel_month1.setBounds(320,50,20,25);
        jTextField_day1=new JTextField();
        jTextField_day1.setBounds(345,50,25,25);
        JLabel jLabel_day1=new JLabel("日");
        jLabel_day1.setBounds(375,50,20,25);

        jTextField_year1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day1.setFont(new Font("宋体", Font.BOLD, 15));

        JLabel jLabel2=new JLabel("至");
        jLabel2.setBounds(400,50,20,25);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year2=new JTextField();
        jTextField_year2.setBounds(435,50,40,25);
        JLabel jLabel_year2=new JLabel("年");
        jLabel_year2.setBounds(480,50,20,25);
        jTextField_month2=new JTextField();
        jTextField_month2.setBounds(505,50,25,25);
        JLabel jLabel_month2=new JLabel("月");
        jLabel_month2.setBounds(535,50,20,25);
        jTextField_day2=new JTextField();
        jTextField_day2.setBounds(560,50,25,25);
        JLabel jLabel_day2=new JLabel("日");
        jLabel_day2.setBounds(590,50,20,25);

        jTextField_year2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day2.setFont(new Font("宋体", Font.BOLD, 15));


        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(500,15,75,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("查询所有");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(600,15,100,25);
        jButton2.addActionListener(new MyActionListener());

        JButton jButton3=new JButton("返回");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(750,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("日期查询");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(620,50,100,25);
        jButton4.addActionListener(new MyActionListener());

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,100);
        jPanel.setLayout(null);


        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(jButton4);
        jPanel.add(jLabel1);
        jPanel.add(jTextField_year1);
        jPanel.add(jLabel_year1);
        jPanel.add(jTextField_month1);
        jPanel.add(jLabel_month1);
        jPanel.add(jTextField_day1);
        jPanel.add(jLabel_day1);
        jPanel.add(jLabel2);
        jPanel.add(jTextField_year2);
        jPanel.add(jLabel_year2);
        jPanel.add(jTextField_month2);
        jPanel.add(jLabel_month2);
        jPanel.add(jTextField_day2);
        jPanel.add(jLabel_day2);

        add(jPanel);
    }
    private void MakeTable(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        columnName.add("场地编号");
        columnName.add("场地地址");
        columnName.add("使用日期");
        columnName.add("申请状态");
        InitDemo.ShowTable(associationController.getData(),columnName,100,600,this);
    }

    private void MakeEmpty(){
        jTextField.setText("");
        jTextField_year1.setText("");
        jTextField_month1.setText("");
        jTextField_day1.setText("");
        jTextField_year2.setText("");
        jTextField_month2.setText("");
        jTextField_day2.setText("");
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        MakeEmpty();
        associationController.getFieldController().GetFieldsUseInformation(associationController,"已通过",name);
        MakeTable(associationController);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        MakeEmpty();
        associationController.getFieldController().GetFieldsUseInformation(associationController,"已通过","");
        MakeTable(associationController);
    }
    private void ProcessQuery3(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        String jyear1=jTextField_year1.getText();
        String jmonth1=jTextField_month1.getText();
        String jday1=jTextField_day1.getText();
        String jyear2=jTextField_year2.getText();
        String jmonth2=jTextField_month2.getText();
        String jday2=jTextField_day2.getText();
        MakeEmpty();
        if(SharedFunction.JudgeEmpty(jyear1,jyear2,jmonth1,jmonth2,jday1,jday2)){
            int year1= 0;
            int year2= 0;
            int month1= 0;
            int month2= 0;
            int day1= 0;
            int day2= 0;
            try {
                year1 = Integer.parseInt(jyear1);
                year2 = Integer.parseInt(jyear2);
                month1 = Integer.parseInt(jmonth1);
                month2 = Integer.parseInt(jmonth2);
                day1 = Integer.parseInt(jday1);
                day2 = Integer.parseInt(jday2);
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
            if(SharedFunction.JudgeDate(year1,month1,day1)&&SharedFunction.JudgeDate(year2,month2,day2)){
                Date date1=new Date(year1-1900,month1-1,day1);
                Date date2=new Date(year2-1900,month2-1,day2);
                associationController.getFieldController().GetFieldsUseInformation(associationController,"已通过",name,date1,date2);
                MakeTable(associationController);
            }
            else {
                ProcessError(-2);
                System.out.println("the date is error");
                return;
            }
        }
        else{
            ProcessError(-2);
            System.out.println("the date is error");
            return;
        }
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.init_field);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -2:str="日期输入错误";x=180;break;
            default:str="查询成功";x=200;break;
        }
        InitDemo.ProcessError(str,x);
    }
}
