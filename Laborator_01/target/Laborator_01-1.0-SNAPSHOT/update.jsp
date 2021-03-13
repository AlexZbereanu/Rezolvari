<html xmlns:jsp="http://java.sun.com/JSP/Page">
       	<head>
       		<title>Update student</title>
       		<meta charset="UTF-8" />
       	</head>
       	<body>
       		<h3>Update student</h3>
       		Introduceti datele noi despre student:
       		<form action="./update-student" method="post">
       		    <input type="hidden" name="id" value='<%= request.getAttribute("id") %>'/>
       			Nume: <input type="text" name="nume" value='<%= request.getAttribute("nume") %>' />
       			<br />
       			Prenume: <input type="text" name="prenume" value='<%= request.getAttribute("prenume") %>' />
       			<br />
       			Varsta: <input type="number" name="varsta" value='<%= request.getAttribute("varsta") %>'/>
       			<br />
       			Nr matricol: <input type="text" name="nrMatricol" value='<%= request.getAttribute("nrMatricol") %>'/>
                   <br />
                   An: <input type="number" name="an" value='<%= request.getAttribute("an") %>'/>
                   <br />
                   Specializare: <input type="text" name="specializare" value='<%= request.getAttribute("specializare") %>'/>
                   <br />
       			<br />
       			<button type="submit" name="submit">Actualizare</button>
       		</form>
			<p>
				<a href="./index.jsp">Home</a>
			</p>
       	</body>
       </html>