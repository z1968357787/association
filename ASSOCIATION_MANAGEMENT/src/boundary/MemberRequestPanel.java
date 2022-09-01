package boundary;

import controller.AssociationController;
import controller.MemberController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class MemberRequestPanel extends JPanel {

    private Vector<String> columnName=new Vector<>();
    private JTextField jTextField2;
    private JTextField jTextField;
    private JComboBox<String> jComboBox;
    public MemberRequestPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }

    public void MemberRequest(AssociationController associationController){

        MakeTable(associationController);

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,50);
        jPanel.setLayout(null);

        JLabel label=new JLabel("请输入需要加入或退出的社团号:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,250,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(350,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox=new JComboBox<>();
        jComboBox.addItem("加入");
        jComboBox.addItem("退出");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(460,15,90,25);

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

        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jComboBox);
        jPanel.add(jButton1);
        jPanel.add(jButton2);

        add(jPanel);

        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,50,1000,50);
        jPanel2.setLayout(null);

        JLabel jLabel=new JLabel("请输入需要查询的社团名称:");
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel.setBounds(100,15,250,25);
        jTextField2=new JTextField(10);
        jTextField2.setBounds(300,15,210,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(jLabel);
        jPanel2.add(jTextField2);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);
    }

    private void MakeTable(AssociationController associationController){
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("社团状态");
        columnName.add("创建日期");
        columnName.add("社团介绍");
        columnName.add("主管老师名字");
        columnName.add("社长名字");
        InitDemo.ShowTable(associationController.getData(),columnName,100,600,this);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        int asno= 0;
        String request_select= null;
        try {
            asno = Integer.parseInt(jTextField.getText());
            request_select = jComboBox.getSelectedItem().toString();
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        int count=associationController.getMemberController().MemberRequest(associationController,asno,request_select);
        ProcessError(count);
        jTextField.setText(null);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getMemberController().setSelect(MemberController.init_member);
        associationController.HandleMode();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField2.getText();
        associationController.getAssociationManagementController().GetAssociations(associationController,name,"已通过");
        MakeTable(associationController);
        jTextField2.setText(null);
    }
    private void ProcessError(int count){
        int x=0;
        String str;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case 0:str="该社团号不存在或你不是学生";x=110;break;
            case -1:str="你已经在此社团";x=170;break;
            case -2:str="你并不在此社团";x=170;break;
            default:str="申请成功";x=200;break;
        }
        InitDemo.ProcessError(str,x);
    }
}
