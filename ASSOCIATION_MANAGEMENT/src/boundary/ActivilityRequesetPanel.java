package boundary;

import controller.ActivilityController;
import controller.AssociationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class ActivilityRequesetPanel extends JPanel {
    private JTextField jTextField;
    private JTextField jTextField1;
    private JTextArea jTextArea;
    private JTextField jTextField_query;
    private Vector<String> columnName=new Vector<>();
    public ActivilityRequesetPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void ActivilityRequeset(AssociationController associationController){

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,160);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,160,1000,50);
        jPanel2.setLayout(null);

        JLabel jLabel=new JLabel("社团号:");
        jLabel .setBounds(165,20,200,20);
        JLabel jLabel1=new JLabel("活动名字:");
        jLabel1.setBounds(150,50,200,20);
        JLabel jLabel2=new JLabel("活动介绍:");
        jLabel2.setBounds(150,80,200,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField=new JTextField(20);
        jTextField.setBounds(220,20,200,25);
        jTextField1=new JTextField(20);
        jTextField1.setBounds(220,50,200,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,80,500,80);
        JButton jButton1=new JButton("申请");
        jButton1.setBounds(845,20,80,35);
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(845,125,80,35);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setFont(new Font("宋体", Font.BOLD, 20));
        jButton2.setFont(new Font("宋体", Font.BOLD, 20));

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "申请":
                        try {
                            ProcessRequest(associationController);
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
        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());

        MakeTable(associationController);

        jPanel1.add(jLabel);
        jPanel1.add(jTextField);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jScrollPane);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);

        add(jPanel1);

        JLabel label4=new JLabel("请输入需要查询的社团名称:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(20,15,250,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(220,15,210,25);
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
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        int asno= 0;
        try {
            asno = Integer.parseInt(jTextField.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        String acname=jTextField1.getText();
        String introduction= jTextArea.getText();
        MakeEmpty();
        int count=associationController.getActivilityController().ActivilityRequest(associationController,asno,acname,introduction);
        ProcessError(count);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.init_activility);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -2:str="该社团处于审批状态";x=150;break;
            case -1:str="你不是该社团社长";x=160;break;
            default:str="申请成功，活动号为:"+count;x=120;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeEmpty(){
        jTextField.setText(null);
        jTextField1.setText(null);
        jTextArea.setText(null);
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        //rowData=associationController.getData();
        columnName.add("社团号");
        columnName.add("社团名");
        InitDemo.ShowTable(associationController.getData(),columnName,220,480,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        associationController.getActivilityController().GetAsnos(associationController,name);
        MakeTable(associationController);
        jTextField_query.setText(null);
    }
}
