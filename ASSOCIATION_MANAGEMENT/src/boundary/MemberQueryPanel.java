package boundary;

import controller.AssociationController;
import controller.AssociationManagementController;
import controller.MemberController;
import entity.Association;
import entity.Participate;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class MemberQueryPanel extends JPanel {
    Vector<String> columnName=new Vector<>();
    private JTextField jTextField;
    public MemberQueryPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void MemberQuery(AssociationController associationController){

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "确定":
                        try {
                            ProcessQuery1(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询所有":
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

        JLabel label=new JLabel("请输入需要查询的社团名或成员名:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,250,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(345,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField.setText("");
        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(455,15,75,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("查询所有");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(550,15,100,25);
        jButton2.addActionListener(new MyActionListener());

        JButton jButton3=new JButton("返回");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(700,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,50);
        jPanel.setLayout(null);


        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);

        add(jPanel);
    }

    private void MakeTable(AssociationController associationController){
        //rowData.clear();;
        columnName.clear();
        columnName.add("社团名");
        columnName.add("成员学号");
        columnName.add("成员名");
        columnName.add("院系");
        columnName.add("学分");
        columnName.add("加入状态");
        InitDemo.ShowTable(associationController.getData(),columnName,50,650,this);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        jTextField.setText("");
        associationController.getMemberController().GetMembersInformation(associationController,name);
        MakeTable(associationController);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        associationController.getMemberController().GetMembersInformation(associationController,"");
        MakeTable(associationController);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getMemberController().setSelect(MemberController.init_member);
        associationController.HandleMode();
    }
}
