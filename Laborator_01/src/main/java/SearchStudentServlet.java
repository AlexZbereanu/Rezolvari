import beans.StudentBean;
import beans.Students;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
        File file = new File("D:/SEMESTRU_2/Sisteme_Distribuite/Rezolvari/Laborator_01/student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }

        XmlMapper xmlMapper = new XmlMapper();

        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String nrMatricol = request.getParameter("nrMatricol");

        Students grupa = xmlMapper.readValue(file, Students.class);
        Vector<StudentBean> students = grupa.getStudents();

        //determinam dupa ce cautam studentul
        boolean dupaNume = (nume.equals("")), dupaPrenume = (prenume.equals("")), dupaNrMatricol = (nrMatricol.equals(""));
        StudentBean needed_student=new StudentBean();
        for(StudentBean student: students)
        {
            if(dupaNume && student.getNume().equals(nume))
            {
                needed_student = student;
                break;
            }
            else if(dupaPrenume && student.getPrenume().equals(prenume))
            {
                needed_student = student;
                break;
            }
            else if(dupaNrMatricol && student.getNume().equals(nume))
            {
                needed_student = student;
                break;
            }
        }

        request.setAttribute("nume", needed_student.getNume());
        request.setAttribute("prenume", needed_student.getPrenume());
        request.setAttribute("varsta", needed_student.getVarsta());
        request.setAttribute("nrMatricol", needed_student.getNrMatricol());
        request.setAttribute("an", needed_student.getAn());
        request.setAttribute("specializare", needed_student.getSpecializare());

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./info-search.jsp").forward(request, response);
    }
}