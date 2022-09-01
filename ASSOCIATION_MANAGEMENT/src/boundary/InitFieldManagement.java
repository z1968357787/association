package boundary;

import controller.AssociationController;
import controller.FieldController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InitFieldManagement extends JPanel {
    public InitFieldManagement() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void InitField(AssociationController associationController){
        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "可用场地添加":
                        try {
                            ProcessAdd(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "可用场地修改或删除":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "活动场地申请或取消":
                        try {
                            ProcessRequest(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "活动场地审批":
                        try {
                            ProcessApproval(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "活动场地信息查询":
                        try {
                            ProcessQuery(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "活动场地信息统计":
                        try {
                            ProcessCount(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "返回选择界面":
                        try {
                            ProcessReturn(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        }

        JButton jButton1=new JButton("可用场地添加");
        jButton1.setBounds(200,25,200,50);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton2=new JButton("可用场地修改或删除");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(200,100,200,50);

        JButton jButton3=new JButton("活动场地申请或取消");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(200,175,200,50);

        JButton jButton4=new JButton("活动场地审批");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(600,20,200,50);


        JButton jButton5=new JButton("活动场地信息查询");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(600,100,200,50);

        JButton jButton6=new JButton("返回选择界面");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(600,175,200,50);

        JButton jButton7=new JButton("活动场地信息统计");
        jButton7.setFont(new Font("宋体", Font.BOLD, 15));
        jButton7.setBounds(200,250,200,50);

        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());
        jButton3.addActionListener(new MyActionListener());
        jButton4.addActionListener(new MyActionListener());
        jButton5.addActionListener(new MyActionListener());
        jButton6.addActionListener(new MyActionListener());
        jButton7.addActionListener(new MyActionListener());

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButton5);
        add(jButton6);
        add(jButton7);
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.field_add);
        associationController.HandleMode();
    }
    /*
     *可用场地修改或删除
     */
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.field_update_or_delete);
        associationController.HandleMode();
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.field_request);
        associationController.HandleMode();
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.field_approval);
        associationController.HandleMode();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.field_query);
        associationController.HandleMode();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
    private void ProcessCount(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.field_count);
        associationController.HandleMode();
    }
}
