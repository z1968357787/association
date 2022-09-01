package boundary;

import controller.AssociationController;
import controller.InvestigationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class AnswerForQuestionPanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JComboBox<String> jComboBox;//加入还是退出
    private JTextArea jTextArea;//事件标题
    private JTextField jTextField;
    private Vector<String> columnName=new Vector<>();
    public AnswerForQuestionPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void AnswerForQuestion(AssociationController associationController){
        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessAddOrDelete(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
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
        MakeTable(associationController);

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,50);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,50,1000,70);
        jPanel2.setLayout(null);
        JPanel jPanel3=new JPanel();
        jPanel3.setBounds(0,120,1000,50);
        jPanel3.setLayout(null);

        JLabel label1=new JLabel("请输入需要回答的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(50,15,200,25);
        JLabel label2=new JLabel("问题号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(300,15,80,25);

        jTextField1=new JTextField(10);
        jTextField1.setBounds(240,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(355,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));

        jComboBox=new JComboBox<>();
        jComboBox.addItem("回答");
        //jComboBox.addItem("更新");
        jComboBox.addItem("取消");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(420,15,80,25);

        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(760,15,75,25);
        jButton1.addActionListener(new MyActionListener());


        JButton jButton2=new JButton("返回");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(850,15,75,25);
        jButton2.addActionListener(new MyActionListener());

        jPanel1.add(label1);
        jPanel1.add(label2);
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);

        jPanel1.add(jComboBox);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        add(jPanel1);

        JLabel label3=new JLabel("回答框:");
        label3.setFont(new Font("宋体", Font.BOLD, 15));
        label3.setBounds(50,0,75,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(105,0,820,70);

        jPanel2.add(label3);
        jPanel2.add(jScrollPane);
        add(jPanel2);

        JLabel label4=new JLabel("请输入需要查询的社团名或问题:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(50,15,250,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(290,15,210,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());


        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel3.add(label4);
        jPanel3.add(jTextField);
        jPanel3.add(jButton3);
        jPanel3.add(jButton4);
        add(jPanel3);
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("问题号");
        columnName.add("问题内容");
        columnName.add("截止日期");
        InitDemo.ShowTable(associationController.getData(),columnName,170,530,this);
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField.setText(null);
        jTextArea.setText(null);
        jTextField2.setText(null);
    }
    private void ProcessAddOrDelete(AssociationController associationController) throws SQLException {
        String select=jComboBox.getSelectedItem().toString();
        switch (select){
            case "回答":ProcessAdd(associationController);break;
            case "取消":ProcessDelete(associationController);break;
        }
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        int asno= 0;
        int qno= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            qno = Integer.parseInt(jTextField2.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        String answer=jTextArea.getText();
        Date date=new Date();
        java.sql.Date answer_date=new java.sql.Date(date.getYear(),date.getMonth(),date.getDate());
        int count=associationController.getInvestigationController().InvestigationAdd(associationController,asno,qno,answer,answer_date);
        ProcessError(count);
        MakeEmpty();
    }
    private void ProcessDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        int qno= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            qno = Integer.parseInt(jTextField2.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        int count=associationController.getInvestigationController().InvestigationDelete(associationController,asno,qno);
        ProcessError(count);
        MakeEmpty();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getInvestigationController().GetQuestions(associationController,name);
        MakeTable(associationController);
        MakeEmpty();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getInvestigationController().setSelect(InvestigationController.init_investigation);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -2:str="重复回答或超过截止日期或无此问题";x=90;break;
            case -1:str="你不是该社团成员";x=160;break;
            case  0:str="无此问题或你未回答";x=150;break;
            default:str= "回答或删除成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);
    }
}
