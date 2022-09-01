package boundary;

import controller.AssociationController;
import controller.FieldController;
import controller.MemberController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class FieldUseCountPanel extends JPanel {
    private JTextField jTextField;
    private static final int y=100;
    private static final int height=600;
    private Vector<String> columnName=new Vector<>();
    public FieldUseCountPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FieldUseCount(AssociationController associationController){
        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "统计可用场地总数":
                        try {
                            ProcessCount(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "统计各场地使用数":
                        try {
                            ProcessCount1(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "统计各社团使用场地数":
                        try {
                            ProcessCount2(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "统计各活动使用场地数":
                        try {
                            ProcessCount3(associationController);
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
        JLabel label=new JLabel("请输入需要搜索的查询内容:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,200,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(300,15,200,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField.setText("");

        JButton jButton1=new JButton("统计可用场地总数");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(100,50,200,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("统计各场地使用数");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(310,50,200,25);
        jButton2.addActionListener(new MyActionListener());

        JButton jButton3=new JButton("统计各社团使用场地数");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(520,50,200,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("统计各活动使用场地数");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(730,50,200,25);
        jButton4.addActionListener(new MyActionListener());

        JButton jButton5=new JButton("返回");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(855,15,75,25);
        jButton5.addActionListener(new MyActionListener());

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,100);
        jPanel.setLayout(null);


        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(jButton4);
        jPanel.add(jButton5);

        add(jPanel);
    }
    private void ProcessCount(AssociationController associationController) throws SQLException {
        associationController.getFieldController().FieldUseCount(associationController);
        MakeTable(associationController);
    }
    private void ProcessCount1(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getFieldController().FieldUseCount1(associationController,name);
        MakeTable1(associationController);
        jTextField.setText("");
    }
    private void ProcessCount2(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getFieldController().FieldUseCount2(associationController,name);
        MakeTable2(associationController);
        jTextField.setText("");
    }
    private void ProcessCount3(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getFieldController().FieldUseCount3(associationController,name);
        MakeTable3(associationController);
        jTextField.setText("");
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.init_field);
        associationController.HandleMode();
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        columnName.add("可用地址总数");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
    private void MakeTable1(AssociationController associationController){
        columnName.clear();
        columnName.add("地址号");
        columnName.add("地址名");
        columnName.add("使用次数");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
    private void MakeTable2(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("地址号");
        columnName.add("地址名");
        columnName.add("使用次数");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
    private void MakeTable3(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        columnName.add("活动号");
        columnName.add("活动名");
        columnName.add("地址号");
        columnName.add("地址名");
        columnName.add("使用次数");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
}
