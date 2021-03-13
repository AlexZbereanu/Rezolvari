import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //luam parametrii primiti
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        Integer varsta = Integer.parseInt(request.getParameter("varsta"));

        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        //realizam updatarea
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("update StudentEntity s set s.nume=\'" + nume + "\',s.prenume =\'"+prenume+"\',s.varsta="+varsta +
                        " where s.id = "+id);
        int updateCount = query.executeUpdate();
        transaction.commit();

        // trimitere raspuns inapoi la client
        response.setContentType("text/html");
        response.getWriter().println("Datele au fost actualizate in baza de date." +
                "<br /><br /><a href='./'>Inapoi la meniul principal</a>"+
                "<br /><br /><a href='./fetch-student-list'>Afiseaza studenti</a>");

    }

}
