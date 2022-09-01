package boundary;

import controller.AssociationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class BBSControllPanel extends JPanel {
    private JTextField jTextField1;
    private JTextArea jTextArea;//回答框
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField;//查找框
    private Vector<Vector<Object>> rowData=new Vector<>();
    private Vector<String> columnName=new Vector<>();
    public BBSControllPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void BBSControll(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "发言":
                        try {
                            ProcessAdd(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "撤回":
                        try {
                            ProcessDelete(associationController);
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
        jPanel1.setBounds(0,0,1000,80);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,80,1000,40);
        jPanel2.setLayout(null);
        JPanel jPanel3=new JPanel();
        jPanel3.setBounds(0,120,1000,50);
        jPanel3.setLayout(null);

        JLabel label1=new JLabel("请输入需要留言的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(50,10,200,25);
        JLabel label2=new JLabel("留言框:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(300,10,80,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(240,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(355,10,395,70);

        JButton jButton1=new JButton("发言");
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
        jPanel1.add(jScrollPane);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        add(jPanel1);

        JLabel label3=new JLabel("请输入需要撤回的社团号:");
        label3.setFont(new Font("宋体", Font.BOLD, 15));
        label3.setBounds(50,15,200,25);
        JLabel label4=new JLabel("留言号:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(300,15,80,25);

        jTextField2=new JTextField(10);
        jTextField2.setBounds(240,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3=new JTextField(10);
        jTextField3.setBounds(355,15,50,25);
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("撤回");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());


        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label3);
        jPanel2.add(label4);
        jPanel2.add(jTextField2);
        jPanel2.add(jTextField3);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        JLabel label5=new JLabel("请输入需要查询的社团名或聊天记录:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(50,15,300,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(310,15,210,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton5=new JButton("查询");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(760,15,75,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("返回");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(850,15,75,25);
        jButton6.addActionListener(new MyActionListener());

        jPanel3.add(label5);
        jPanel3.add(jTextField);
        jPanel3.add(jButton5);
        jPanel3.add(jButton6);
        add(jPanel3);
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        rowData=associationController.getData();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("学号");
        columnName.add("学生姓名");
        columnName.add("留言号");
        columnName.add("留言内容");
        columnName.add("留言日期");
        InitDemo.ShowTable(rowData,columnName,170,530,this);
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField.setText(null);
        jTextArea.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        int asno= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        String content=jTextArea.getText();
        Date date=new Date();
        java.sql.Date speak_date=new java.sql.Date(date.getYear(),date.getMonth(),date.getDate());
        int count=associationController.getBbsController().BBSAdd(associationController,asno,content,speak_date);
        ProcessError(count);
        ProcessQuery(associationController);
        MakeEmpty();
    }
    private void ProcessDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        int bno= 0;
        try {
            asno = Integer.parseInt(jTextField2.getText());
            bno = Integer.parseInt(jTextField3.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        int count=associationController.getBbsController().BBSDelete(associationController,asno,bno);
        ProcessError(count);
        ProcessQuery(associationController);
        MakeEmpty();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getBbsController().GetBBS(associationController,name);
        MakeTable(associationController);
        MakeEmpty();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -1:str="发言失败，你不是该社团成员";x=100;break;
            case  0:str="撤回失败，无此发言号";x=130;break;
            case  1:str="撤回成功";x=200;break;
            default:str= "发言成功，发言号为:"+count;x=120;break;
        }
        InitDemo.ProcessError(str,x);
    }
}
