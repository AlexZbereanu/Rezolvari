import beans.StudentBean;
import beans.Students;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {
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
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String nrMatricol = request.getParameter("nrMatricol");
        int an = Integer.parseInt(request.getParameter("an"));
        String specializare = request.getParameter("specializare");
        int id = Integer.parseInt(request.getParameter("id"));

        Students studenti = xmlMapper.readValue(file, Students.class);
        StudentBean bean = studenti.getStudents().get(id-1);

        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setNrMatricol(nrMatricol);
        bean.setAn(an);
        bean.setSpecializare(specializare);

        xmlMapper.writeValue(file, studenti);

        request.setAttribute("studenti", studenti.getStudents());

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}