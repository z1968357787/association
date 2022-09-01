package entity;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class JDBCDemo {
    private static Connection connection=null;
    private static final String url="jdbc:mysql://rm-bp1h8323vd93h379wpo.mysql.rds.aliyuncs.com:3306/db_association?useSSL=false&allowPublicKeyRetrieval=true&useServerPrepStmts=true";
    private static final String username="js_2022";
    private static final String password="abc123!!!";
    //private static final String url="jdbc:mysql://127.0.0.1:3306/db_association?useSSL=false&allowPublicKeyRetrieval=true&useServerPrepStmts=true";
    //private static final String username="root";
    //private static final String password="123456";
    public static void start() throws ClassNotFoundException, SQLException {
         //Class.forName("com.mysql.jdbc.Driver");
         connection= DriverManager.getConnection(url,username,password);
         String sql="show tables";
         PreparedStatement pstmt=connection.prepareStatement(sql);
         ResultSet count=pstmt.executeQuery(sql);
         while(count.next()){
             String name=count.getString(1);
             System.out.println(name);
         }
         count.close();
         pstmt.close();
         //connection.close();
    }
    public static boolean IsLoginValidated(Users user) throws SQLException {
        String sql="call IsLoginValidated(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getStatus());
        //long startTime = System.currentTimeMillis();
        ResultSet count=pstmt.executeQuery();
        //long endTime = System.currentTimeMillis();
        /*System.out.println("the startTime is " + startTime + " ms");
        System.out.println("the endTime is " + endTime+ " ms");
        System.out.println("the duration is " + (endTime - startTime) + " ms");*/
        if(count.next()){
            int uno=count.getInt(1);
            String username= count.getString(2);
            String password=count.getString(3);
            String status=count.getString(4);

            user.setUno(uno);
            user.setUsername(username);
            user.setPassword(password);
            user.setStatus(status);
            count.close();
            pstmt.close();
            //return true;
        }
        else{
            count.close();
            pstmt.close();
            return false;
        }

        String sql2="call GetUser(?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setString(1,user.getStatus());
        pstmt2.setInt(2,user.getUno());
        ResultSet count2=pstmt2.executeQuery();
        if(count2.next()){
            user.setId(count2.getInt(1));
            user.setName(count2.getString(2));
            user.setDepartment(count2.getString(3));
            user.setCreait_or_Salary(count2.getDouble(4));
            count2.close();
            pstmt2.close();
            return true;
        }
        else {
            count2.close();
            pstmt2.close();
            return false;
        }
    }
    public static boolean IsRegisterValidated(Users user) throws SQLException {
        /*String sql1="select *from student where sno=?";
        PreparedStatement pstmt1=connection.prepareStatement(sql1);
        pstmt1.setInt(1,user.getId());
        ResultSet resultSet1=pstmt1.executeQuery();
        if(resultSet1.next()){
            resultSet1.close();
            pstmt1.close();
            return false;
        }

        resultSet1.close();
        pstmt1.close();*/
        int uno;
        String sql2="call GetUno(?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        while(true){
            Random random=new Random();
            uno=random.nextInt(10000);
            pstmt2.setInt(1,uno);
            ResultSet resultSet2=pstmt2.executeQuery();
            if(!resultSet2.next()){
                resultSet2.close();
                pstmt2.close();
                break;
            }
        }
        user.setUno(uno);
        String sql3="call InsertUser(?,?,?,?)";
        PreparedStatement pstmt3=connection.prepareStatement(sql3);
        pstmt3.setInt(1,user.getUno());
        pstmt3.setString(2,user.getUsername());
        pstmt3.setString(3,user.getPassword());
        pstmt3.setString(4,user.getStatus());
        int count1=pstmt3.executeUpdate();
        System.out.println(count1);
        pstmt3.close();

        String sql4="call InsertUserInformation(?,?,?,?,?,?)";
        PreparedStatement pstmt4=connection.prepareStatement(sql4);
        pstmt4.setString(1,user.getStatus());
        pstmt4.setInt(2,user.getId());
        pstmt4.setString(3,user.getName());
        pstmt4.setString(4,user.getDepartment());
        pstmt4.setDouble(5,user.getCreait_or_Salary());
        pstmt4.setInt(6,user.getUno());
        int count2=pstmt4.executeUpdate();
        System.out.println(count2);
        pstmt4.close();

        return true;
    }

    public static int AssociationRequest(Association association) throws SQLException {
        int asno;
        String sql="call GetAsno(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        while(true){
            Random random=new Random();
            asno=random.nextInt(10000);
            pstmt.setInt(1,asno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        association.setAsno(asno);
        Date date=new Date();
        association.setCreate_date(new java.sql.Date(date.getYear(),date.getMonth(),date.getDate()));
        String sql2="call AssociationRequest(?,?,?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,association.getAsno());
        pstmt2.setString(2,association.getAsname());
        pstmt2.setString(3,association.getIntroduction());
        pstmt2.setDate(4,association.getCreate_date());
        pstmt2.setInt(5,association.getMgn_tno());
        pstmt2.setInt(6,association.getMgn_sno());
        pstmt2.executeUpdate();
        int count=asno;
        //System.out.println(count);
        pstmt2.close();
        return count;
    }
    public static Association GetAssociation(int asno) throws SQLException {
        String sql="call GetAssociation(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        ResultSet resultSet=pstmt.executeQuery();
        Association association=null;
        if(resultSet.next()){
            association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.setIntroduction(resultSet.getString(3));
            association.setMgn_tno(resultSet.getInt(4));
        }
        resultSet.close();
        pstmt.close();
        return association;
    }
    public static void GetAssociations(List<Association> associations, String name, String state) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetAssociations(?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,state);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.setState(resultSet.getString(3));
            association.setCreate_date(resultSet.getDate(4));
            association.setIntroduction(resultSet.getString(5));
            association.setMgn_tname(resultSet.getString(6));
            association.setMgn_sname(resultSet.getString(7));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }

    public static int UpdateApproval(int asno,String state) throws SQLException {

        String sql="call AssociationApproval(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,state);
        pstmt.setInt(2,asno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        String sql1="call GetMgn_Sno(?)";
        PreparedStatement pstmt1=connection.prepareStatement(sql1);
        pstmt1.setInt(1,asno);
        ResultSet resultSet=pstmt1.executeQuery();
        int sno;
        if(resultSet.next()){
            sno=resultSet.getInt(1);
            resultSet.close();
            pstmt1.close();
        }
        else{
            count=0;
            resultSet.close();
            pstmt1.close();
            return count;
        }
        if(state.equals("已通过")){
            String sql2="call InsertPARTICIPATE(?,?,?)";
            PreparedStatement pstmt2=connection.prepareStatement(sql2);
            pstmt2.setInt(1,asno);
            pstmt2.setInt(2,sno);
            pstmt2.setString(3,"已通过");
            count=pstmt2.executeUpdate();
            pstmt2.close();

        }
        else if(state.equals("已拒绝")){
            String sql2="call DeleteAssociation(?)";
            PreparedStatement pstmt2=connection.prepareStatement(sql2);
            pstmt2.setInt(1,asno);
            count=pstmt2.executeUpdate();
            pstmt2.close();
        }
        return count;
    }

    public static boolean IsAssociationExists(int asno) throws SQLException {
        String sql="call GetAssociation(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            resultSet.close();
            pstmt.close();
            return true;
        }
        else{
            resultSet.close();
            pstmt.close();
            return false;
        }
    }
    public static int AssociationUpadte(Association association) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        String name=association.getAsname();
        String introduction=association.getIntroduction();
        int asno=association.getAsno();
        int mgn_tno=association.getMgn_tno();
        sql="call UpdateAssociation(?,?,?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,introduction);
        pstmt.setInt(3,mgn_tno);
        pstmt.setInt(4,asno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    public static int AssociationDelete(int asno) throws SQLException {
        String sql="call DeleteAssociation(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    public static int InsertMember(Participate participate) throws SQLException {
        int asno=participate.getAssociation().getAsno();
        int sno=participate.getStudent().getId();
        String sql="call InsertMember(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    public static int DeleteMember(int asno,int sno) throws SQLException {
        String sql="call DeleteMember(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetRequestMembers(List<Participate> participates,String name) throws SQLException {
        String sql="call GetRequestMembers(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Participate participate=new Participate();
            participate.getAssociation().setAsno(resultSet.getInt(1));
            participate.getAssociation().setAsname(resultSet.getString(2));
            participate.getStudent().setId(resultSet.getInt(3));
            participate.getStudent().setName(resultSet.getString(4));
            participate.setState(resultSet.getString(5));
            participates.add(participate);
        }
    }
    public static boolean IsAssociationLeader(int asno,int sno) throws SQLException {
        String sql="call IsAssociationLeader(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            resultSet.close();
            pstmt.close();
            return true;
        }
        else{
            resultSet.close();
            pstmt.close();
            return false;
        }
    }
    public static int UpdateMemberState(int asno,int sno) throws SQLException {
        String sql="call UpdateMemberState(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,"已通过");
        pstmt.setInt(2,asno);
        pstmt.setInt(3,sno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetMembersInformation(List<Participate> participates,String name) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetMembersInformation(?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Participate participate=new Participate();
            participate.getAssociation().setAsname(resultSet.getString(1));
            participate.getStudent().setId(resultSet.getInt(2));
            participate.getStudent().setName(resultSet.getString(3));
            participate.getStudent().setDepartment(resultSet.getString(4));
            participate.getStudent().setCreait_or_Salary(resultSet.getDouble(5));
            participate.setState(resultSet.getString(6));
            participates.add(participate);
        }
    }
    public static boolean IsAssociationMember(int asno,int sno,String state) throws SQLException {
        String sql="call IsAssociationMember(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        pstmt.setString(3,state);
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            resultSet.close();;
            pstmt.close();
            return true;
        }
        else{
            resultSet.close();;
            pstmt.close();
            return false;
        }
    }
    public static int InsertActivility(Association association) throws SQLException {
        int acno;
        String sql1="call GetAcno(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        pstmt.setInt(1,association.getAsno());
        while(true){
            Random random=new Random();
            acno=random.nextInt(10000);
            pstmt.setInt(2,acno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        association.getAcilitiy().setAcno(acno);
        String sql2="call InsertActivility(?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,association.getAsno());
        pstmt2.setInt(2,association.getAcilitiy().getAcno());
        pstmt2.setString(3,association.getAcilitiy().getAcname());
        pstmt2.setString(4,association.getAcilitiy().getIntroduction());
        pstmt2.executeUpdate();
        int count=acno;
        pstmt2.close();
        return count;
    }

    public static void GetActivilities(List<Association> associations,String state,String name) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetActivilities(?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,state);
        pstmt.setString(2,name);

        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setAcno(resultSet.getInt(3));
            association.getAcilitiy().setAcname(resultSet.getString(4));
            association.getAcilitiy().setState(resultSet.getString(5));
            association.getAcilitiy().setIntroduction(resultSet.getString(6));
            associations.add(association);
        }
        resultSet.close();;
        pstmt.close();
    }
    public static boolean IsManagementTeacher(int asno,int mgn_tno) throws SQLException {
        String sql="call IsManagementTeacher(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,mgn_tno);
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            resultSet.close();
            pstmt.close();
            return true;
        }
        else{
            resultSet.close();
            pstmt.close();
            return false;
        }
    }

    public static int ActivilityApproval(int asno, int acno) throws SQLException {
        String sql="call UpdateActivilityState(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,"已通过");
        pstmt.setInt(2,asno);
        pstmt.setInt(3,acno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static int DeleteActivility(int asno,int acno) throws SQLException {
        String sql="call DeleteActivility(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,acno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static Association GetActivility(int asno,int acno) throws SQLException {
        String sql="call GetActivility(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,acno);
        Association association=null;
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            association=new Association();
            association.setAsno(asno);
            association.getAcilitiy().setAcno(acno);
            association.getAcilitiy().setAcname(resultSet.getString(1));
            association.getAcilitiy().setIntroduction(resultSet.getString(2));
        }
        resultSet.close();
        pstmt.close();
        return association;
    }
    public static int UpdateActivility(Association association) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        String name=association.getAcilitiy().getAcname();
        String introduction=association.getAcilitiy().getIntroduction();
        int asno=association.getAsno();
        int acno=association.getAcilitiy().getAcno();
        sql="call UpdateActivility(?,?,?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,introduction);
        pstmt.setInt(3,asno);
        pstmt.setInt(4,acno);

        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static boolean IsTeacher(int tno) throws SQLException {
        String sql="call IsTeacher(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,tno);
        boolean judge=false;
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            judge=true;
        }
        resultSet.close();
        pstmt.close();
        return judge;
    }
    public static int FieldAdd(ActilityLoaction actilityLoaction) throws SQLException {
        String sql="call GetLno(?)";
        int lno;
        PreparedStatement pstmt=connection.prepareStatement(sql);
        while(true){
            Random random=new Random();
            lno=random.nextInt(10000);
            pstmt.setInt(1,lno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        String sql2="call FieldAdd(?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,lno);
        pstmt2.setString(2,actilityLoaction.getAddress());
        pstmt2.executeUpdate();
        pstmt2.close();
        return lno;
    }
    public static int FieldUpdate(ActilityLoaction actilityLoaction) throws SQLException {
        String sql="call FieldUpdate(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,actilityLoaction.getAddress());
        pstmt.setInt(2,actilityLoaction.getLno());
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    public static int FieldDelete(int lno) throws SQLException {
        String sql="call FieldDelete(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,lno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetActilityLoactions(List<ActilityLoaction> actilityLoactions,String name) throws SQLException {
        String sql="call GetActilityLoactions(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            ActilityLoaction actilityLoaction=new ActilityLoaction();
            actilityLoaction.setLno(resultSet.getInt(1));
            actilityLoaction.setAddress(resultSet.getString(2));
            actilityLoactions.add(actilityLoaction);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int FieldRequest(Located located) throws SQLException {
        String sql="call FieldRequest(?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,located.getAssociation().getAsno());
        pstmt.setInt(2,located.getAssociation().getAcilitiy().getAcno());
        pstmt.setInt(3,located.getActilityLoaction().getLno());
        pstmt.setDate(4,located.getUse_date());
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static int FieldDelete(int asno, int acno, int lno, java.sql.Date date) throws SQLException {
        String sql="call FieldCancel(?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,acno);
        pstmt.setInt(3,lno);
        pstmt.setDate(4,date);
        int count=pstmt.executeUpdate();
        return count;
    }
    public static void GetFieldsUseInformation(List<Located> locateds,String state,String name) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetFieldsUseInformation(?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,state);
        pstmt.setString(2,name);

        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Located located=new Located();
            located.getAssociation().setAsno(resultSet.getInt(1));
            located.getAssociation().setAsname(resultSet.getString(2));
            located.getAssociation().getAcilitiy().setAcno(resultSet.getInt(3));
            located.getAssociation().getAcilitiy().setAcname(resultSet.getString(4));
            located.getActilityLoaction().setLno(resultSet.getInt(5));
            located.getActilityLoaction().setAddress(resultSet.getString(6));
            located.setUse_date(resultSet.getDate(7));
            located.setState(resultSet.getString(8));
            locateds.add(located);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int FieldApproval(int asno, int acno, int lno, java.sql.Date date) throws SQLException {
        String sql="call FieldApproval(?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,"已通过");
        pstmt.setInt(2,asno);
        pstmt.setInt(3,acno);
        pstmt.setInt(4,lno);
        pstmt.setDate(5,date);
        int count=pstmt.executeUpdate();
        return count;
    }
    public static int FinanceAdd(Association association) throws SQLException {
        String sql="call GetFno(?,?)";
        int asno=association.getAsno();
        int fno;
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        while(true){
            Random random=new Random();
            fno=random.nextInt(10000);
            pstmt.setInt(2,fno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        String sql2="call FinanceAdd(?,?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,asno);
        pstmt2.setInt(2,fno);
        pstmt2.setString(3,association.getFinance().getReason());
        pstmt2.setDouble(4,association.getFinance().getMoney());
        pstmt2.setDate(5,association.getFinance().getRecord_date());
        pstmt2.executeUpdate();
        return fno;
    }
    public static Association GetFinance(int asno,int fno) throws SQLException {
        String sql="call GetFinance(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,fno);
        Association association=null;
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            association=new Association();
            association.setAsno(asno);
            association.getFinance().setFno(fno);
            association.getFinance().setMoney(resultSet.getDouble(1));
            association.getFinance().setReason(resultSet.getString(2));
            association.getFinance().setRecord_date(resultSet.getDate(3));
        }
        resultSet.close();
        pstmt.close();
        return association;
    }
    public static int FinanceUpdate(Association association) throws SQLException {
        String sql="call FinanceUpdate(?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setDouble(1,association.getFinance().getMoney());
        pstmt.setString(2,association.getFinance().getReason());
        pstmt.setDate(3,association.getFinance().getRecord_date());
        pstmt.setInt(4,association.getAsno());
        pstmt.setInt(5,association.getFinance().getFno());
        int count= pstmt.executeUpdate();
        return count;
    }
    public static int FinanceDelete(int asno,int fno) throws SQLException {
        String sql="call FinanceDelete(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,fno);
        int count= pstmt.executeUpdate();
        return count;
    }
    public static void FinanceQuery1(List<Association> associations,int select,String name) throws SQLException {
        String sql="call FinanceQuery1(?,?)";
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setInt(2,select);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getFinance().setFno(resultSet.getInt(3));
            association.getFinance().setMoney(resultSet.getDouble(4));
            association.getFinance().setReason(resultSet.getString(5));
            association.getFinance().setRecord_date(resultSet.getDate(6));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void FinanceQuery2(List<Association> associations,int select,String name) throws SQLException {
        String sql="call FinanceQuery2(?,?)";
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setInt(2,select);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getFinance().setMoney(resultSet.getDouble(3));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void FinanceQuery3(List<Association> associations, java.sql.Date date1, java.sql.Date date2, String name) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call FinanceQuery3(?,?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setDate(1,date1);
        pstmt.setDate(2,date2);
        pstmt.setString(3,name);

        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getFinance().setFno(resultSet.getInt(3));
            association.getFinance().setMoney(resultSet.getDouble(4));
            association.getFinance().setReason(resultSet.getString(5));
            association.getFinance().setRecord_date(resultSet.getDate(6));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetFieldsUseInformation(List<Located> locateds, String state, String name, java.sql.Date date1, java.sql.Date date2) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetFieldsUseInformation2(?,?,?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,state);
        pstmt.setString(2,name);
        pstmt.setDate(3,date1);
        pstmt.setDate(4,date2);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Located located=new Located();
            located.getAssociation().setAsno(resultSet.getInt(1));
            located.getAssociation().setAsname(resultSet.getString(2));
            located.getAssociation().getAcilitiy().setAcno(resultSet.getInt(3));
            located.getAssociation().getAcilitiy().setAcname(resultSet.getString(4));
            located.getActilityLoaction().setLno(resultSet.getInt(5));
            located.getActilityLoaction().setAddress(resultSet.getString(6));
            located.setUse_date(resultSet.getDate(7));
            located.setState(resultSet.getString(8));
            locateds.add(located);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int TaskAdd(Association association) throws SQLException {
        String sql1="call GetTnum(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        int tnum;
        pstmt.setInt(1,association.getAsno());
        pstmt.setInt(2,association.getAcilitiy().getAcno());
        while(true){
            Random random=new Random();
            tnum=random.nextInt(10000);
            pstmt.setInt(3,tnum);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }

        String sql2="call TaskAdd(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,association.getAsno());
        pstmt2.setInt(2,association.getAcilitiy().getAcno());
        pstmt2.setInt(3,tnum);
        pstmt2.setString(4,association.getAcilitiy().getTask().getAssignment());
        pstmt2.setInt(5,association.getAcilitiy().getTask().getDuration());
        pstmt2.setInt(6,association.getAcilitiy().getTask().getPeople_needed());
        pstmt2.setInt(7,association.getAcilitiy().getTask().getActilityLoaction().getLno());
        pstmt2.setDate(8,association.getAcilitiy().getTask().getVolunteer_date());
        pstmt2.executeUpdate();
        return tnum;
    }
    public static Association GetTask(int asno,int acno,int tnum) throws SQLException {
        String sql="call GetTask(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,acno);
        pstmt.setInt(3,tnum);
        Association association=null;
        ResultSet resultSet=pstmt.executeQuery();
        if(resultSet.next()){
            association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.getAcilitiy().setAcno(resultSet.getInt(2));
            association.getAcilitiy().getTask().setTnum(resultSet.getInt(3));
            association.getAcilitiy().getTask().setAssignment(resultSet.getString(4));
            association.getAcilitiy().getTask().setDuration(resultSet.getInt(5));
            association.getAcilitiy().getTask().setPeople_needed(resultSet.getInt(7));
            association.getAcilitiy().getTask().getActilityLoaction().setLno(resultSet.getInt(8));
            association.getAcilitiy().getTask().setVolunteer_date(resultSet.getDate(9));
        }
        resultSet.close();
        pstmt.close();
        return association;
    }
    public static int TaskUpdate(Association association) throws SQLException {
        String sql="call TaskUpdate(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,association.getAcilitiy().getTask().getAssignment());
        pstmt.setInt(2,association.getAcilitiy().getTask().getDuration());
        pstmt.setInt(3,association.getAcilitiy().getTask().getPeople_needed());
        pstmt.setInt(4,association.getAcilitiy().getTask().getActilityLoaction().getLno());
        pstmt.setDate(5,association.getAcilitiy().getTask().getVolunteer_date());
        pstmt.setInt(6,association.getAsno());
        pstmt.setInt(7,association.getAcilitiy().getAcno());
        pstmt.setInt(8,association.getAcilitiy().getTask().getTnum());
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static int TaskDelete(int asno,int acno,int tnum) throws SQLException {
        String sql="call TaskDelete(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,acno);
        pstmt.setInt(3,tnum);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetTasks(List<Association> associations,String name) throws SQLException {
        String sql="call GetTasks(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setAcno(resultSet.getInt(3));
            association.getAcilitiy().setAcname(resultSet.getString(4));
            association.getAcilitiy().getTask().setTnum(resultSet.getInt(5));
            association.getAcilitiy().getTask().setAssignment(resultSet.getString(6));
            association.getAcilitiy().getTask().setDuration(resultSet.getInt(7));
            association.getAcilitiy().getTask().setPeople_haven(resultSet.getInt(8));
            association.getAcilitiy().getTask().setPeople_needed(resultSet.getInt(9));
            association.getAcilitiy().getTask().getActilityLoaction().setAddress(resultSet.getString(10));
            association.getAcilitiy().getTask().setVolunteer_date(resultSet.getDate(11));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int VolunteerAdd(VolunteerFor volunteerFor) throws SQLException {
        String sql="call VolunteerAdd(?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,volunteerFor.getAssociation().getAsno());
        pstmt.setInt(2,volunteerFor.getAssociation().getAcilitiy().getAcno());
        pstmt.setInt(3,volunteerFor.getAssociation().getAcilitiy().getTask().getTnum());
        pstmt.setInt(4,volunteerFor.getStudent().getId());
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    public static int VolunteerDelete(int asno,int sno,int acno,int tnum) throws SQLException {
        String sql="call VolunteerDelete(?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        pstmt.setInt(3,acno);
        pstmt.setInt(4,tnum);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetVolunteers(List<VolunteerFor> volunteerFors,String name) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetVolunteers1(?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            VolunteerFor volunteerFor=new VolunteerFor();
            volunteerFor.getAssociation().setAsname(resultSet.getString(1));
            volunteerFor.getAssociation().getAcilitiy().setAcname(resultSet.getString(2));
            volunteerFor.getStudent().setName(resultSet.getString(3));
            volunteerFor.getAssociation().getAcilitiy().getTask().setAssignment(resultSet.getString(4));
            volunteerFor.getAssociation().getAcilitiy().getTask().getActilityLoaction().setAddress(resultSet.getString(5));
            volunteerFor.getAssociation().getAcilitiy().getTask().setVolunteer_date(resultSet.getDate(6));
            volunteerFors.add(volunteerFor);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetVolunteers(List<VolunteerFor> volunteerFors, String name, java.sql.Date date1, java.sql.Date date2) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        name=SharedFunction.ProcessName(name);
        sql="call GetVolunteers2(?,?,?)";
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setDate(2,date1);
        pstmt.setDate(3,date2);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            VolunteerFor volunteerFor=new VolunteerFor();
            volunteerFor.getAssociation().setAsname(resultSet.getString(1));
            volunteerFor.getAssociation().getAcilitiy().setAcname(resultSet.getString(2));
            volunteerFor.getStudent().setName(resultSet.getString(3));
            volunteerFor.getAssociation().getAcilitiy().getTask().setAssignment(resultSet.getString(4));
            volunteerFor.getAssociation().getAcilitiy().getTask().getActilityLoaction().setAddress(resultSet.getString(5));
            volunteerFor.getAssociation().getAcilitiy().getTask().setVolunteer_date(resultSet.getDate(6));
            volunteerFors.add(volunteerFor);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int EventAdd(Association association) throws SQLException {
        String sql1="call GetEno(?,?)";
        int asno=association.getAsno();
        int eno;
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        pstmt.setInt(1,asno);
        while(true){
            Random random=new Random();
            eno=random.nextInt(10000);
            pstmt.setInt(2,eno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        String sql2="call EventAdd(?,?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,association.getAsno());
        pstmt2.setInt(2,eno);
        pstmt2.setString(3,association.getVoteEvents().getTitle());
        pstmt2.setDate(4,association.getVoteEvents().getDdl_date());
        pstmt2.setInt(5,association.getVoteEvents().getMax_poll());
        pstmt2.executeUpdate();
        pstmt2.close();
        return eno;
    }
    public static int EventDelete(int asno, int eno) throws SQLException {
        String sql="call EventDelete(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,eno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static int OptionAdd(Association association) throws SQLException {
        String sql1="call GetOno(?,?,?)";
        int asno=association.getAsno();
        int eno=association.getVoteEvents().getEno();
        int ono;
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,eno);
        while(true){
            Random random=new Random();
            ono=random.nextInt(10000);
            pstmt.setInt(3,ono);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        String sql2="call OptionAdd(?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,ono);
        pstmt2.setInt(2,asno);
        pstmt2.setInt(3,eno);
        pstmt2.setString(4,association.getVoteEvents().getVoteOptions().getContent());
        pstmt2.executeUpdate();
        pstmt2.close();
        return ono;
    }
    public static int OptionDelete(int asno, int eno,int ono) throws SQLException {
        String sql="call OptionDelete(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,eno);
        pstmt.setInt(3,ono);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetEvents(List<Association> associations,String name) throws SQLException {
        String sql="call GetEvents(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getVoteEvents().setEno(resultSet.getInt(3));
            association.getVoteEvents().setTitle(resultSet.getString(4));
            association.getVoteEvents().setMax_poll(resultSet.getInt(5));
            association.getVoteEvents().setDdl_date(resultSet.getDate(6));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int VoteAdd(Vote vote) throws SQLException {
        String sql="call VoteAdd(?,?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,vote.getAssociation().getAsno());
        pstmt.setInt(2,vote.getStudent().getId());
        pstmt.setInt(3,vote.getAssociation().getVoteEvents().getEno());
        pstmt.setInt(4,vote.getAssociation().getVoteEvents().getVoteOptions().getOno());
        pstmt.setInt(5,vote.getPoll());
        pstmt.setDate(6,vote.getVote_date());
        int count= pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    /*public static int VoteUpdate(Vote vote) throws SQLException {
        String sql="update vote set poll=? where asno=? and sno=? and eno=? and ono=?";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,vote.getPoll());
        pstmt.setInt(2,vote.getAssociation().getAsno());
        pstmt.setInt(3,vote.getStudent().getId());
        pstmt.setInt(4,vote.getAssociation().getVoteEvents().getEno());
        pstmt.setInt(5,vote.getAssociation().getVoteEvents().getVoteOptions().getOno());
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }*/
    public static int VoteDelete(int asno,int sno,int eno,int ono) throws SQLException {
        String sql="call VoteDelete(?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        pstmt.setInt(3,eno);
        pstmt.setInt(4,ono);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetOptions(List<Association> associations,String name) throws SQLException {
        String sql="call GetOptions(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getVoteEvents().setEno(resultSet.getInt(3));
            association.getVoteEvents().setTitle(resultSet.getString(4));
            association.getVoteEvents().getVoteOptions().setOno(resultSet.getInt(5));
            association.getVoteEvents().getVoteOptions().setContent(resultSet.getString(6));
            association.getVoteEvents().setMax_poll(resultSet.getInt(7));
            association.getVoteEvents().setDdl_date(resultSet.getDate(8));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetVotes(List<Vote> votes,String name) throws SQLException {
        String sql="call GetVotes1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Vote vote=new Vote();
            vote.getAssociation().setAsname(resultSet.getString(1));
            vote.getAssociation().getVoteEvents().setTitle(resultSet.getString(2));
            vote.getAssociation().getVoteEvents().getVoteOptions().setContent(resultSet.getString(3));
            vote.getStudent().setName(resultSet.getString(4));
            vote.setPoll(resultSet.getInt(5));
            vote.setVote_date(resultSet.getDate(6));
            votes.add(vote);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetVotes(List<Vote> votes,String name,String select) throws SQLException {
        String sql="call GetVotes2(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        pstmt.setString(2,select);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Vote vote=new Vote();
            vote.getAssociation().setAsname(resultSet.getString(1));
            vote.getAssociation().getVoteEvents().setTitle(resultSet.getString(2));
            vote.getAssociation().getVoteEvents().getVoteOptions().setContent(resultSet.getString(3));
            vote.setPoll(resultSet.getInt(4));
            votes.add(vote);
        }
        resultSet.close();
        pstmt.close();
    }

    public static int QuestionAdd(Association association) throws SQLException {
        String sql1="call GetQno(?,?)";
        int asno=association.getAsno();
        int qno;
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        pstmt.setInt(1,asno);
        while(true){
            Random random=new Random();
            qno=random.nextInt(10000);
            pstmt.setInt(2,qno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        String sql2="call QuestionAdd(?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,association.getAsno());
        pstmt2.setInt(2,qno);
        pstmt2.setString(3,association.getQuestions().getContent());
        pstmt2.setDate(4,association.getQuestions().getDdl_date());
        pstmt2.executeUpdate();
        pstmt2.close();
        return qno;
    }
    public static int QuestionDelete(int asno,int qno) throws SQLException {
        String sql="call QuestionDelete(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,qno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetQuestions(List<Association> associations,String name) throws SQLException {
        String sql="call GetQuestions(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getQuestions().setQno(resultSet.getInt(3));
            association.getQuestions().setContent(resultSet.getString(4));
            association.getQuestions().setDdl_date(resultSet.getDate(5));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int InvestigationAdd(Investigation investigation) throws SQLException {
        String sql="call InvestigationAdd(?,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,investigation.getAssociation().getAsno());
        pstmt.setInt(2,investigation.getStudent().getId());
        pstmt.setInt(3,investigation.getAssociation().getQuestions().getQno());
        pstmt.setString(4,investigation.getAnswer());
        pstmt.setDate(5,investigation.getAnswer_date());
        int count= pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static int InvestigationDelete(int asno,int sno,int qno) throws SQLException {
        String sql="call InvestigationDelete(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        pstmt.setInt(3,qno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    public static void GetInvestigations(List<Investigation> investigations,String name) throws SQLException {
        String sql="call GetInvestigations1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Investigation investigation=new Investigation();
            investigation.getAssociation().setAsname(resultSet.getString(1));
            investigation.getAssociation().getQuestions().setContent(resultSet.getString(2));
            investigation.getStudent().setName(resultSet.getString(3));
            investigation.setAnswer(resultSet.getString(4));
            investigation.setAnswer_date(resultSet.getDate(5));
            investigations.add(investigation);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetInvestigations(List<Investigation> investigations,String name,String select) throws SQLException {
        String sql="call GetInvestigations2(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        pstmt.setString(2,select);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Investigation investigation=new Investigation();
            investigation.getAssociation().setAsname(resultSet.getString(1));
            investigation.getAssociation().getQuestions().setContent(resultSet.getString(2));
            investigation.setCount(resultSet.getInt(3));
            investigations.add(investigation);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int BBSAdd(BBS bbs) throws SQLException {
        String sql1="call GetBno(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        int asno=bbs.getAssociation().getAsno();
        int sno=bbs.getStudent().getId();
        int bno;
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        while(true){
            Random random=new Random();
            bno=random.nextInt(10000);
            pstmt.setInt(3,bno);
            ResultSet resultSet=pstmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                pstmt.close();
                break;
            }
        }
        String sql2="call BBSAdd(?,?,?,?,?)";
        PreparedStatement pstmt2=connection.prepareStatement(sql2);
        pstmt2.setInt(1,asno);
        pstmt2.setInt(2,sno);
        pstmt2.setInt(3,bno);
        pstmt2.setString(4,bbs.getContent());
        pstmt2.setDate(5,bbs.getSpeak_date());
        pstmt2.executeUpdate();
        pstmt2.close();
        return bno;
    }
    public static int BBSDelete(int asno,int sno,int bno) throws SQLException {
        String sql="call BBSDelete(?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,asno);
        pstmt.setInt(2,sno);
        pstmt.setInt(3,bno);
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void GetBBS(List<BBS> bbsList,String name) throws SQLException {
        String sql="call GetBBS(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            BBS bbs=new BBS();
            bbs.getAssociation().setAsno(resultSet.getInt(1));
            bbs.getAssociation().setAsname(resultSet.getString(2));
            bbs.getStudent().setId(resultSet.getInt(3));
            bbs.getStudent().setName(resultSet.getString(4));
            bbs.setBno(resultSet.getInt(5));
            bbs.setContent(resultSet.getString(6));
            bbs.setSpeak_date(resultSet.getDate(7));
            bbsList.add(bbs);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetTeachers(List<Teacher> teachers, String name) throws SQLException {
        String sql="call GetTeachers(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Teacher teacher=new Teacher();
            teacher.setId(resultSet.getInt(1));
            teacher.setName(resultSet.getString(2));
            teachers.add(teacher);
        }
        resultSet.close();
        pstmt.close();
    }
    public static int MemberUpdate(Users users) throws SQLException {
        String status=users.getStatus();
        String sql="call MemberUpdate(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        pstmt=connection.prepareStatement(sql);
        pstmt.setString(1,status);
        pstmt.setInt(2,users.getId());
        pstmt.setString(3,users.getName());
        pstmt.setString(4,users.getDepartment());
        pstmt.setDouble(5,users.getCreait_or_Salary());
        pstmt.setInt(6,users.getUno());
        int count=pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    public static void AssociationCount(Association association) throws SQLException {
        String sql ="call AssociationCount()";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            association.setCount(resultSet.getInt(1));
        }
        resultSet.close();
        pstmt.close();
    }
    public static void AssociationCount1(List<Association> associations,String name) throws SQLException {
        String sql ="call AssociationCount1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setCount(resultSet.getInt(1));
            association.setMgn_sname(resultSet.getString(2));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void AssociationCount2(List<Association> associations,String name) throws SQLException {
        String sql ="call AssociationCount2(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setCount(resultSet.getInt(1));
            association.setMgn_tname(resultSet.getString(2));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void AssociationCount3(List<Association> associations,String name) throws SQLException {
        String sql ="call AssociationCount3(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setCount(resultSet.getInt(1));
            association.setState(resultSet.getString(2));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void MemberCount(Participate participate) throws SQLException {
        String sql="call MemberCount()";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            participate.setCount(resultSet.getInt(1));
        }
        resultSet.close();
        pstmt.close();
    }
    public static void MemberCount1(List<Participate> participates,String name) throws SQLException {
        String sql="call MemberCount1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Participate participate=new Participate();
            participate.getAssociation().setAsno(resultSet.getInt(1));
            participate.getAssociation().setAsname(resultSet.getString(2));
            participate.setCount(resultSet.getInt(3));
            participates.add(participate);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void MemberCount2(List<Participate> participates,String name) throws SQLException {
        String sql="call MemberCount2(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Participate participate=new Participate();
            participate.getAssociation().setAsno(resultSet.getInt(1));
            participate.getAssociation().setAsname(resultSet.getString(2));
            participate.getStudent().setDepartment(resultSet.getString(3));
            participate.setCount(resultSet.getInt(4));
            participates.add(participate);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void MemberCount3(List<Participate> participates,String name) throws SQLException {
        String sql="call MemberCount3(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Participate participate=new Participate();
            participate.getAssociation().setAsno(resultSet.getInt(1));
            participate.getAssociation().setAsname(resultSet.getString(2));
            participate.setState(resultSet.getString(3));
            participate.setCount(resultSet.getInt(4));
            participates.add(participate);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void ActivilityCount(Acility acility) throws SQLException {
        String sql="call ActivilityCount()";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            acility.setCount(resultSet.getInt(1));
        }
        resultSet.close();
        pstmt.close();
    }
    public static void ActiviltyCount1(List<Association> associations,String name) throws SQLException {
        String sql="call ActivilityCount1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setCount(resultSet.getInt(3));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void ActiviltyCount2(List<Association> associations,String name) throws SQLException {
        String sql="call ActivilityCount2(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setState(resultSet.getString(3));
            association.getAcilitiy().setCount(resultSet.getInt(4));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void FieldUseCount(Located located) throws SQLException {
        String sql="call FieldUseCount()";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            located.setCount(resultSet.getInt(1));
        }
        resultSet.close();
        pstmt.close();
    }
    public static void FieldUseCount1(List<Located> locateds,String name) throws SQLException {
        String sql="call FieldUseCount1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Located located=new Located();
            located.getActilityLoaction().setLno(resultSet.getInt(1));
            located.getActilityLoaction().setAddress(resultSet.getString(2));
            located.setCount(resultSet.getInt(3));
            locateds.add(located);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void FieldUseCount2(List<Located> locateds,String name) throws SQLException {
        String sql="call FieldUseCount2(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Located located=new Located();
            located.getAssociation().setAsno(resultSet.getInt(1));
            located.getAssociation().setAsname(resultSet.getString(2));
            located.getActilityLoaction().setLno(resultSet.getInt(3));
            located.getActilityLoaction().setAddress(resultSet.getString(4));
            located.setCount(resultSet.getInt(5));
            locateds.add(located);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void FieldUseCount3(List<Located> locateds,String name) throws SQLException {
        String sql="call FieldUseCount3(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet= pstmt.executeQuery();
        while(resultSet.next()){
            Located located=new Located();
            located.getAssociation().setAsno(resultSet.getInt(1));
            located.getAssociation().setAsname(resultSet.getString(2));
            located.getAssociation().getAcilitiy().setAcno(resultSet.getInt(3));
            located.getAssociation().getAcilitiy().setAcname(resultSet.getString(4));
            located.getActilityLoaction().setLno(resultSet.getInt(5));
            located.getActilityLoaction().setAddress(resultSet.getString(6));
            located.setCount(resultSet.getInt(7));
            locateds.add(located);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void TaskCount(Task task) throws SQLException {
        String sql="call TaskCount()";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            task.setCount(resultSet.getInt(1));
            task.setPeople_haven(resultSet.getInt(2));
            task.setPeople_needed(resultSet.getInt(3));
        }
        resultSet.close();
        pstmt.close();
    }
    public static void TaskCount1(List<Association> associations,String name) throws SQLException {
        String sql="call TaskCount1(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().getTask().setCount(resultSet.getInt(3));
            association.getAcilitiy().getTask().setPeople_haven(resultSet.getInt(4));
            association.getAcilitiy().getTask().setPeople_needed(resultSet.getInt(5));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void TaskCount2(List<Association> associations,String name) throws SQLException {
        String sql="call TaskCount2(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setAcname(resultSet.getString(3));
            association.getAcilitiy().getTask().setCount(resultSet.getInt(4));
            association.getAcilitiy().getTask().setPeople_haven(resultSet.getInt(5));
            association.getAcilitiy().getTask().setPeople_needed(resultSet.getInt(6));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void TaskCount3(List<Association> associations,String name) throws SQLException {
        String sql="call TaskCount3(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setAcname(resultSet.getString(3));
            association.getAcilitiy().getTask().getActilityLoaction().setAddress(resultSet.getString(4));
            association.getAcilitiy().getTask().setCount(resultSet.getInt(5));
            association.getAcilitiy().getTask().setPeople_haven(resultSet.getInt(6));
            association.getAcilitiy().getTask().setPeople_needed(resultSet.getInt(7));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }

    public static void GetAsnos(List<Association> associations,String name,String state) throws SQLException {
        String sql="call GetAsnos(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        pstmt.setString(2,state);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetAcnos(List<Association> associations,String name,String state) throws SQLException {
        String sql="call GetAcnos(?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        pstmt.setString(2,state);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setAcno(resultSet.getInt(3));
            association.getAcilitiy().setAcname(resultSet.getString(4));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetTnums(List<Association> associations,String name) throws SQLException {
        String sql="call GetTnums(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getAcilitiy().setAcno(resultSet.getInt(3));
            association.getAcilitiy().setAcname(resultSet.getString(4));
            association.getAcilitiy().getTask().setTnum(resultSet.getInt(5));
            association.getAcilitiy().getTask().setAssignment(resultSet.getString(6));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void GetOnos(List<Association> associations,String name) throws SQLException {
        String sql="call GetOnos(?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        name=SharedFunction.ProcessName(name);
        pstmt.setString(1,name);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            Association association=new Association();
            association.setAsno(resultSet.getInt(1));
            association.setAsname(resultSet.getString(2));
            association.getVoteEvents().setEno(resultSet.getInt(3));
            association.getVoteEvents().setTitle(resultSet.getString(4));
            association.getVoteEvents().getVoteOptions().setOno(resultSet.getInt(5));
            association.getVoteEvents().getVoteOptions().setContent(resultSet.getString(6));
            associations.add(association);
        }
        resultSet.close();
        pstmt.close();
    }
    public static void ConnectionClose() throws SQLException {
        connection.close();
    }
}
