package boundary;

import controller.AssociationController;
import controller.FieldController;
import entity.SharedFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class FieldRequestPanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField;
    private JTextField jTextField_year;
    private JTextField jTextField_month;
    private JTextField jTextField_day;
    private JTextField jTextField_query;
    private JComboBox<String> jComboBox;//加入还是退出

    private Vector<Vector<Object>> rowData=new Vector<>();
    private Vector<String> columnName=new Vector<>();

    public FieldRequestPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FieldRequest(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessRequest(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-3);
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

        MakeTable1(associationController);
        MakeTable2(associationController);

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,80);
        jPanel.setLayout(null);

        JLabel label1=new JLabel("请输入申请场地的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(100,15,200,25);
        JLabel label2=new JLabel("活动号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(350,15,80,25);
        JLabel label3=new JLabel("场地号:");
        label3.setFont(new Font("宋体", Font.BOLD, 15));
        label3.setBounds(475,15,80,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(290,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(415,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3=new JTextField(10);
        jTextField3.setBounds(540,15,50,25);
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));

        jComboBox=new JComboBox<>();
        jComboBox.addItem("申请");
        jComboBox.addItem("取消");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(600,15,100,25);

        JLabel jLabel=new JLabel("请输入使用日期:");
        jLabel.setBounds(100,50,200,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year=new JTextField();
        jTextField_year.setBounds(220,50,40,25);
        JLabel jLabel_year=new JLabel("年");
        jLabel_year.setBounds(265,50,20,25);
        jTextField_month=new JTextField();
        jTextField_month.setBounds(290,50,25,25);
        JLabel jLabel_month=new JLabel("月");
        jLabel_month.setBounds(320,50,20,25);
        jTextField_day=new JTextField();
        jTextField_day.setBounds(345,50,25,25);
        JLabel jLabel_day=new JLabel("日");
        jLabel_day.setBounds(375,50,20,25);

        jTextField_year.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(760,15,75,25);
        jButton1.addActionListener(new MyActionListener());


        JButton jButton2=new JButton("返回");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(850,15,75,25);
        jButton2.addActionListener(new MyActionListener());

        jPanel.add(label1);
        jPanel.add(jTextField1);
        jPanel.add(label2);
        jPanel.add(jTextField2);
        jPanel.add(label3);
        jPanel.add(jTextField3);
        jPanel.add(jLabel);
        jPanel.add(jTextField_year);
        jPanel.add(jLabel_year);
        jPanel.add(jTextField_month);
        jPanel.add(jLabel_month);
        jPanel.add(jTextField_day);
        jPanel.add(jLabel_day);
        jPanel.add(jComboBox);
        jPanel.add(jButton1);
        jPanel.add(jButton2);

        add(jPanel);

        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,80,1000,50);
        jPanel2.setLayout(null);

        JLabel label4=new JLabel("请输入需要查询的场地地址:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(100,15,250,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(300,15,210,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询1");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label4);
        jPanel2.add(jTextField);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        JPanel jPanel3=new JPanel();
        jPanel3.setBounds(0,400,1000,50);
        jPanel3.setLayout(null);

        JLabel label5=new JLabel("请输入需要查询的社团名或活动名:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(100,15,300,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(350,15,210,25);
        jTextField_query.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton5=new JButton("查询2");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(760,15,75,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("返回");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(850,15,75,25);
        jButton6.addActionListener(new MyActionListener());

        jPanel3.add(label5);
        jPanel3.add(jTextField_query);
        jPanel3.add(jButton5);
        jPanel3.add(jButton6);
        add(jPanel3);
    }
    private void MakeTable1(AssociationController associationController){
        columnName.clear();
        columnName.add("可用场地号");
        columnName.add("可用场地地址");
        InitDemo.ShowTable(associationController.getData(),columnName,130,270,this);
    }
    private void MakeTable2(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        InitDemo.ShowTable(associationController.getData2(),columnName,450,250,this);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        int asno= 0;
        int acno= 0;
        int lno= 0;
        String select=null;
        int year= 0;
        int month= 0;
        int day= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            acno = Integer.parseInt(jTextField2.getText());
            lno = Integer.parseInt(jTextField3.getText());
            select=jComboBox.getSelectedItem().toString();
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
        switch (select){
            case "申请":ProcessAdd(associationController,asno,acno,lno,date);break;
            case "取消":ProcessCancel(associationController,asno,acno,lno,date);break;
        }
    }
    private void ProcessAdd(AssociationController associationController,int asno,int acno,int lno,Date date) throws SQLException {
        int count=associationController.getFieldController().FieldRequest(associationController,asno,acno,lno,date);
        ProcessError(count);
    }
    private void ProcessCancel(AssociationController associationController,int asno,int acno,int lno,Date date) throws SQLException {
        int count=associationController.getFieldController().FieldCancel(associationController,asno,acno,lno,date);
        ProcessError(count);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getFieldController().GetActilityLoactions(associationController,name);
        MakeTable1(associationController);
        jTextField.setText(null);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        associationController.getFieldController().GetAcnos(associationController,name);
        MakeTable2(associationController);
        jTextField_query.setText(null);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.init_field);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -3:str="申请时间冲突或无此活动或地址";x=100;break;
            case -2:str="日期输入有误";x=180;break;
            case -1:str="你不是社团社长";x=170;break;
            case  0:str="取消失败";x=200;break;
            default:str="申请或取消成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeEmpty(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField_year.setText("");
        jTextField_month.setText("");
        jTextField_day.setText("");
    }

}
