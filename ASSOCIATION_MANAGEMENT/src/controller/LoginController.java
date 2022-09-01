package controller;

import entity.*;

import java.sql.SQLException;

public class LoginController {

    private int select;
    public static final int init_login=0;
    public static final int direct_login=1;
    public static final int register_login=2;
    public static final int visitor_login=3;
    public static final int exit_login=4;
    private static final int visitor=-10086;
    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }


    public LoginController(){
        //init(associationController.getInitDemo());
        select=init_login;
        //HandleMode(associationController);
    }

    public void HandleMode(AssociationController associationController) throws SQLException {
        switch(select){
            case init_login:init(associationController);break;
            case direct_login:DirectLogin(associationController);break;
            case register_login:RegisterLogin(associationController);break;
            case visitor_login:VisitorLogin(associationController);break;
            case exit_login:ExitLogin(associationController);break;
        }
    }
    private void init(AssociationController associationController){
        associationController.getInitDemo().InitInterface(associationController);
    }
    private void DirectLogin(AssociationController associationController){
        associationController.getInitDemo().DirectLogin(associationController);
    }
    private void RegisterLogin(AssociationController associationController){
        associationController.getInitDemo().RegisterLogin(associationController);
    }
    private void VisitorLogin(AssociationController associationController) throws SQLException {
        Users users=new Visitor();
        users.setId(visitor);
        associationController.setUser(users);
        associationController.setSelect(AssociationController.select_mode);
        associationController.HandleMode();
    }
    private void ExitLogin (AssociationController associationController) throws SQLException {
        JDBCDemo.ConnectionClose();
        System.exit(0);
    }

    public boolean IsLoginValidated(AssociationController associationController,String username,String password,String status) throws SQLException {
        Users user=null;
        if(status.equals("学生")){
            user=new Student();
        }
        else{
            user=new Teacher();
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(status);
        if(JDBCDemo.IsLoginValidated(user)){
            associationController.setUser(user);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean IsRegisterValidated(AssociationController associationController,String username,String password,String status,int id,String name,String department,double creait_or_salary) throws SQLException {
        Users user=null;
        System.out.println(status);
        if(status.equals("学生")){
            user=new Student();
        }
        else{
            user=new Teacher();
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(status);
        user.setId(id);
        user.setName(name);
        user.setDepartment(department);
        user.setCreait_or_Salary(creait_or_salary);
        if(JDBCDemo.IsRegisterValidated(user)){
            System.out.println("ok");
            associationController.setUser(user);
            return true;
        }
        else {
            return false;
        }
    }
}
