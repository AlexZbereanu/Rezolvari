import javax.persistence.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateCoursesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //luam parametrii primiti
        int course_id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String profesor = request.getParameter("profesor");
        int credite = Integer.parseInt(request.getParameter("credite"));

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
            //realizam updatarea
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            query = em.createQuery("update Courses c set c.nume=\'" + nume + "\',c.nrCredite ="+credite+",c.profesorTitular=\'"+profesor +
                    "\' where c.id = "+course_id);
            query.executeUpdate();
            transaction.commit();

            // trimitere raspuns inapoi la client
            response.setContentType("text/html");
            response.getWriter().println("Datele au fost actualizate in baza de date." +
                    "<br /><br /><a href='./'>Inapoi la meniul principal</a>"+
                    "<br /><br /><a href='./fetch-courses'>Afiseaza cursuri</a>");
        }
    }
}
