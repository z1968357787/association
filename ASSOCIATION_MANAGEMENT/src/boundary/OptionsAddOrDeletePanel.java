package boundary;

import controller.AssociationController;
import controller.VoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class OptionsAddOrDeletePanel extends JPanel {

    private Vector<String> columnName=new Vector<>();
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField;
    public OptionsAddOrDeletePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void OptionsAddOrDelete(AssociationController associationController){

        class MyActionListener implements ActionListener{

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
                    case "删除":
                        try {
                            ProcessDelete(associationController);
                        } catch (SQLException ex) {
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

        JPanel jPanel1=new JPanel();
        JPanel jPanel2=new JPanel();
        JPanel jPanel3=new JPanel();
        JPanel jPanel4=new JPanel();
        jPanel1.setBounds(0,0,1000,50);
        jPanel2.setBounds(0,50,1000,50);
        jPanel3.setBounds(0,100,1000,50);
        jPanel4.setBounds(0,400,1000,50);
        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel3.setLayout(null);
        jPanel4.setLayout(null);

        JLabel label1=new JLabel("请输入添加选项的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(50,15,200,25);
        JLabel label2=new JLabel("事件号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(300,15,80,25);
        JLabel label3=new JLabel("选项内容:");
        label3.setFont(new Font("宋体", Font.BOLD, 15));
        label3.setBounds(425,15,100,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(240,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(365,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3=new JTextField(10);
        jTextField3.setBounds(500,15,250,25);
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton1=new JButton("添加");
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
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jTextField3);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        add(jPanel1);

        JLabel label4=new JLabel("请输入删除选项的社团号:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(50,15,200,25);
        JLabel label5=new JLabel("事件号:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(300,15,80,25);
        JLabel label6=new JLabel("选项号:");
        label6.setFont(new Font("宋体", Font.BOLD, 15));
        label6.setBounds(440,15,100,25);
        jTextField4=new JTextField(10);
        jTextField4.setBounds(240,15,50,25);
        jTextField4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField5=new JTextField(10);
        jTextField5.setBounds(365,15,50,25);
        jTextField5.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField6=new JTextField(10);
        jTextField6.setBounds(500,15,50,25);
        jTextField6.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("删除");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());


        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label4);
        jPanel2.add(label5);
        jPanel2.add(label6);
        jPanel2.add(jTextField4);
        jPanel2.add(jTextField5);
        jPanel2.add(jTextField6);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        JLabel label7=new JLabel("请输入需要查询的社团名或事件名:");
        label7.setFont(new Font("宋体", Font.BOLD, 15));
        label7.setBounds(50,15,300,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(300,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        JButton jButton5=new JButton("查询1");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(760,15,75,25);
        jButton5.addActionListener(new MyActionListener());


        JButton jButton6=new JButton("返回");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(850,15,75,25);
        jButton6.addActionListener(new MyActionListener());

        jPanel3.add(label7);
        jPanel3.add(jTextField);
        jPanel3.add(jButton5);
        jPanel3.add(jButton6);
        add(jPanel3);

        JLabel label8=new JLabel("请输入需要查询的社团名或事件名或选项名:");
        label8.setFont(new Font("宋体", Font.BOLD, 15));
        label8.setBounds(50,15,350,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(360,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        JButton jButton7=new JButton("查询2");
        jButton7.setFont(new Font("宋体", Font.BOLD, 15));
        jButton7.setBounds(760,15,75,25);
        jButton7.addActionListener(new MyActionListener());


        JButton jButton8=new JButton("返回");
        jButton8.setFont(new Font("宋体", Font.BOLD, 15));
        jButton8.setBounds(850,15,75,25);
        jButton8.addActionListener(new MyActionListener());

        jPanel4.add(label8);
        jPanel4.add(jTextField);
        jPanel4.add(jButton7);
        jPanel4.add(jButton8);
        add(jPanel4);
    }
    private void MakeTable1(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("事件号");
        columnName.add("事件标题");
        columnName.add("限投票数");
        columnName.add("截止日期");
        InitDemo.ShowTable(associationController.getData(),columnName,150,250,this);
    }
    private void MakeTable2(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("事件号");
        columnName.add("事件标题");
        columnName.add("选项号");
        columnName.add("选项内容");
        InitDemo.ShowTable(associationController.getData2(),columnName,450,250,this);
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        int asno= 0;
        int eno= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            eno = Integer.parseInt(jTextField2.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        String content=jTextField3.getText();
        int count=associationController.getVoteController().OptionAdd(associationController,asno,eno,content);
        ProcessError(count);
        MakeEmpty();
        ProcessQuery2(associationController);
    }
    private void ProcessDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        int eno= 0;
        int ono= 0;
        try {
            asno = Integer.parseInt(jTextField4.getText());
            eno = Integer.parseInt(jTextField5.getText());
            ono = Integer.parseInt(jTextField6.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        int count=associationController.getVoteController().OptionDelete(associationController,asno,eno,ono);
        ProcessError(count);
        MakeEmpty();
        ProcessQuery2(associationController);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getVoteController().GetEvents(associationController,name);
        MakeTable1(associationController);
        MakeEmpty();
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getVoteController().GetOnos(associationController,name);
        MakeTable2(associationController);
        MakeEmpty();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getVoteController().setSelect(VoteController.init_vote);
        associationController.HandleMode();
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);
        jTextField5.setText(null);
        jTextField6.setText(null);
        jTextField.setText(null);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -1:str="申请失败，你不是该社团社长";x=120;break;
            case 0:str="无此事件或选项";x=170;break;
            case 1:str="删除成功";x=200;break;
            default:str= "申请成功，选项号为:"+count;x=120;break;
        }
        InitDemo.ProcessError(str,x);
    }
}
