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

public class DeleteStudentServlet extends HttpServlet {
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
        Vector<StudentBean> students = studenti.getStudents();
        int id = Integer.parseInt(request.getParameter("id"));
        students.remove(id-1);
        for(int i=id - 1;i<students.size();i++)
        {
            students.get(i).setID(i);
        }
        xmlMapper.writeValue(file, studenti);

        request.setAttribute("studenti", students);

        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}
