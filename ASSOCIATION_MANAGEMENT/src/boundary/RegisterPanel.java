package boundary;

import controller.AssociationController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterPanel extends Panel {
    private JTextField jTextField1;
    private JPasswordField jPasswordField;
    private JComboBox<String> jComboBox3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    public RegisterPanel() {
        setLayout(null);
        setBounds(0,0,InitDemo.width,InitDemo.height);
    }
    public void RegisterLogin(AssociationController associationController){
        JLabel jLabel1=new JLabel("帐号:");
        JLabel jLabel2=new JLabel("密码:");
        JLabel jLabel3=new JLabel("身份:");
        JLabel jLabel4=new JLabel("学号或教工号:");
        JLabel jLabel5=new JLabel("姓名:");
        JLabel jLabel6=new JLabel("院系:");
        JLabel jLabel7=new JLabel("工资或绩点:");
        JLabel jLabel8=new JLabel("注：如果您是学生，请输入百分制绩点，如果您是教师，请输入工资");

        jLabel1.setBounds(420,25,50,25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setBounds(420,75,50,25);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setBounds(420,125,50,25);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel4.setBounds(360,175,120,25);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel5.setBounds(420,225,50,25);
        jLabel5.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel6.setBounds(420,275,50,25);
        jLabel6.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel7.setBounds(375,325,100,25);
        jLabel7.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel8.setBounds(250,375,500,25);
        jLabel8.setFont(new Font("宋体", Font.BOLD, 15));

        jTextField1=new JTextField();
        jPasswordField = new JPasswordField();
        jPasswordField.setEchoChar('*');
        jComboBox3=new JComboBox<String>();
        jComboBox3.addItem("学生");
        jComboBox3.addItem("教师");
        jTextField4=new JTextField();
        jTextField5=new JTextField();
        jTextField6=new JTextField();
        jTextField7=new JTextField();

        jTextField1.setBounds(460,25,150,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jPasswordField.setBounds(460,75,150,25);
        jPasswordField.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox3.setBounds(460,125,60,25);
        jComboBox3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField4.setBounds(460,175,150,25);
        jTextField4.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField5.setBounds(460,225,150,25);
        jTextField5.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField6.setBounds(460,275,150,25);
        jTextField6.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField7.setBounds(460,325,70,25);
        jTextField7.setFont(new Font("宋体", Font.BOLD, 15));


        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "注册并登录":
                        try {
                            ProcessRegister(associationController);
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
                }
            }
        }

        JButton jButton1=new JButton("注册并登录");
        jButton1.setBounds(350,425,150,25);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(550,425,70,25);
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());


        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jPasswordField);
        add(jLabel3);
        add(jComboBox3);
        add(jLabel4);
        add(jTextField4);
        add(jLabel5);
        add(jTextField5);
        add(jLabel6);
        add(jTextField6);
        add(jLabel7);
        add(jTextField7);
        add(jLabel8);
        add(jButton1);
        add(jButton2);
    }
    private void ProcessError(int count){
        String str;
        int x;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case 0:str="注册失败，学号重复或用户名重复";x=70;break;
            default:str="注册成功";x=200;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void ProcessRegister(AssociationController associationController) throws SQLException {
        String username= null;
        String password= null;
        String status= null;
        int id= 0;
        String name= null;
        String department= null;
        double creait_or_salary= 0;
        try {
            username = jTextField1.getText();
            password = jPasswordField.getText();
            status = jComboBox3.getSelectedItem().toString();
            id = Integer.parseInt(jTextField4.getText());
            name = jTextField5.getText();
            department = jTextField6.getText();
            creait_or_salary = Double.parseDouble(jTextField7.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        if(associationController.getLoginController().IsRegisterValidated(associationController,username,password,status,id,name,department,creait_or_salary)){
            associationController.setSelect(AssociationController.select_mode);
            associationController.HandleMode();
            ProcessError(1);
        }
        else{
            ProcessError(0);
        }
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getLoginController().setSelect(LoginController.init_login);
        associationController.HandleMode();
    }
}
