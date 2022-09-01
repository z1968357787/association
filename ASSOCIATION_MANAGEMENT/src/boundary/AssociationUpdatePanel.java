package boundary;

import controller.AssociationController;
import controller.AssociationManagementController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class AssociationUpdatePanel extends JPanel {
    private JTextField jTextField;
    private JTextField jTextField_query;
    private JTextField jTextField3;
    private JTextField jTextField1;
    private JTextArea jTextArea;
    private JTextField jTextField2;
    private JComboBox<String> jComboBox;
    private Vector<String> columnName=new Vector<>();
    public AssociationUpdatePanel() {
        setLayout(null);
        setBounds(0,0,InitDemo.width,InitDemo.height);
    }
    public void AssociationUpdate(AssociationController associationController){

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "确定":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询1":
                        try {
                            ProcessQuery1(associationController);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "查询2":
                        try {
                            ProcessQuery2(associationController);
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
        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,220);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,220,1000,50);
        jPanel2.setLayout(null);
        JPanel jPanel3=new JPanel();
        jPanel3.setBounds(0,450,1000,50);
        jPanel3.setLayout(null);

        JLabel label=new JLabel("请输入需要修改或删除的社团号:");
        label.setFont(new Font("宋体", Font.BOLD, 15));
        label.setBounds(150,15,250,25);
        jTextField=new JTextField(10);
        jTextField.setBounds(390,15,100,25);
        jTextField.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox=new JComboBox<>();
        jComboBox.addItem("更新");
        jComboBox.addItem("删除");
        jComboBox.setFont(new Font("宋体", Font.BOLD, 15));
        jComboBox.setBounds(500,15,90,25);

        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(760,15,75,25);
        jButton1.addActionListener(new MyActionListener());


        JButton jButton2=new JButton("返回");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(850,15,75,25);
        jButton2.addActionListener(new MyActionListener());

        add(label);
        add(jTextField);
        add(jComboBox);
        add(jButton1);
        add(jButton2);
        JLabel jLabel=new JLabel("注：如果要修改活动信息，则需要填写以下信息，若某部分不修改，可该部分不填");
        jLabel.setBounds(200,50,600,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        JLabel jLabel1=new JLabel("社团名字:");
        jLabel1.setBounds(150,70,200,20);
        JLabel jLabel2=new JLabel("社团介绍:");
        jLabel2.setBounds(150,100,200,20);
        JLabel jLabel3=new JLabel("主管教师教工号:");
        jLabel3.setBounds(100,190,200,20);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 15));
        jLabel3.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField1=new JTextField(20);
        jTextField1.setBounds(220,70,200,25);
        jTextField2=new JTextField(20);
        jTextField2.setBounds(220,185,50,25);
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(220,100,500,80);

        jTextField1.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jTextField2.setFont(new Font("宋体", Font.BOLD, 15));
        jPanel1.add(jLabel);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jScrollPane);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField2);
        add(jPanel1);

        MakeTable1(associationController);

        JLabel label4=new JLabel("请输入需要查询的教师名称:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(50,15,250,25);
        jTextField3=new JTextField(10);
        jTextField3.setBounds(250,15,210,25);
        jTextField3.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询1");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label4);
        jPanel2.add(jTextField3);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        MakeTable2(associationController);

        JLabel label5=new JLabel("请输入需要查询的社团名称:");
        label5.setFont(new Font("宋体", Font.BOLD, 15));
        label5.setBounds(50,15,250,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(250,15,210,25);
        jTextField_query.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton5=new JButton("查询2");
        jButton5.setFont(new Font("宋体", Font.BOLD, 15));
        jButton5.setBounds(760,15,75,25);
        jButton5.addActionListener(new MyActionListener());

        JButton jButton6=new JButton("返回");
        jButton6.setFont(new Font("宋体", Font.BOLD, 15));
        jButton6.setBounds(850,15,75,25);
        jButton6.addActionListener(new MyActionListener());

        jPanel3.add(label5);
        jPanel3.add(jTextField_query);
        jPanel3.add(jButton5);
        jPanel3.add(jButton6);
        add(jPanel3);

    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        int asno= 0;
        try {
            asno = Integer.parseInt(jTextField.getText());
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        if(associationController.getAssociationManagementController().IsAssociationExists(asno)){
            String select=jComboBox.getSelectedItem().toString();
            switch (select){
                case "更新":ProcessUpdate(asno,associationController);break;
                case "删除":ProcessDelete(asno,associationController);break;
            }
        }
        else{
            ProcessError(-3);
            System.out.println("更新或删除失败");
        }
        MakeEmpty();
        ProcessQuery2(associationController);
    }
    private void ProcessUpdate(int asno,AssociationController associationController) throws SQLException {
        String name=jTextField1.getText();
        String introduction= jTextArea.getText();
        int mgn_tno=-1;
        if(!jTextField2.getText().equals("")){
            try {
                mgn_tno= Integer.parseInt(jTextField2.getText());
            } catch (NumberFormatException e) {
                ProcessError(InitDemo.error);
                e.printStackTrace();
                return;
            }
        }
        int count=associationController.getAssociationManagementController().AssociationUpadte(associationController,asno,name,introduction,mgn_tno);
        ProcessError(count);
    }
    private void ProcessDelete(int asno,AssociationController associationController) throws SQLException {
        int count=associationController.getAssociationManagementController().AssociationDelete(associationController,asno);
        ProcessError(count);
    }
    private void MakeTable1(AssociationController associationController){
        columnName.clear();
        //rowData=associationController.getData();
        columnName.add("教工号");
        columnName.add("教工姓名");
        InitDemo.ShowTable(associationController.getData(),columnName,270,180,this);
    }
    private void MakeTable2(AssociationController associationController){
        columnName.clear();
        columnName.add("社团号");
        columnName.add("社团名");
        InitDemo.ShowTable(associationController.getData2(),columnName,500,200,this);
    }
    private void ProcessQuery1(AssociationController associationController) throws SQLException {
        String name=jTextField3.getText();
        associationController.getAssociationManagementController().GetTeacher(associationController,name);
        MakeTable1(associationController);
        jTextField3.setText(null);
    }
    private void ProcessQuery2(AssociationController associationController) throws SQLException {
        String name=jTextField_query.getText();
        associationController.getAssociationManagementController().GetAsnos(associationController,name);
        MakeTable2(associationController);
        jTextField_query.setText(null);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getAssociationManagementController().setSelect(AssociationManagementController.init_association);
        associationController.HandleMode();
    }
    private void ProcessError(int count){
        String str;
        int x;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case  0:str="该社团号不存在";x=170;break;
            case -1:str="你不是社团主管教师";x=150;break;
            case -2:str="该教工号不存在或无此社团号";x=110;break;
            case -3:str="更新或删除失败";x=170;break;
            default:str="修改或删除成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void MakeEmpty(){
        jTextField.setText("");
        jTextField1.setText("");
        jTextArea.setText("");
        jTextField2.setText("");
    }
}
