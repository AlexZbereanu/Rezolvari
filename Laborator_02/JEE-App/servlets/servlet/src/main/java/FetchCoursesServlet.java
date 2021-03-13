import ejb.Courses;
import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FetchCoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        //creem o pagina noua in care
        StringBuilder responseText = new StringBuilder();
        responseText.append("<html>\n<head><title>Lista cursuri</title></head>\n<body>\n");
        responseText.append("<h2>Lista cursuri</h2>");
        responseText.append("<table border='1'><thead><tr><th>ID</th><th>Nume</th><th>Numar credite</th><th>Profesor titular</th></thead>");
        responseText.append("<tbody>");

        // preluare date studenti din baza de date
        TypedQuery<Courses> query = em.createQuery("select course from Courses course", Courses.class);
        List<Courses> results = query.getResultList();
        for (Courses course : results) {
            // se creeaza cate un rand de tabel HTML pentru fiecare student gasit
            responseText.append("<tr><td>" + course.getId() + "</td><td>" +
                    course.getNume() + "</td><td>" + course.getNrCredite() +
                    "</td><td>" + course.getProfesorTitular() + "</td>\n");
        }

        responseText.append("</tbody></table><br /><br />");
        responseText.append("<h3>Formular pentru actualizarea unui curs</h3><form action=\"./update-courses\" method=\"post\">\n" +
                "ID: <input type =\"number\" name=\"id\"/><br />\n" +
                "Nume: <input type =\"text\" name=\"nume\"/><br />\n"+
                "Numar credite: <input type =\"number\" name=\"credite\"/><br />\n"+
                "Profesor: <input type =\"text\" name=\"profesor\"/><br />\n"+
                "<button type=\"submit\" name=\"submit\">Actualizare</button></form><br /><br />\n");
        responseText.append("<h3>Formular pentru stergere</h3><form action=\"./delete-courses\" method=\"post\">\n" +
                "ID: <input type =\"number\" name=\"id\"/><br />\n" +
                "<button type=\"submit\" name=\"submit\">Sterge</button></form><br /><br />\n");
        responseText.append("<a href='./'>Inapoi la meniul principal</a><br />");
        responseText.append("<a href='./formular-courses.jsp'>Adaugare nou curs!</a>");
        responseText.append("\n</body>\n</html>");

        // inchidere EntityManager
        em.close();
        factory.close();

        // trimitere raspuns la client
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }
}
