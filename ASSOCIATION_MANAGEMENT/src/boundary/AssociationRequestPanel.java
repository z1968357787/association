package boundary;

import controller.AssociationController;
import controller.AssociationManagementController;
import entity.Association;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class AssociationRequestPanel extends JPanel {
    private JTextField jTextField1;
    private JTextArea jTextArea;
    private JTextField jTextField2;
    private JTextField jTextField;
    private Vector<String> columnName=new Vector<>();
    public AssociationRequestPanel(){
        setLayout(null);
        setBounds(0,0,InitDemo.width,InitDemo.height);
    }
    public void AssociationRequest(AssociationController associationController){

        MakeTable(associationController);

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,160);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,160,1000,50);
        jPanel2.setLayout(null);

        JLabel jLabel1=new JLabel("社团名字:");
        jLabel1.setBounds(150,20,200,20);
        JLabel jLabel2=new JLabel("社团介绍:");
        jLabel2.setBounds(150,50,200,20);
        JLabel jLabel3=new JLabel("主管教师教工号:");
        jLabel3.setBounds(100,140,200,20);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField1=new JTextField(20);
        jTextField1.setBounds(220,20,200,25);
        
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,50,500,80);
        jTextField2=new JTextField(20);
        jTextField2.setBounds(220,135,50,25);
        JButton jButton1=new JButton("申请");
        jButton1.setBounds(845,20,80,35);
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(845,125,80,35);
        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setFont(new Font("宋体", Font.BOLD, 20));
        jButton2.setFont(new Font("宋体", Font.BOLD, 20));
        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "申请":
                        try {
                            ProcessRequest(associationController);
                        } catch (SQLException ex) {
                            ProcessError(0);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询":
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
                }
            }
        }


        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());

        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jScrollPane);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField2);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        add(jPanel1);

        JLabel jLabel=new JLabel("请输入需要查询的教师名称:");
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel.setBounds(50,15,250,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(250,15,210,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(jLabel);
        jPanel2.add(jTextField);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        columnName.add("教工号");
        columnName.add("教工姓名");
        InitDemo.ShowTable(associationController.getData(),columnName,210,490,this);
    }
    private void ProcessRequest(AssociationController associationController) throws SQLException {
        String name=jTextField1.getText();
        String introduction= jTextArea.getText();
        int mgn_tno= 0;
        try {
            mgn_tno = Integer.parseInt(jTextField2.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        jTextField1.setText(null);
        jTextArea.setText(null);
        jTextField2.setText(null);
        //System.out.println(associationController.getUser().getId());
        int count=associationController.getAssociationManagementController().AssociationRequest(associationController,name,introduction,mgn_tno);
        ProcessError(count);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getAssociationManagementController().GetTeacher(associationController,name);
        MakeTable(associationController);
        jTextField.setText(null);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.init_association);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch(count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case 0: str="申请失败，该教工号不存在或游客访问";x=60;break;
            default:str= "申请成功，社团号为:"+count;x=100;break;
        }
        InitDemo.ProcessError(str,x);
    }
}
