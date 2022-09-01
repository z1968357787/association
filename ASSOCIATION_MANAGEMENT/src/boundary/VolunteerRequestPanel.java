package boundary;

import controller.AssociationController;
import controller.VolunteerController;
import entity.ActilityLoaction;
import entity.Association;
import entity.Participate;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class VolunteerRequestPanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField;
    private JComboBox<String> jComboBox;//加入还是退出

    private Vector<String> columnName=new Vector<>();

    public VolunteerRequestPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void VolunteerRequest(AssociationController associationController){

        MakeTable(associationController);

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,50);
        jPanel.setLayout(null);

        JLabel label1=new JLabel("请输入申请场地的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(100,15,200,25);
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
        jComboBox.addItem("报名");
        jComboBox.addItem("取消");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(600,15,100,25);

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessRequest(associationController);
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
                    case "查询":
                        try {
                            ProcessQuery(associationController);
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

        jPanel.add(label1);
        jPanel.add(jTextField1);
        jPanel.add(label2);
        jPanel.add(jTextField2);
        jPanel.add(label3);
        jPanel.add(jTextField3);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jComboBox);
        add(jPanel);

        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,50,1000,50);
        jPanel2.setLayout(null);

        JLabel label4=new JLabel("请输入需要查询的社团名或活动名或志愿任务:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(100,15,350,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(430,15,210,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
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
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        String select=jComboBox.getSelectedItem().toString();
        switch(select){
            case "报名":ProcessAdd(associationController);break;
            case "取消":ProcessDelete(associationController);break;
        }
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
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
        MakeEmpty();
        int count=associationController.getVolunteerController().VolunteerAdd(associationController,asno,acno,tnum);
        associationController.HandleMode();
        ProcessError(count);
    }
    private void ProcessDelete(AssociationController associationController) throws SQLException {
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
        MakeEmpty();
        int count=associationController.getVolunteerController().VolunteerDelete(associationController,asno,acno,tnum);
        associationController.HandleMode();
        ProcessError(count);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getVolunteerController().setSelect(VolunteerController.init_volunteer);
        associationController.HandleMode();
    }
    private void MakeTable(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        columnName.add("任务号");
        columnName.add("任务简述");
        columnName.add("志愿时长");
        columnName.add("现有人数");
        columnName.add("需要人数");
        columnName.add("服务地址");
        columnName.add("服务日期");
        InitDemo.ShowTable(associationController.getData(),columnName,100,600,this);
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getVolunteerController().GetTasks(associationController,name);
        MakeTable(associationController);
        jTextField.setText(null);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;InitDemo.ProcessError(str,x);break;
            case -1:str="申请失败，你不是该社团成员";x=100;InitDemo.ProcessError(str,x);break;
            case  0:str="申请失败，无此活动号或任务号或已在此任务或不在此任务或人数已满";x=20;InitDemo.ProcessError(str,x,700);break;
            default:str= "申请成功";x=200;InitDemo.ProcessError(str,x);break;
        }

    }
}
