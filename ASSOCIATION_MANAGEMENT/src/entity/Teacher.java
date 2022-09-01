package entity;

import java.util.Vector;

public class Teacher extends Users{
    private double salary;

    @Override
    public double getCreait_or_Salary() {
        return salary;
    }

    @Override
    public void setCreait_or_Salary(double creait_or_salary) {
        this.salary=creait_or_salary;
    }

    public Vector<Object> GetTeachersInformation(){
        Vector<Object> objects=new Vector<>();
        objects.add(id);
        objects.add(name);
        return objects;
    }
}
