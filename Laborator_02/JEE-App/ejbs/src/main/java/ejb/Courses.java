package ejb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Courses {
    @Id
    @GeneratedValue
    private int id;
    private String nume;
    private int nrCredite;
    private String profesorTitular;

    public Courses()
    {

    }

    public int getId(){return id;}

    public void setId(int id){this.id=id;}

    public String getNume(){return nume;}

    public void setNume(String _nume){nume=_nume;}

    public int getNrCredite(){return nrCredite;}

    public void setNrCredite(int _nrCredite){nrCredite=_nrCredite;}

    public  String getProfesorTitular() {return profesorTitular;}

    public void setProfesorTitular(String profesor){profesorTitular=profesor;}
}
