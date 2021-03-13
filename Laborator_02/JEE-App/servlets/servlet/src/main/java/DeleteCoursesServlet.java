import javax.persistence.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteCoursesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //luam parametrii primiti
        String course_id = request.getParameter("id");

        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        //realizam cautarea sa vedem daca avem acel id
        Query query = em.createQuery("select c.id from Courses c where c.id=" + course_id);
        List resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            // trimitere raspuns inapoi la client
            response.setContentType("text/html");
            response.getWriter().println("Cursul cu id-ul " + course_id + " nu exista!" +
                    "<br /><br /><a href='./'>Inapoi la meniul principal</a>"+
                    "<br /><br /><a href='./fetch-courses'>Afiseaza cursuri</a>");
        }
        else
        {
            //realizam stergerea
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            query = em.createQuery("delete from Courses c where c.id="+course_id);
            query.executeUpdate();
            transaction.commit();

            // trimitere raspuns inapoi la client
            response.setContentType("text/html");
            response.getWriter().println("Cursul selectat a fost sters!" +
                    "<br /><br /><a href='./'>Inapoi la meniul principal</a>"+
                    "<br /><br /><a href='./fetch-courses'>Afiseaza cursuri</a>");
        }

    }
}
