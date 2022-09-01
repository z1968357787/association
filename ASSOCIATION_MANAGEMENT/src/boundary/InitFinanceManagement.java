package boundary;

import controller.AssociationController;
import controller.FinanceController;
import controller.MemberController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InitFinanceManagement extends JPanel{
    public InitFinanceManagement() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void InitFinance(AssociationController associationController){

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "财务活动添加":
                        try {
                            ProcessAdd(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "财务活动修改或删除":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "财务活动查询":
                        try {
                            ProcessQuery(associationController);
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

        JButton jButton1=new JButton("财务活动添加");
        jButton1.setBounds(400,25,200,50);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton2=new JButton("财务活动修改或删除");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(400,100,200,50);

        JButton jButton3=new JButton("财务活动查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(400,175,200,50);

        JButton jButton4=new JButton("返回选择界面");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(400,400,200,50);

        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());
        jButton3.addActionListener(new MyActionListener());
        jButton4.addActionListener(new MyActionListener());

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        associationController.getFinanceController().setSelect(FinanceController.finance_add);
        associationController.HandleMode();
    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        associationController.getFinanceController().setSelect(FinanceController.finance_update_or_delete);
        associationController.HandleMode();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        associationController.getFinanceController().setSelect(FinanceController.finance_query);
        associationController.HandleMode();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
}
