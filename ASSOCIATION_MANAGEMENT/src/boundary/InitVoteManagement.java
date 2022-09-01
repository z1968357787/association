package boundary;

import controller.ActivilityController;
import controller.AssociationController;
import controller.VoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InitVoteManagement extends JPanel {
    public InitVoteManagement() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void InitVote(AssociationController associationController){
        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "事件发布或删除":
                        try {
                            ProcessEvents(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "选项添加或删除":
                        try {
                            ProcessOptions(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "成员投票处":
                        try {
                            ProcessVote(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "投票结果查询":
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

        JButton jButton1=new JButton("事件发布或删除");
        jButton1.setBounds(400,25,200,50);
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton2=new JButton("选项添加或删除");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(400,100,200,50);

        JButton jButton3=new JButton("成员投票处");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(400,175,200,50);

        JButton jButton4=new JButton("投票结果查询");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(400,250,200,50);

        JButton jButton5=new JButton("返回选择界面");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(400,400,200,50);

        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());
        jButton3.addActionListener(new MyActionListener());
        jButton4.addActionListener(new MyActionListener());
        jButton5.addActionListener(new MyActionListener());

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButton5);
    }
    private void ProcessEvents(AssociationController associationController) throws SQLException {
        associationController.getVoteController().setSelect(VoteController.events_add_or_delete);
        associationController.HandleMode();
    }
    private void ProcessOptions(AssociationController associationController) throws SQLException {
        associationController.getVoteController().setSelect(VoteController.options_add_or_delete);
        associationController.HandleMode();
    }
    private void ProcessVote(AssociationController associationController) throws SQLException {
        associationController.getVoteController().setSelect(VoteController.vote_for_options);
        associationController.HandleMode();
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        associationController.getVoteController().setSelect(VoteController.vote_query);
        associationController.HandleMode();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
}
