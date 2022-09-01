package boundary;

import controller.AssociationController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InitPanel extends JPanel {
    public InitPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }

    public void InitInterface(AssociationController associationController){
        class MyActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "直接登录":
                        try {
                            ProcessLogin(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "注册登录":
                        try {
                            ProcessRegister(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "游客登录":
                        try {
                            ProcessVisitor(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "退出系统":
                        try {
                            ProcessExit(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }

            }
        }

        JButton jButton1=new JButton("直接登录");
        jButton1.addActionListener(new MyActionListener());
        jButton1.setBounds(450,50,120,50);
        JButton jButton2=new JButton("注册登录");
        jButton2.addActionListener(new MyActionListener());
        jButton2.setBounds(450,150,120,50);
        JButton jButton3=new JButton("游客登录");
        jButton3.addActionListener(new MyActionListener());
        jButton3.setBounds(450,250,120,50);
        JButton jButton4=new JButton("退出系统");
        jButton4.addActionListener(new MyActionListener());
        jButton4.setBounds(450,350,120,50);

        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
    }
    private void ProcessLogin(AssociationController associationController) throws SQLException {
        associationController.getLoginController().setSelect(LoginController.direct_login);
        associationController.HandleMode();
    }
    private void ProcessRegister(AssociationController associationController) throws SQLException {
        associationController.getLoginController().setSelect(LoginController.register_login);
        associationController.HandleMode();
    }
    private void ProcessExit(AssociationController associationController) throws SQLException {
        associationController.getLoginController().setSelect(LoginController.exit_login);
        associationController.HandleMode();
    }
    private void ProcessVisitor(AssociationController associationController) throws SQLException {
        associationController.getLoginController().setSelect(LoginController.visitor_login);
        associationController.HandleMode();
    }
}
