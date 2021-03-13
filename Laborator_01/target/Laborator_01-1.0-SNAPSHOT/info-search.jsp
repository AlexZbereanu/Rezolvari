<html>
    <body>
        <%
            Object nume = request.getAttribute("nume");
            Object prenume = request.getAttribute("prenume");
            Object varsta = request.getAttribute("varsta");
            Object nrMatricol = request.getAttribute("nrMatricol");
            Object an = request.getAttribute("an");
            Object specializare = request.getAttribute("specializare");
            if(nume != null && prenume != null && varsta != null && nrMatricol != null && an != null && specializare != null)
            {
                out.println("<p>Informatii despre student gasit:</p>");
                out.println("<div>Numele studentului:" + nume + "</div><br/>");
                out.println("<div>Prenumele studentului:" + prenume + "</div><br/>");
                out.println("<div>Varsta studentului:" + varsta + "</div><br/>");
                out.println("<div>Numarul Matricol al studentului:" + nrMatricol + "</div><br/>");
                out.println("<div>An in care se afla student:" + an + "</div><br/>");
                out.println("<div>Specializarea studentului:" + specializare + "</div><br/>");
            }
            else
            {
                out.println("<p>Nu a fost gasit nici un student!</p>");
            }
        %>
           <p>
               <a href="./search.jsp">Cauta din nou</a>
           </p>
           <p>
                <a href="./index.jsp">Home</a>
           </p>
    </body>
</html>