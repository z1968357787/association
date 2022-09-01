package boundary;

import controller.AssociationController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SelectPanel extends JPanel {
    public SelectPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }

    public void SelectMode(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "社团信息管理":associationController.setSelect(AssociationController.association_management);break;
                    case "社团成员管理":associationController.setSelect(AssociationController.member_management);break;
                    case "社团活动管理":associationController.setSelect(AssociationController.activility_management);break;
                    case "活动场地管理":associationController.setSelect(AssociationController.field_management);break;
                    case "社团财务管理":associationController.setSelect(AssociationController.finance_management);break;
                    case "志愿服务管理":associationController.setSelect(AssociationController.volunteer_management);break;
                    case "社团投票管理":associationController.setSelect(AssociationController.vote_management);break;
                    case "社团调查管理":associationController.setSelect(AssociationController.investigation_management);break;
                    case "社团BBS管理":associationController.setSelect(AssociationController.bbs_management);break;
                    case "返回登录界面":{
                        associationController.getLoginController().setSelect(LoginController.init_login);
                        associationController.setSelect(AssociationController.login);
                        break;
                    }
                }
                try {
                    associationController.HandleMode();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        JButton jButton1=new JButton("社团信息管理");
        jButton1.setBounds(200,25,180,50);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton2=new JButton("社团成员管理");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(200,100,180,50);

        JButton jButton3=new JButton("社团活动管理");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(200,175,180,50);

        JButton jButton4=new JButton("活动场地管理");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(200,250,180,50);


        JButton jButton5=new JButton("社团财务管理");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(200,325,180,50);


        JButton jButton6=new JButton("志愿服务管理");
        jButton6.setBounds(600,25,180,50);
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton7=new JButton("社团投票管理");
        jButton7.setFont(new Font("宋体", Font.BOLD, 15));
        jButton7.setBounds(600,100,180,50);

        JButton jButton8=new JButton("社团调查管理");
        jButton8.setFont(new Font("宋体", Font.BOLD, 15));
        jButton8.setBounds(600,175,180,50);

        JButton jButton9=new JButton("社团BBS管理");
        jButton9.setFont(new Font("宋体", Font.BOLD, 15));
        jButton9.setBounds(600,250,180,50);


        JButton jButton10=new JButton("返回登录界面");
        jButton10.setFont(new Font("宋体", Font.BOLD, 15));
        jButton10.setBounds(600,325,180,50);

        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());
        jButton3.addActionListener(new MyActionListener());
        jButton4.addActionListener(new MyActionListener());
        jButton5.addActionListener(new MyActionListener());
        jButton6.addActionListener(new MyActionListener());
        jButton7.addActionListener(new MyActionListener());
        jButton8.addActionListener(new MyActionListener());
        jButton9.addActionListener(new MyActionListener());
        jButton10.addActionListener(new MyActionListener());

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButton5);
        add(jButton6);
        add(jButton7);
        add(jButton8);
        add(jButton9);
        add(jButton10);

    }
}
