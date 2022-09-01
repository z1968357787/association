package boundary;

import controller.AssociationController;
import controller.VoteController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class VoteForOptionsPanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField;
    private JComboBox<String> jComboBox;//加入还是退出

    private Vector<String> columnName=new Vector<>();
    public VoteForOptionsPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);

    }
    public void VoteForOptions(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessVote(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
                            System.out.println(System.err);
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
        jPanel2.setBounds(0,50,1000,50);
        jPanel2.setLayout(null);

        JLabel label1=new JLabel("请输入需要投票的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(50,15,200,25);
        JLabel label2=new JLabel("事件号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(300,15,80,25);
        JLabel label3=new JLabel("选项号:");
        label3.setFont(new Font("宋体", Font.BOLD, 15));
        label3.setBounds(425,15,80,25);
        JLabel label4=new JLabel("投票数:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(545,15,80,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(240,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(355,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3=new JTextField(10);
        jTextField3.setBounds(480,15,50,25);
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField4=new JTextField(10);
        jTextField4.setBounds(605,15,50,25);
        jTextField4.setFont(new Font("宋体", Font.BOLD, 15));

        jComboBox=new JComboBox<>();
        jComboBox.addItem("投票");
        //jComboBox.addItem("更新");
        jComboBox.addItem("取消");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(670,15,80,25);

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
        jPanel1.add(label3);
        jPanel1.add(label4);
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jTextField3);
        jPanel1.add(jTextField4);
        jPanel1.add(jComboBox);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        add(jPanel1);

        JLabel label5=new JLabel("请输入需要查询的社团名或事件名或选项名:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(50,15,350,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(360,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());


        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label5);
        jPanel2.add(jTextField);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("事件号");
        columnName.add("事件标题");
        columnName.add("选项号");
        columnName.add("选项名称");
        columnName.add("限投票数");
        columnName.add("截止日期");
        InitDemo.ShowTable(associationController.getData(),columnName,100,600,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getVoteController().GetOptions(associationController,name);
        MakeTable(associationController);
        jTextField.setText(null);
    }
    private void ProcessVote(AssociationController associationController) throws SQLException {
        int asno= 0;
        int eno= 0;
        int ono= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            eno = Integer.parseInt(jTextField2.getText());
            ono = Integer.parseInt(jTextField3.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        String select=jComboBox.getSelectedItem().toString();
        switch (select){
            case "投票":ProcessAdd(associationController,asno,eno,ono);break;
            //case "更新":ProcessUpdate(associationController,asno,eno,ono);break;
            case "取消":ProcessDelete(associationController,asno,eno,ono);break;
        }
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getVoteController().setSelect(VoteController.init_vote);
        associationController.HandleMode();
    }
    private void ProcessAdd(AssociationController associationController,int asno,int eno,int ono) throws SQLException {
        int poll= 0;
        try {
            poll = Integer.parseInt(jTextField4.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        Date date=new Date();
        java.sql.Date vote_date=new java.sql.Date(date.getYear(),date.getMonth(),date.getDate());
        int count=associationController.getVoteController().VoteAdd(associationController,asno,eno,ono,poll,vote_date);
        ProcessError(count);
        MakeEmpty();
    }
    /*private void ProcessUpdate(AssociationController associationController,int asno,int eno,int ono) throws SQLException {
        int poll= Integer.parseInt(jTextField4.getText());
        associationController.getVoteController().VoteUpdate(associationController,asno,eno,ono,poll);
        MakeEmpty();
    }*/
    private void ProcessDelete(AssociationController associationController,int asno,int eno,int ono) throws SQLException {
        int count=associationController.getVoteController().VoteDelete(associationController,asno,eno,ono);
        ProcessError(count);
        MakeEmpty();
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);
        jTextField.setText(null);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;InitDemo.ProcessError(str,x);break;
            case -2:str="重复投票或超过截止日期或超过限定票数或无此事件或选项";x=60;InitDemo.ProcessError(str,x,700);break;
            case -1:str="你不是该社团成员";x=160;InitDemo.ProcessError(str,x);break;
            case  0:str="无此事件或选项";x=170;InitDemo.ProcessError(str,x);break;
            default:str= "投票或取消成功";x=170;InitDemo.ProcessError(str,x);break;
        }

    }
}
