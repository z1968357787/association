package boundary;

import controller.AssociationController;
import controller.FieldController;
import entity.ActilityLoaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class FieldAddPanel extends JPanel {
    private JTextArea jTextArea;
    public FieldAddPanel() {
        setBounds(0,0,InitDemo.width,InitDemo.height);
        setLayout(null);
    }
    public void FieldAdd(AssociationController associationController){
        JLabel jLabel=new JLabel("请输入可用场地地址：");
        jLabel .setBounds(165,100,220,20);
        jLabel.setFont(new Font("宋体", Font.BOLD, 15));
        jTextArea =new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setBounds(320,100,500,80);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        class MyActionListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String select=e.getActionCommand();
                switch (select){
                    case "申请":
                        try {
                            ProcessAdd(associationController);
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

        JButton jButton1=new JButton("申请");
        jButton1.setBounds(150,350,80,35);
        JButton jButton2=new JButton("返回");
        jButton2.setBounds(650,350,80,35);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 15));
        jButton1.setFont(new Font("宋体", Font.BOLD, 20));
        jButton2.setFont(new Font("宋体", Font.BOLD, 20));
        jButton1.addActionListener(new MyActionListener());
        jButton2.addActionListener(new MyActionListener());

        add(jLabel);
        add(jScrollPane);
        add(jButton1);
        add(jButton2);
    }
    private void ProcessError(int count){
        int x;
        String str;
        switch (count){
            case 0:str="该地址已存在";x=180;break;
            case -1:str="你不是主管教师";x=170;break;
            default:str="添加成功，地址号为:"+count;x=120;break;
            //case -2:str="你并不在此社团";x=170;break;
            //case -3:str="更新或删除失败";break;
        }
        InitDemo.ProcessError(str,x);
    }
    private void ProcessAdd(AssociationController associationController) throws SQLException {
        String address=jTextArea.getText();
        jTextArea.setText(null);
        int count=associationController.getFieldController().FieldAdd(associationController,address);
        ProcessError(count);
    }
    private void ProcessReturn(AssociationController associationController) throws SQLException {
        associationController.getFieldController().setSelect(FieldController.init_field);
        associationController.HandleMode();
    }

}
