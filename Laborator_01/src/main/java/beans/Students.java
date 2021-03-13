package beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Vector;

public class Students implements java.io.Serializable{
    @JsonProperty("students")
    private Vector<StudentBean> students = null;

    public Students(){
        students = new Vector<StudentBean>();
    }

    public void add(StudentBean student){
        students.add(student);
    }

    public void delete(StudentBean student){
        students.remove(student);
    }

    public Vector<StudentBean> getStudents(){
        return students;
    }

    public void setStudents(Vector<StudentBean> std){
        students = std;
    }
}
