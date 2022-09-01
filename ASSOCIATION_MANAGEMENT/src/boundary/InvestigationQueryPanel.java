package boundary;

import controller.AssociationController;
import controller.InvestigationController;
import controller.VoteController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class InvestigationQueryPanel extends JPanel {
    private JTextField jTextField;
    private static final int y=120;
    private static final int height=580;
    private Vector<String> columnName=new Vector<>();
    public InvestigationQueryPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void InvestigationQuery(AssociationController associationController){

        class MyActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "查询所有回答情况":
                        try {
                            ProcessQuery(associationController);
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
                    default:
                        try {
                            ProcessQuery(associationController,select);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        }
        JLabel label=new JLabel("请输入需要查询的社团名或问题内容或回答结果:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(100,15,350,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(440,15,200,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField.setText("");

        JButton jButton1=new JButton("查询所有回答情况");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(100,50,200,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("统计各问题回答数量");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(100,80,200,25);
        jButton2.addActionListener(new MyActionListener());

        JButton jButton3=new JButton("回答次数升序排序");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(415,50,200,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("回答次数降序排序");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(415,80,200,25);
        jButton4.addActionListener(new MyActionListener());

        JButton jButton5=new JButton("查询最多回答数问题");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(730,50,200,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("查询最少回答数问题");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(730,80,200,25);
        jButton6.addActionListener(new MyActionListener());

        JButton jButton7=new JButton("返回");
        jButton7.setFont(new Font("宋体", Font.BOLD, 15));
        jButton7.setBounds(855,15,75,25);
        jButton7.addActionListener(new MyActionListener());

        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,1000,120);
        jPanel.setLayout(null);


        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(jButton4);
        jPanel.add(jButton5);
        jPanel.add(jButton6);
        jPanel.add(jButton7);

        add(jPanel);
    }
    private void MakeTable1(AssociationController associationController){
        columnName.clear();
        columnName.add("社团名");
        columnName.add("问题内容");
        columnName.add("作答成员");
        columnName.add("回答结果");
        columnName.add("回答日期");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
    private void MakeTable2(AssociationController associationController){
        columnName.clear();
        columnName.add("社团名");
        columnName.add("问题内容");
        columnName.add("回答次数");
        InitDemo.ShowTable(associationController.getData(),columnName,y,height,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getInvestigationController().GetInvestigations(associationController,name);
        MakeTable1(associationController);
        MakeEmpty();
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getInvestigationController().setSelect(InvestigationController.init_investigation);
        associationController.HandleMode();
    }
    private void ProcessQuery(AssociationController associationController,String select) throws SQLException {
        String name=jTextField.getText();
        associationController.getInvestigationController().GetInvestigations(associationController,name,select);
        MakeTable2(associationController);
        MakeEmpty();
    }
    private void MakeEmpty(){
        jTextField.setText(null);
    }
}
