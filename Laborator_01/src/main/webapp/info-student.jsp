<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student</h3>
		<!-- populare bean cu informatii din cererea HTTP -->
		<!-- folosirea bean-ului pentru afisarea informatiilor -->
		<ul type="bullet">

		    <%@ page import="beans.*" %>
		    <%@ page import="java.time.Year" %>
		    <%@ page import="java.util.Vector" %>

		    <%
		        Vector<StudentBean> students = (Vector<StudentBean>)request.getAttribute("studenti");
		        int anCurent;
		        int anNastere;

		        for(StudentBean student:students)
		        {
		            anCurent = Year.now().getValue();
                    anNastere = anCurent - student.getVarsta();

                    out.println("<div>Studentul " + student.getID() + " are urmatoarele informatii:</div> <br/>");
			        out.println("<li>Nume:" + student.getNume() + "</li> <br/>");
			        out.println("<li>Prenume:" + student.getPrenume() + "</li> <br/>");
			        out.println("<li>Varsta:" + student.getVarsta() + "</li> <br/>");
			        out.println("<li>Numar matricol:" + student.getNrMatricol() + "</li> <br/>");
			        out.println("<li>An:" + student.getAn() + "</li> <br/>");
			        out.println("<li>Specializare:" + student.getSpecializare() + "</li> <br/>");
			        out.println("<li>Anul nasterii:" + anNastere + "</li> <br/>");
			        out.println("<form action=\"./fill-input\" method=\"post\">");
			        out.println("<input type=\"hidden\" name=\"id\" value=\"" + student.getID() + "\"/>");
			        out.println("<button type=\"submit\" name=\"submit\">Actualizare</button></form>");

			        out.println("<form action=\"./delete-student\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + student.getID() + "\"/>");
                    out.println("<button type=\"submit\" name=\"submit\">Stergere</button></form>");
			    }
			%>
		</ul>
		<p>
             <a href="./formular.jsp">Formular student</a>
        </p>
		<p>
			<a href="./index.jsp">Home</a>
		</p>
	</body>
</html>