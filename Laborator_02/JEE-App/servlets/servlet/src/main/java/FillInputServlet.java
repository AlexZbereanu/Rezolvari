import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FillInputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //luam parametrii primiti
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        Integer varsta = Integer.parseInt(request.getParameter("varsta"));

        //creem o pagina noua in care
        StringBuilder responseText = new StringBuilder();
        responseText.append("<html><head><title>Update</title></head><body>");
        responseText.append("<h2>Updateaza studentul</h2>");
        responseText.append("<form action=\"./update-student\" method=\"post\">\n" +
                "<input type =\"hidden\" name=\"id\" value=\""+id+"\"/><br />\n" +
                "Nume: <input type =\"text\" name=\"nume\" value=\""+nume+"\"/><br />\n"+
                "Prenume: <input type =\"text\" name=\"prenume\" value=\""+prenume+"\"/><br />\n"+
                "Varsta: <input type =\"number\" name=\"varsta\" value=\""+varsta+"\"/><br />\n"+
                "<button type=\"submit\" name=\"submit\">Actualizare</button></form>\n");
        responseText.append("</body></html>");

        // trimitere raspuns la client
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }
}
