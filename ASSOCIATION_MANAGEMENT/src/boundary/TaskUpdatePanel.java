package boundary;

import controller.AssociationController;
import controller.VolunteerController;
import entity.SharedFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class TaskUpdatePanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JComboBox<String> jComboBox;
    private JTextArea jTextArea;
    private JTextField jTextField_year;
    private JTextField jTextField_month;
    private JTextField jTextField_day;
    private JTextField jTextField6;
    private JTextField jTextField_query1;
    private JTextField jTextField_query2;
    private Vector<String> columnName=new Vector<>();
    public TaskUpdatePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void TaskUpdate(AssociationController associationController){

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,230);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,230,1000,50);
        jPanel2.setLayout(null);
        JPanel jPanel3=new JPanel();
        jPanel3.setBounds(0,460,1000,50);
        jPanel3.setLayout(null);

        JLabel label1=new JLabel("请输入更新或删除的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(90,15,200,25);
        JLabel label2=new JLabel("活动号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(350,15,80,25);
        JLabel label3=new JLabel("任务号:");
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
        jComboBox.addItem("更新");
        jComboBox.addItem("删除");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(600,15,100,25);

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-3);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询1":
                        try {
                            ProcessQuery1(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-3);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询2":
                        try {
                            ProcessQuery2(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-3);
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
        JLabel jLabel=new JLabel("注：如果要修改活动信息，则需要填写以下信息，若某部分不修改，可该部分不填");
        jLabel.setBounds(200,50,600,25);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));

        JLabel jLabel1=new JLabel("志愿时长:");
        jLabel1.setBounds(150,80,100,25);
        JLabel jLabel2=new JLabel("任务介绍:");
        jLabel2.setBounds(150,120,100,25);
        JLabel jLabel3=new JLabel("所需人数:");
        jLabel3.setBounds(275,80,100,25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField4 =new JTextField(20);
        jTextField4.setBounds(220,80,50,25);
        jTextField5=new JTextField(20);
        jTextField5.setBounds(350,80,50,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,120,500,80);
        jTextField4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField5.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));

        JLabel jLabel4=new JLabel("请输入服务日期:");
        jLabel4.setBounds(100,205,200,25);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_year=new JTextField();
        jTextField_year.setBounds(220,205,40,25);
        JLabel jLabel_year=new JLabel("年");
        jLabel_year.setBounds(265,205,20,25);
        jTextField_month=new JTextField();
        jTextField_month.setBounds(290,205,25,25);
        JLabel jLabel_month=new JLabel("月");
        jLabel_month.setBounds(320,205,20,25);
        jTextField_day=new JTextField();
        jTextField_day.setBounds(345,205,25,25);
        JLabel jLabel_day=new JLabel("日");
        jLabel_day.setBounds(375,205,20,25);

        jTextField_year.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_month.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField_day.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_year.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_month.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel_day.setFont(new Font("宋体", Font.BOLD, 15));

        JLabel jLabel5=new JLabel("服务场地号:");
        jLabel5.setBounds(400,205,100,25);
        jLabel5.setFont(new Font("宋体", Font.BOLD, 15));

        jTextField6=new JTextField(20);
        jTextField6.setBounds(490,205,50,25);
        jTextField6.setFont(new Font("宋体", Font.BOLD, 15));

        jPanel1.add(jLabel);
        jPanel1.add(label1);
        jPanel1.add(jTextField1);
        jPanel1.add(label2);
        jPanel1.add(jTextField2);
        jPanel1.add(label3);
        jPanel1.add(jTextField3);
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField4);
        jPanel1.add(jTextField5);
        jPanel1.add(jScrollPane);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField_year);
        jPanel1.add(jLabel_year);
        jPanel1.add(jTextField_month);
        jPanel1.add(jLabel_month);
        jPanel1.add(jTextField_day);
        jPanel1.add(jLabel_day);
        jPanel1.add(jLabel5);
        jPanel1.add(jTextField6);
        jPanel1.add(jComboBox);

        add(jPanel1);

        JLabel label4=new JLabel("请输入需要查询的社团名或活动名或地址:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(100,15,300,25);
        jTextField_query1 =new JTextField(10);
        jTextField_query1.setBounds(390,15,100,25);
        jTextField_query1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询1");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label4);
        jPanel2.add(jTextField_query1);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        JLabel label5=new JLabel("请输入需要查询的社团名或活动名或任务:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(100,15,300,25);
        jTextField_query2=new JTextField(10);
        jTextField_query2.setBounds(390,15,100,25);
        jTextField_query2.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton5=new JButton("查询2");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(760,15,75,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("返回");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(850,15,75,25);
        jButton6.addActionListener(new MyActionListener());

        jPanel3.add(label5);
        jPanel3.add(jTextField_query2);
        jPanel3.add(jButton5);
        jPanel3.add(jButton6);
        add(jPanel3);

        MakeTable1(associationController);
        MakeTable2(associationController);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getVolunteerController().setSelect(VolunteerController.init_volunteer);
        associationController.HandleMode();
    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        String select=jComboBox.getSelectedItem().toString();
        int asno= 0;
        int acno= 0;
        int tnum= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            acno = Integer.parseInt(jTextField2.getText());
            tnum = Integer.parseInt(jTextField3.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        switch (select){
            case "更新":ProcessUpdate(associationController,asno,acno,tnum);break;
            case "删除":ProcessDelete(associationController,asno,acno,tnum);break;
        }
        ProcessQuery2(associationController);
    }
    private void ProcessUpdate(AssociationController associationController,int asno,int acno,int tnum) throws SQLException {

        int duration=-1;
        if(!jTextField4.getText().equals("")){
            try {
                duration=Integer.parseInt(jTextField4.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        int people_needed=-1;
        if(!jTextField5.getText().equals("")){
            try {
                people_needed=Integer.parseInt(jTextField5.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        int lno=-1;
        if(!jTextField6.getText().equals("")){
            try {
                lno= Integer.parseInt(jTextField6.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        String assignment= jTextArea.getText();
        String jyear=jTextField_year.getText();
        String jmonth=jTextField_month.getText();
        String jday=jTextField_day.getText();
        Date date=null;
        MakeEmpty();
        if(SharedFunction.JudgeEmpty(jyear,jmonth,jday)){
            int year= 0;
            int month= 0;
            int day= 0;
            try {
                year = Integer.parseInt(jyear);
                month = Integer.parseInt(jmonth);
                day = Integer.parseInt(jday);
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
        int count=associationController.getVolunteerController().TaskUpdate(associationController,asno,acno,tnum,duration,people_needed,assignment,date,lno);
        ProcessError(count);
    }
    private void ProcessDelete(AssociationController associationController,int asno,int acno,int tnum) throws SQLException {
        MakeEmpty();
        int count=associationController.getVolunteerController().TaskDelete(associationController,asno,acno,tnum);
        ProcessError(count);
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);
        jTextArea.setText(null);
        jTextField_year.setText(null);
        jTextField_month.setText(null);
        jTextField_day.setText(null);
        jTextField5.setText(null);
        jTextField6.setText(null);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -3:str="地址与日期不符合活动要求";x=130;break;
            case -2:str="日期输入错误";x=180;break;
            case -1:str="操作失败，你不是该社团社长";x=120;break;
            case  0:str="无此任务号或无此活动号";x=140;break;
            default:str= "修改或删除成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeTable1(AssociationController associationController){
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
        InitDemo.ShowTable(associationController.getData(),columnName,280,180,this);
    }
    private void MakeTable2(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        columnName.add("任务号");
        columnName.add("任务简述");
        InitDemo.ShowTable(associationController.getData2(),columnName,510,190,this);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name= jTextField_query1.getText();
        jTextField_query1.setText(null);
        associationController.getVolunteerController().GetFieldsUseInformation(associationController,name);
        MakeTable1(associationController);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        String name=jTextField_query2.getText();
        jTextField_query2.setText(null);
        associationController.getVolunteerController().GetTnums(associationController,name);
        MakeTable2(associationController);
    }
}
