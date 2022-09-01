package boundary;

import controller.AssociationController;
import controller.MemberController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenberUpdatePanel extends JPanel {
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;

    public MenberUpdatePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void MenberUpdate(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "修改":
                        try {
                            ProcessUpdate(associationController);
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

        JLabel jLabel1=new JLabel("学号或教工号:");
        JLabel jLabel2=new JLabel("姓名:");
        JLabel jLabel3=new JLabel("院系:");
        JLabel jLabel4=new JLabel("工资或绩点:");
        JLabel jLabel5=new JLabel("注：如果您是学生，请输入百分制绩点，如果您是教师，请输入工资");

        jTextField1 =new JTextField();
        jTextField2 =new JTextField();
        jTextField3 =new JTextField();
        jTextField4 =new JTextField();

        JLabel jLabel=new JLabel("注：如果要修改活动信息，则需要填写以下信息，若某部分不修改，可该部分不填");
        jLabel.setBounds(200,20,600,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));

        jLabel1.setBounds(360,50,120,25);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setBounds(420,100,50,25);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setBounds(420,150,50,25);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel4.setBounds(375,200,100,25);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel5.setBounds(250,250,500,25);
        jLabel5.setFont(new Font("宋体", Font.BOLD, 15));

        jTextField1.setBounds(460,50,150,25);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2.setBounds(460,100,150,25);
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField3.setBounds(460,150,150,25);
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField4.setBounds(460,200,70,25);
        jTextField4.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton1=new JButton("修改");
        jButton1.setBounds(350,300,70,25);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(550,300,70,25);
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());

        add(jLabel);
        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jTextField2);
        add(jLabel3);
        add(jTextField3);
        add(jLabel4);
        add(jTextField4);
        add(jLabel5);
        add(jButton1);
        add(jButton2);
    }
    private void ProcessUpdate(AssociationController associationController) throws SQLException {
        int id=-1;
        if(!jTextField1.getText().equals("")) {
            try {
                id = Integer.parseInt(jTextField1.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        String name=jTextField2.getText();
        String department=jTextField3.getText();
        double credit_or_salary=-1;
        if(!jTextField4.getText().equals("")){
            try {
                credit_or_salary= Double.parseDouble(jTextField4.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        int count=associationController.getMemberController().MemberUpdate(associationController,id,name,department,credit_or_salary);
        ProcessError(count);
        MakeEmpty();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getMemberController().setSelect(MemberController.init_member);
        associationController.HandleMode();
    }
    private void MakeEmpty(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);

    }
    private void ProcessError(int count){
        String str;
        int x;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case 0:str="学号重复";x=200;break;
            default:str="修改成功";x=200;break;
        }
        InitDemo.ProcessError(str,x);
    }

}
