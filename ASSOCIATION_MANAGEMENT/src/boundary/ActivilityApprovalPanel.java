package boundary;

import controller.ActivilityController;
import controller.AssociationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class ActivilityApprovalPanel extends JPanel {
    private Vector<String> columnName=new Vector<>();
    private JTextField jTextField1;//社团号
    private JTextField jTextField2;//活动号
    private JTextField jTextField;
    private JComboBox<String> jComboBox;//已通过还是已拒绝
    public ActivilityApprovalPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void ActivilityApproval(AssociationController associationController){

        MakeTable(associationController);

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,50);
        jPanel.setLayout(null);

        JLabel label1=new JLabel("请输入需要审批的社团号:");
        label1.setFont(new Font("宋体", Font.BOLD, 15));
        label1.setBounds(100,15,200,25);
        JLabel label2=new JLabel("活动号:");
        label2.setFont(new Font("宋体", Font.BOLD, 15));
        label2.setBounds(350,15,80,25);
        jTextField1=new JTextField(10);
        jTextField1.setBounds(290,15,50,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2=new JTextField(10);
        jTextField2.setBounds(415,15,50,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));

        jComboBox=new JComboBox<>();
        jComboBox.addItem("已通过");
        jComboBox.addItem("已拒绝");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(475,15,100,25);

        class MyActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessApproval(associationController);
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
        jPanel.add(jComboBox);
        jPanel.add(jButton1);
        jPanel.add(jButton2);

        add(jPanel);

        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,50,1000,50);
        jPanel2.setLayout(null);

        JLabel label=new JLabel("请输入需要查询的社团名或活动名:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,300,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(350,15,210,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label);
        jPanel2.add(jTextField);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);
    }

    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getActivilityController().GetActivilities(associationController,"申请中",name);
        MakeTable(associationController);
        jTextField.setText(null);
    }

    private void MakeTable(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        columnName.add("申请状态");
        columnName.add("活动介绍");
        InitDemo.ShowTable(associationController.getData(),columnName,100,600,this);
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
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
        String approval_select=jComboBox.getSelectedItem().toString();
        int count=associationController.getActivilityController().ActivilityApproval(associationController,asno,acno,approval_select);
        ProcessError(count);
        ProcessQuery(associationController);
        MakeEmpty();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.init_activility);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        String str;
        int x;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case 0:str="该活动号不存在";x=170;break;
            case -1:str="你不是该社团主管教师";x=120;break;
            default:str="审批成功";x=200;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
    }
}
