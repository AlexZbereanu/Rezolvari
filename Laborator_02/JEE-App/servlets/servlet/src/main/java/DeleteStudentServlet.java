import javax.persistence.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //luam parametrii primiti
        String de_sters = request.getParameter("id");

        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        //realizam stergerea
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("delete from StudentEntity s where s.id="+de_sters);
        query.executeUpdate();
        transaction.commit();

        // trimitere raspuns inapoi la client
        response.setContentType("text/html");
        response.getWriter().println("Studentul a fost sters din baza de date." +
                "<br /><br /><a href='./'>Inapoi la meniul principal</a>"+
                "<br /><br /><a href='./fetch-student-list'>Afiseaza studenti</a>");

    }
}

