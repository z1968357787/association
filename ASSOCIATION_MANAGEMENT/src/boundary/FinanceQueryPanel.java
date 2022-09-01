package boundary;

import controller.AssociationController;
import controller.FinanceController;
import entity.Association;
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

public class FinanceQueryPanel extends JPanel {
    private static final int y=150;
    private static final int height=550;
    private JTextField jTextField;
    private Vector<String> columnName=new Vector<>();
    private JTextField jTextField_year1;
    private JTextField jTextField_month1;
    private JTextField jTextField_day1;
    private JTextField jTextField_year2;
    private JTextField jTextField_month2;
    private JTextField jTextField_day2;
    public FinanceQueryPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FinanceQuery(AssociationController associationController){

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select =e.getActionCommand();
                switch(select){
                    case "查询所有":
                        try {
                            ProcessQuery1(associationController,1);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询收入":
                        try {
                            ProcessQuery1(associationController,2);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询支出":
                        try {
                            ProcessQuery1(associationController,3);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询总收支":
                        try {
                            ProcessQuery2(associationController,1);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询总收入":
                        try {
                            ProcessQuery2(associationController,2);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询总支出":
                        try {
                            ProcessQuery2(associationController,3);
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


        JLabel label=new JLabel("请输入需要查询的社团名:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,200,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(290,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField.setText("");

        JButton jButton1=new JButton("查询所有");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(100,50,100,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("查询收入");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(210,50,100,25);
        jButton2.addActionListener(new MyActionListener());

        JButton jButton3=new JButton("查询支出");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(320,50,100,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("查询总收支");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(430,50,120,25);
        jButton4.addActionListener(new MyActionListener());

        JButton jButton5=new JButton("查询总收入");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(560,50,120,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("查询总支出");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(700,50,120,25);
        jButton6.addActionListener(new MyActionListener());

        JButton jButton7=new JButton("日期查询");
        jButton7.setFont(new Font("宋体", Font.BOLD, 15));
        jButton7.setBounds(620,100,100,25);
        jButton7.addActionListener(new MyActionListener());

        JButton jButton8=new JButton("返回");
        jButton8.setFont(new Font("宋体", Font.BOLD, 15));
        jButton8.setBounds(620,15,75,25);
        jButton8.addActionListener(new MyActionListener());

        JLabel jLabel1=new JLabel("请输入使用日期:");
        jLabel1.setBounds(100,100,200,25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year1=new JTextField();
        jTextField_year1.setBounds(220,100,40,25);
        JLabel jLabel_year1=new JLabel("年");
        jLabel_year1.setBounds(265,100,20,25);
        jTextField_month1=new JTextField();
        jTextField_month1.setBounds(290,100,25,25);
        JLabel jLabel_month1=new JLabel("月");
        jLabel_month1.setBounds(320,100,20,25);
        jTextField_day1=new JTextField();
        jTextField_day1.setBounds(345,100,25,25);
        JLabel jLabel_day1=new JLabel("日");
        jLabel_day1.setBounds(375,100,20,25);

        jTextField_year1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day1.setFont(new Font("宋体", Font.BOLD, 15));

        JLabel jLabel2=new JLabel("至");
        jLabel2.setBounds(400,100,20,25);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year2=new JTextField();
        jTextField_year2.setBounds(435,100,40,25);
        JLabel jLabel_year2=new JLabel("年");
        jLabel_year2.setBounds(480,100,20,25);
        jTextField_month2=new JTextField();
        jTextField_month2.setBounds(505,100,25,25);
        JLabel jLabel_month2=new JLabel("月");
        jLabel_month2.setBounds(535,100,20,25);
        jTextField_day2=new JTextField();
        jTextField_day2.setBounds(560,100,25,25);
        JLabel jLabel_day2=new JLabel("日");
        jLabel_day2.setBounds(590,100,20,25);

        jTextField_year2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day2.setFont(new Font("宋体", Font.BOLD, 15));

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,150);
        jPanel.setLayout(null);


        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(jButton4);
        jPanel.add(jButton5);
        jPanel.add(jButton6);
        jPanel.add(jButton7);
        jPanel.add(jButton8);
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
    private void MakeTable1(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("财务流水号");
        columnName.add("收支金额");
        columnName.add("收支原因");
        columnName.add("记录日期");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
    private void MakeTable2(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("收支金额");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
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
    private void ProcessQuery1(AssociationController associationController,int select) throws SQLException {
        String name=jTextField.getText();
        jTextField.setText("");
        associationController.getFinanceController().FinanceQuery1(associationController,select,name);
        MakeTable1(associationController);
    }
    private void ProcessQuery2(AssociationController associationController,int select) throws SQLException {
        String name=jTextField.getText();
        jTextField.setText("");
        associationController.getFinanceController().FinanceQuery2(associationController,select,name);
        MakeTable2(associationController);
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
                associationController.getFinanceController().FinanceQuery3(associationController,date1,date2,name);
                MakeTable1(associationController);
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
        associationController.getFinanceController().setSelect(FinanceController.init_finance);
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
