package entity;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.util.Enumeration;

public class SharedFunction {
    public static boolean JudgeDate(int year,int month,int day) {
        boolean judge;
        if(month<1||month>12) {
            System.out.print("月份不合法");
            judge=false;}
        else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
            if(day>=1&&day<=31) {
                judge=true;}
            else {
                judge=false;
                System.out.print("日期不合法");
            }
        }
        else if(month==2) {
            if(year%400==0||(year%4==0&&year%100!=0)) {
                if(day>=1&&day<=29) {
                    judge=true;
                }
                else {
                    judge=false;
                    System.out.print("日期不合法");
                }
            }
            else {
                if(day>=1&&day<=28){
                    judge=true;
                }
                else {
                    judge=false;
                    System.out.print("日期不合法");
                }
            }
        }
        else {
            if(day>=1&&day<=30){
                judge=true;
            }
            else {
                judge=false;
                System.out.print("日期不合法");
            }
        }
        return judge;
    }
    public static boolean JudgeEmpty(String jyear1, String jmonth1, String jday1, String jyear2, String jmonth2, String jday2){
        return !(jyear1.equals("")||jyear2.equals("")||jmonth1.equals("")||jmonth2.equals("")||jday1.equals("")||jday2.equals(""));
    }
    public static boolean JudgeEmpty(String jyear, String jmonth, String jday){
        return !(jyear.equals("")||jmonth.equals("")||jday.equals(""));
    }
    public static void FitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width + 10);
            myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }
    public static String ProcessName(String name){
        return "%"+name+"%";
    }
}
