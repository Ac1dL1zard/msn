<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addnewuser" method="post" modelAttribute="newuser">
	
		 <input type="text" name="mail" placeholder="Inserisci email">
		 <input type="text" name="nickname" placeholder="Inserisci nickname">
		 <input type="text" name="password" placeholder="Inserisci password">
		 
         <input class="submit" type="submit" value="Aggiungi">
	
	
	</form>
</body>
</html>