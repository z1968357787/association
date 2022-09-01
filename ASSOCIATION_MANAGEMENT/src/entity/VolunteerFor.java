package entity;

import java.util.Vector;

public class VolunteerFor {
    private Student student=new Student();
    private Association association=new Association();

    public Student getStudent() {
        return student;
    }

    public Association getAssociation() {
        return association;
    }

    public Vector<Object> GetVolunteerInformation(){
        Vector<Object> objects=new Vector<>();
        objects.addAll(student.GetVolunteerInformation());
        objects.addAll(association.GetVolunteerInformation());
        return objects;
    }
}
