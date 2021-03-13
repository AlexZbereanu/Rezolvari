<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular curs</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular curs</h3>
		Introduceti datele despre curs:
		<form action="./process-course" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			Numar credite: <input type="number" name="nrCredite" />
			<br />
			Profesor titular: <input type="text" name="profesor" />
			<br />
			<br />
			<button type="submit" name="submit">Trimite</button>
		</form>
	</body>
</html>