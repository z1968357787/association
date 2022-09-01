package boundary;

import controller.ActivilityController;
import controller.AssociationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InitActivilityManagement extends JPanel {
    public InitActivilityManagement() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void InitActivility(AssociationController associationController){

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "社团活动申请":
                        try {
                            ProcessRequest(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团活动审批":
                        try {
                            ProcessApproval(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团活动信息查询":
                        try {
                            ProcessQuery(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团活动修改或取消":
                        try {
                            ProcessUpdateAndDelete(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团活动信息统计":
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

        JButton jButton1=new JButton("社团活动申请");
        jButton1.setBounds(400,25,200,50);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton2=new JButton("社团活动审批");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(400,100,200,50);

        JButton jButton3=new JButton("社团活动信息查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(400,175,200,50);

        JButton jButton4=new JButton("社团活动修改或取消");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(400,250,200,50);

        JButton jButton5=new JButton("社团活动信息统计");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(400,325,200,50);

        JButton jButton6=new JButton("返回选择界面");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(400,400,200,50);

        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());
        jButton3.addActionListener(new MyActionListener());
        jButton4.addActionListener(new MyActionListener());
        jButton5.addActionListener(new MyActionListener());
        jButton6.addActionListener(new MyActionListener());

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButton5);
        add(jButton6);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.activility_request);
        associationController.HandleMode();
    }
    private void ProcessApproval(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.activility_approval);
        associationController.HandleMode();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.activility_query);
        associationController.HandleMode();
    }
    private void ProcessUpdateAndDelete(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.activility_update_or_delete);
        associationController.HandleMode();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
    private void ProcessCount(AssociationController associationController) throws SQLException {
        associationController.getActivilityController().setSelect(ActivilityController.activility_count);
        associationController.HandleMode();
    }
}
