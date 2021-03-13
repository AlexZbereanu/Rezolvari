import beans.StudentBean;
import beans.Students;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;

public class FillInput extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
        File file = new File("D:/SEMESTRU_2/Sisteme_Distribuite/Rezolvari/Laborator_01/student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }

        //sa cauti studentul pe care il trimit

        XmlMapper xmlMapper = new XmlMapper();
        Students studenti = xmlMapper.readValue(file,Students.class);

        int id = Integer.parseInt(request.getParameter("id"));
        StudentBean bean = studenti.getStudents().get(id-1);

        request.setAttribute("id", id);
        request.setAttribute("nume", bean.getNume());
        request.setAttribute("prenume", bean.getPrenume());
        request.setAttribute("varsta", bean.getVarsta());
        request.setAttribute("nrMatricol", bean.getNrMatricol());
        request.setAttribute("an", bean.getAn());
        request.setAttribute("specializare", bean.getSpecializare());

        request.getRequestDispatcher("./update.jsp").forward(request, response);
    }
}