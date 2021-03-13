<%--
  Created by IntelliJ IDEA.
  User: zbere
  Date: 3/1/2021
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stergere student</title>
</head>
<body>
<h3>Informatii student</h3>
<!--populare bean cu informatii din cererea http-->
<jsp:useBean id="studentBean" class="beans.StudentBean"/>
<jsp:setProperty name="studentBean" property="nume"
                 value="<%=request.getAttribute("nume")%>"/>
<jsp:setProperty name="studentBean" property="prenume"
                 value="<%=request.getAttribute("prenume")%>"/>
<jsp:setProperty name="studentBean" property="varsta"
                 value="<%=request.getAttribute("varsta")%>"/>

<!-- folosim bean-ul pentru afisare informatii-->
<p>Urmatoarele informatii au fost introduse:</p>
<ul type="bullet">
    <li>Nume:<jsp:getProperty name="studentBean" property="nume"/></li>
    <li>Prenume:<jsp:getProperty name="studentBean" property="prenume"/></li>
    <li>Varsta:<jsp:getProperty name="studentBean" property="varsta"/></li>
    <li>Anul nasterii:<%
        Object anNastere = request.getAttribute("anNastere");
        if(anNastere != null)
            out.print(anNastere);
        else
            out.print("tzeapa");
    %></li>
</ul>

<h3>Stergere student</h3>
<form action="delete-student" method="post">
    <button type="submit" name="submit">Stergere</button>
</form>
</body>
</html>
