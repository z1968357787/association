package boundary;

import controller.AssociationController;
import controller.AssociationManagementController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class AssociationQueryPanel extends JPanel {
    private JTextField jTextField;
    private Vector<String> columnName=new Vector<>();
    //Object columnNames[] = { "社团号", "社团名", "社团状态","创建日期","社团介绍","主管老师名字","社长名字" };
    public AssociationQueryPanel() {
        setLayout(null);
        setBounds(0,0,InitDemo.width,InitDemo.height);
    }
    public void AssociationQuery(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
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


        JLabel label=new JLabel("请输入需要查询的社团名:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,200,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(290,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField.setText("");
        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(400,15,75,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("查询所有");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(500,15,100,25);
        jButton2.addActionListener(new MyActionListener());

        JButton jButton3=new JButton("返回");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(650,15,75,25);
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
        //rowData.clear();
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("社团状态");
        columnName.add("创建日期");
        columnName.add("社团介绍");
        columnName.add("主管老师名字");
        columnName.add("社长名字");
        InitDemo.ShowTable(associationController.getData(),columnName,50,650,this);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String asname=jTextField.getText();
        jTextField.setText("");
        associationController.getAssociationManagementController().GetAssociations(associationController,asname,"已通过");
        MakeTable(associationController);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().GetAssociations(associationController,"","已通过");
        MakeTable(associationController);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.init_association);
        associationController.HandleMode();
    }
}
