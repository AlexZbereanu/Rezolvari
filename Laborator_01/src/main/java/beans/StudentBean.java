package beans;

public class StudentBean implements java.io.Serializable{
    private int ID = 0;
    private String nume = null;
    private String prenume = null;
    private int varsta = 0;
    private String nrMatricol = null;
    private int an = 0;
    private String specializare = null;

    public StudentBean(){

    }

    public String getNume(){
        return nume;
    }

    public void setNume(String nume){
        this.nume = nume;
    }

    public String getPrenume(){
        return prenume;
    }

    public void setPrenume(String prenume){
        this.prenume = prenume;
    }

    public int getVarsta(){
        return varsta;
    }

    public void setVarsta(int varsta){
        this.varsta = varsta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNrMatricol() {
        return nrMatricol;
    }

    public void setNrMatricol(String nrMatricol) {
        this.nrMatricol = nrMatricol;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }
}
