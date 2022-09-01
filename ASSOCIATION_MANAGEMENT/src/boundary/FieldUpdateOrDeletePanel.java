package boundary;

import controller.AssociationController;
import controller.FieldController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class FieldUpdateOrDeletePanel extends JPanel {
    private JTextField jTextField;
    private JTextArea jTextArea;
    private JComboBox<String> jComboBox;
    private JTextField jTextField_query;
    private Vector<String> columnName=new Vector<>();
    public FieldUpdateOrDeletePanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FieldUpdateOrDelete(AssociationController associationController){

        JPanel jPanel1=new JPanel();
        jPanel1.setBounds(0,0,1000,150);
        jPanel1.setLayout(null);
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,150,1000,50);
        jPanel2.setLayout(null);

        JLabel label=new JLabel("请输入需要修改或删除的场地号:");
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

        JLabel jLabel=new JLabel("请输入可用场地地址：");
        jLabel .setBounds(150,50,220,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(305,50,500,80);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));

        class MyActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch(select){
                    case "确定":
                        try {
                            ProcessUpdateOrDelete(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
                            ex.printStackTrace();
                        }
                        break;
                    case "查询":
                        try {
                            ProcessQuery(associationController);
                        } catch (SQLException ex) {
                            ProcessError(-2);
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

        JButton jButton1=new JButton("确定");
        jButton1.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setBounds(760,15,75,25);
        jButton1.addActionListener(new MyActionListener());

        JButton jButton2=new JButton("返回");
        jButton2.setFont(new Font("宋体", Font.BOLD, 15));
        jButton2.setBounds(850,15,75,25);
        jButton2.addActionListener(new MyActionListener());

        jPanel1.add(label);
        jPanel1.add(jTextField);
        jPanel1.add(jComboBox);
        jPanel1.add(jLabel);
        jPanel1.add(jScrollPane);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);

        add(jPanel1);

        JLabel label4=new JLabel("请输入需要查询的场地地址:");
        label4.setFont(new Font("宋体", Font.BOLD, 15));
        label4.setBounds(150,15,250,25);
        jTextField_query=new JTextField(10);
        jTextField_query.setBounds(350,15,210,25);
        jTextField_query.setFont(new Font("宋体", Font.BOLD, 15));

        JButton jButton3=new JButton("查询");
        jButton3.setFont(new Font("宋体", Font.BOLD, 15));
        jButton3.setBounds(760,15,75,25);
        jButton3.addActionListener(new MyActionListener());

        JButton jButton4=new JButton("返回");
        jButton4.setFont(new Font("宋体", Font.BOLD, 15));
        jButton4.setBounds(850,15,75,25);
        jButton4.addActionListener(new MyActionListener());

        jPanel2.add(label4);
        jPanel2.add(jTextField_query);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        add(jPanel2);

        MakeTable(associationController);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch (count){
            case InitDemo.error:str="纯数字域填写了其他字符，请重输";x=70;break;
            case -2:str="该地址已存在";x=180;break;
            case -1:str="你不是主管教师";x=170;break;
            case 0:str="该地址号不存在";x=170;break;
            default:str="修改或删除成功";x=170;break;
        }
        InitDemo.ProcessError(str,x);;
    }
    private void ProcessUpdateOrDelete(AssociationController associationController) throws SQLException {
        String select= null;
        int lno= 0;
        String address= null;
        try {
            select = jComboBox.getSelectedItem().toString();
            lno = Integer.parseInt(jTextField.getText());
            address = jTextArea.getText();
        } catch (NumberFormatException e) {
            ProcessError(InitDemo.error);
            e.printStackTrace();
            return;
        }
        jTextField.setText(null);
        jTextArea.setText(null);
        int count=associationController.getFieldController().FieldUpdateOrDelete(associationController,lno,address,select);
        ProcessError(count);
        ProcessQuery(associationController);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.init_field);
        associationController.HandleMode();
    }
    private void MakeTable(AssociationController associationController){
        columnName.clear();
        columnName.add("可用场地号");
        columnName.add("可用场地地址");
        InitDemo.ShowTable(associationController.getData(),columnName,200,500,this);
    }
    private void ProcessQuery(AssociationController associationController) throws SQLException {
        String name=jTextField.getText();
        associationController.getFieldController().GetActilityLoactions(associationController,name);
        MakeTable(associationController);
        jTextField.setText(null);
    }
}
