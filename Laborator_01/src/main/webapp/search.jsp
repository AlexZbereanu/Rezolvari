<html xmlns:jsp="http://java.sun.com/JSP/Page">
       	<head>
       		<title>Shearch student</title>
       		<meta charset="UTF-8" />
       	</head>
       	<body>
       		<h3>Shearch student</h3>
       		Cautati student dupa urmatoarele date:
       		<form action="./search-student" method="post">
       			Nume: <input type="text" name="nume" value=""/>
       			<br />
       			Prenume: <input type="text" name="prenume" value=""/>
       			<br />
       			Nr matricol: <input type="text" name="nrMatricol" value=""/>
                <br />
       			<button type="submit" name="submit">Cautare</button>
       		</form>
       	</body>
</html>