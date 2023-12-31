<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.generation.msn.model.entities.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Insert title here</title>
        <link rel="stylesheet"href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet"href="https://db.onlinewebfonts.com/c/2b9a54453c1a3cbc651c0788ade94e4a?family=Noto+IKEA+Latin+Regular">
        <link rel="stylesheet"href="https://db.onlinewebfonts.com/c/b269e509d0e2d442240b8df6035c9999?family=Noto+IKEA+Latin+Bold"> 
        <style>
            bdiv.mainframe
            {
                max-height: 100vh;
                background-color: rgb(235, 241, 243);
            }
            div.bannertop
            {
                position:relative;
                width:100vw;
                height:50px;
                background-color: rgb(235, 241, 243);
            }
            div.bannerleft
            {
                position:relative;
                width:50px;
                height:50vw;
                background-color: rgb(235, 241, 243);
            }
            div.chatcol
            {
                position:absolute;
                top:50px;
                left:350px;
                width:100vw;
                height:100%; 
                box-shadow:rgb(243, 248, 248);;
                border:1px solid rgb(220,220,220);
                background-color: rgb(255, 255, 255);    
                
            }
            div.contactcol
            {
                overflow:hidden;
                position:absolute;
                top:50px;
                left:50px;
                height:100%;
                width:320px;
                border-top-left-radius:10px;
                border:1px solid rgb(220,220,220);
                background-color: rgb(255, 255, 255);
            }
            div.contactlist
            {
                position:relative;
                top:30px;
                left:10px;
                width:300px;
                height:100%;
                border-radius: 5px;
                border:1px solid  rgb(240,240,240);
            }
            input
            {
                position:relative;
                top:12px;
                left: 25px;
                width:270px;
                height:30px;
                margin-bottom:20px;
                text-indent: 10px;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 95%;
                border-radius: 4px;
                border:1px solid rgb(240,240,240);
                border-bottom:1px solid rgb(180,180,180);
            }
            input.submit
            {
                text-indent: 0px;
            }
            div.message
            {
                padding-top:10px;
                text-indent:15px;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 88%;
                color:red;
            }
            div.register
            {
            	position:relative;
            	 left: 25px;
            	width:270px;
                height:30px;
                text-align: center;
            }
			button
			{
				position:relative;
				top:12px;
            	width:270px;
                height:30px;
                text-align: center;
                border-radius: 4px;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 95%;
                border:1px solid rgb(240,240,240);
                border-bottom:1px solid rgb(180,180,180);
			}
        </style> 
    </head>
    <body>
        <div class="mainframe">
            <div class="bannertop"></div>
            <div class="bannerleft"></div>

            <div class="chatcol">
                
            </div>
            <div class="contactcol">
                <h4 style="padding-left:25px;padding-top:8px"><b>Login</b></h4>
                <form:form action="login" method="post" modelAttribute="credentials">
                    <input type="text" name="mail" placeholder="Inserisci nome Utente">
                    <input type="text" name="password" type="password" placeholder="Inserisci password">
                    <input class="submit" type="submit" value="Accedi">
                </form:form>
                <div class="register">
               		 <a href="/register"><button>Registrati</button></a>
                </div>
                <div class="contactlist">
                    <div class="message">
                        ${errormessage}
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>