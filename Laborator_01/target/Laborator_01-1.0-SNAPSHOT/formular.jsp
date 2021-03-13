<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular student</h3>
		Introduceti datele despre student:
		<form action="./process-student" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			Prenume: <input type="text" name="prenume" />
			<br />
			Varsta: <input type="number" name="varsta" />
			<br />
			Nr matricol: <input type="text" name="nrMatricol" />
            <br />
            An: <input type="number" name="an" />
            <br />
            Specializare: <input type="text" name="specializare" />
            <br />
			<br />
			<button type="submit" name="submit">Trimite</button>
		</form>
		<p>
			<a href="./index.jsp">Home</a>
		</p>
	</body>
</html>