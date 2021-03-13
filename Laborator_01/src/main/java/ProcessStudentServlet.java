import beans.Students;
import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.Vector;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String nrMatricol = request.getParameter("nrMatricol");
        int an = Integer.parseInt(request.getParameter("an"));
        String specializare = request.getParameter("specializare");


        // initializare serializator Jackson
        XmlMapper mapper = new XmlMapper();

        // creare bean si populare cu date
        StudentBean bean = new StudentBean();
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setAn(an);
        bean.setNrMatricol(nrMatricol);
        bean.setSpecializare(specializare);

        File file = new File("D:/SEMESTRU_2/Sisteme_Distribuite/Rezolvari/Laborator_01/student.xml");
        Students studenti = new Students();

        if(file.length() == 0) {
            bean.setID(1);
            studenti.add(bean);
        }
        else
        {
            studenti = mapper.readValue(file, Students.class);
            bean.setID(studenti.getStudents().size()+1);
            studenti.add(bean);
        }

        // serializare bean sub forma de string XML
        mapper.writeValue(file, studenti);

        // se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
        request.setAttribute("studenti", studenti.getStudents());
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}