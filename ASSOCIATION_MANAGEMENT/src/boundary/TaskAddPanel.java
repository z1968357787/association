package boundary;

import controller.ActivilityController;
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

public class TaskAddPanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextArea jTextArea;
    private JTextField jTextField4;
    private JTextField jTextField_year;
    private JTextField jTextField_month;
    private JTextField jTextField_day;
    private JTextField jTextField5;
    private JTextField jTextField_query;
    private Vector<String> columnName=new Vector<>();
    public TaskAddPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void TaskAdd(AssociationController associationController){

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,190);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,190,1000,50);
        jPanel2.setLayout(null);

        JLabel jLabel=new JLabel("社团号:");
        jLabel .setBounds(165,20,100,25);
        JLabel jLabell=new JLabel("活动号:");
        jLabell.setBounds(290,20,100,25);
        JLabel jLabel1=new JLabel("志愿时长:");
        jLabel1.setBounds(150,50,100,25);
        JLabel jLabel2=new JLabel("任务介绍:");
        jLabel2.setBounds(150,80,100,25);
        JLabel jLabel3=new JLabel("所需人数:");
        jLabel3.setBounds(275,50,100,25);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jLabell.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField1 =new JTextField(20);
        jTextField1.setBounds(220,20,50,25);
        jTextField2 =new JTextField(20);
        jTextField2.setBounds(350,20,50,25);
        jTextField3 =new JTextField(20);
        jTextField3.setBounds(220,50,50,25);
        jTextField4=new JTextField(20);
        jTextField4.setBounds(350,50,50,25);
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
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setFont(new Font("宋体", Font.BOLD, 20));
        jButton2.setFont(new Font("宋体", Font.BOLD, 20));

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "添加":
                        try {
                            ProcessAdd(associationController);
                        } catch (SQLException ex) {
                            ProcessError(0);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询":
                        try {
                            ProcessQuery(associationController);
                        } catch (SQLException ex) {
                            ProcessError(0);
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

        JLabel jLabel4=new JLabel("请输入服务日期:");
        jLabel4.setBounds(100,165,200,25);
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

        JLabel jLabel5=new JLabel("服务场地号:");
        jLabel5.setBounds(400,165,100,25);
        jLabel5.setFont(new Font("宋体", Font.BOLD, 15));

        jTextField5=new JTextField(20);
        jTextField5.setBounds(490,165,50,25);
        jTextField5.setFont(new Font("宋体", Font.BOLD, 15));
        jPanel1.add(jLabel);
        jPanel1.add(jLabell);
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jTextField3);
        jPanel1.add(jTextField4);
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
        jPanel1.add(jTextField5);
        add(jPanel1);

        JLabel label4=new JLabel("请输入需要查询的社团名或活动名或地址:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(100,15,300,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(390,15,100,25);
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
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        int asno= 0;
        int acno= 0;
        int duration= 0;
        int people_needed= 0;
        String assignment= null;
        int year= 0;
        int month= 0;
        int day= 0;
        int lno= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            acno = Integer.parseInt(jTextField2.getText());
            duration = Integer.parseInt(jTextField3.getText());
            people_needed = Integer.parseInt(jTextField4.getText());
            assignment = jTextArea.getText();
            year = Integer.parseInt(jTextField_year.getText());
            month = Integer.parseInt(jTextField_month.getText());
            day = Integer.parseInt(jTextField_day.getText());
            lno = Integer.parseInt(jTextField5.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        MakeEmpty();
        if(!SharedFunction.JudgeDate(year,month,day)){
            System.out.println("the date is wrong");
            ProcessError(-2);
            return;
        }
        Date date=new Date(year-1900,month-1,day);
        int count=associationController.getVolunteerController().TaskAdd(associationController,asno,acno,duration,people_needed,assignment,date,lno);
        ProcessError(count);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getVolunteerController().setSelect(VolunteerController.init_volunteer);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -2:str="日期输入错误";x=180;break;
            case -1:str="申请失败，你不是该社团社长";x=120;break;
            case 0:str="地址与日期不符合活动要求或无此活动号";x=60;break;
            default:str= "申请成功,任务号为:"+count;x=100;break;
        }
        InitDemo.ProcessError(str,x);
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
        InitDemo.ShowTable(associationController.getData(),columnName,240,460,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        jTextField_query.setText(null);
        associationController.getVolunteerController().GetFieldsUseInformation(associationController,name);
        MakeTable(associationController);
    }
}
