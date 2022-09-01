package boundary;

import controller.ActivilityController;
import controller.AssociationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class ActivilityUpdatePanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextArea jTextArea;
    private JComboBox<String> jComboBox;
    private JTextField jTextField_query;
    private Vector<String> columnName=new Vector<>();
    public ActivilityUpdatePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void ActivilityUpdate(AssociationController associationController){

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,250);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,250,1000,450);
        jPanel2.setLayout(null);

        JLabel label=new JLabel("请输入需要修改或删除的社团号:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(150,15,250,25);
        JLabel label2=new JLabel("活动号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(460,15,65,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(390,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(520,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox=new JComboBox<>();
        jComboBox.addItem("更新");
        jComboBox.addItem("删除");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(580,15,90,25);

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ProcessError(0);
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
        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(760,15,75,25);
        jButton1.addActionListener(new MyActionListener());


        JButton jButton2=new JButton("返回");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(850,15,75,25);
        jButton2.addActionListener(new MyActionListener());

        add(label);
        add(label2);
        add(jTextField1);
        add(jTextField2);
        add(jComboBox);
        add(jButton1);
        add(jButton2);

        JLabel jLabel=new JLabel("注：如果要修改活动信息，则需要填写以下信息，若某部分不修改，可该部分不填");
        jLabel.setBounds(200,50,600,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        JLabel jLabel1=new JLabel("活动名字:");
        jLabel1.setBounds(150,100,200,20);
        JLabel jLabel2=new JLabel("活动介绍:");
        jLabel2.setBounds(150,150,200,20);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3=new JTextField(20);
        jTextField3.setBounds(220,100,200,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,150,500,80);

        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jPanel1.add(jLabel);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField3);
        jPanel1.add(jLabel2);
        jPanel1.add(jScrollPane);
        add(jPanel1);

        MakeTable(associationController);

        JLabel label4=new JLabel("请输入需要查询的社团名或活动名:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(50,15,300,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(300,15,210,25);
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
    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        int acno= 0;
        try {
            asno = Integer.parseInt(jTextField1.getText());
            acno = Integer.parseInt(jTextField2.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        String select=jComboBox.getSelectedItem().toString();
        switch (select){
            case "更新":ProcessUpdate(asno,acno,associationController);break;
            case "删除":ProcessDelete(asno,acno,associationController);break;
        }
        MakeEmpty();
        ProcessQuery(associationController);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.init_activility);
        associationController.HandleMode();
    }
    private void ProcessUpdate(int asno,int acno,AssociationController associationController) throws SQLException {
        String name=jTextField3.getText();
        String introduction= jTextArea.getText();
        int count=associationController.getActivilityController().ProcessUpdate(asno,acno,name,introduction,associationController);
        ProcessError(count);
    }
    private void ProcessDelete(int asno,int acno,AssociationController associationController) throws SQLException {
        int count=associationController.getActivilityController().ProcessDelete(asno,acno,associationController);
        ProcessError(count);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case 0:str="该活动号不存在";x=170;break;
            case -1:str="你不是该社团主管教师";x=140;break;
            default:str="修改或删除成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeEmpty(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextArea.setText("");
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        //rowData=associationController.getData();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        InitDemo.ShowTable(associationController.getData(),columnName,300,400,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        associationController.getActivilityController().GetAcnos(associationController,name);
        MakeTable(associationController);
        jTextField_query.setText(null);
    }
}
