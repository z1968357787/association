package boundary;

import controller.AssociationController;
import controller.AssociationManagementController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InitAssociationManagement extends JPanel {
    public InitAssociationManagement(){
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }

    public void ShowAssociation(AssociationController associationController){
        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String label=e.getActionCommand();
                switch (label){
                    case "社团申请":
                        try {
                            ProcessRequest(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团审批":
                        try {
                            ProcessApproval(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团查询":
                        try {
                            ProcessQuery(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团更新与删除":
                        try {
                            ProcessUpdateAndDelete(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "社团统计":
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
                //associationController.HandleMode();
            }
        }



        JButton jButton1=new JButton("社团申请");
        jButton1.setBounds(420,25,180,50);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton2=new JButton("社团审批");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(420,100,180,50);

        JButton jButton3=new JButton("社团查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(420,175,180,50);

        JButton jButton4=new JButton("社团统计");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(420,250,180,50);


        JButton jButton5=new JButton("社团更新与删除");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(420,325,180,50);

        JButton jButton6=new JButton("返回选择界面");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(420,400,180,50);


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
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.association_request);
        associationController.HandleMode();
    }

    private void ProcessApproval(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.process_approval);
        associationController.HandleMode();
    }

    private void ProcessQuery(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.process_query);
        associationController.HandleMode();
    }

    private void ProcessUpdateAndDelete(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.process_update_and_delete);
        associationController.HandleMode();
    }

    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
    private void ProcessCount(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.process_count);
        associationController.HandleMode();
    }

}
