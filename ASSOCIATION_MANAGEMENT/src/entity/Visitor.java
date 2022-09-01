package entity;

public class Visitor extends Users{
    @Override
    public double getCreait_or_Salary() {
        return -1;
    }

    @Override
    public void setCreait_or_Salary(double creait) {
        return;
    }
}
