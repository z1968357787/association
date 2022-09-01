package boundary;

import controller.AssociationController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private JTextField jTextField1;
    private JPasswordField jPasswordField;
    private JComboBox<String> jComboBox3;
    public LoginPanel(){
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);

    }
    public void DirectLogin(AssociationController associationController) {
        class MyActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "登录":
                        try {
                            ProcessLogin(associationController);
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

        JLabel jLabel1 = new JLabel("帐号:");
        JLabel jLabel2 = new JLabel("密码:");
        JLabel jLabel3 = new JLabel("身份:");

        jLabel1.setBounds(420, 100, 50, 25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setBounds(420, 150, 50, 25);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setBounds(420, 200, 50, 25);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));

        jTextField1 = new JTextField(20);
        jPasswordField = new JPasswordField(20);
        jPasswordField.setEchoChar('*');
        jComboBox3 = new JComboBox<>();
        jComboBox3.addItem("学生");
        jComboBox3.addItem("教师");

        jTextField1.setBounds(460, 100, 150, 25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jPasswordField.setBounds(460, 150, 150, 25);
        jPasswordField.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox3.setBounds(460, 200, 60, 25);
        jComboBox3.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton = new JButton("登录");
        jButton.setBounds(400, 300, 70, 25);
        jButton.setFont(new Font("宋体", Font.BOLD, 15));
        JButton jButton1 = new JButton("返回");
        jButton1.setBounds(550, 300, 70, 25);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton.addActionListener(new MyActionListener());
        jButton1.addActionListener(new MyActionListener());
        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jPasswordField);
        add(jLabel3);
        add(jComboBox3);
        add(jButton);
        add(jButton1);
    }
    private void ProcessError(int count){
        String str;
        switch (count){
            case 0:str="登录失败";break;
            default:str="登陆成功";break;
        }
        InitDemo.ProcessError(str,200);
    }
    private void ProcessLogin(AssociationController associationController) throws SQLException {
        String username = jTextField1.getText();
        String password = jPasswordField.getText();
        String status = jComboBox3.getSelectedItem().toString();
        if (associationController.getLoginController().IsLoginValidated(associationController, username, password, status)) {
            associationController.setSelect(AssociationController.select_mode);
            associationController.HandleMode();
            ProcessError(1);
        }
        else {
            ProcessError(0);
        }
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getLoginController().setSelect(LoginController.init_login);
        associationController.HandleMode();
    }
}
