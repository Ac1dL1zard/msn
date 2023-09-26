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
                left:370px;
                width:100%;
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
                width:245px;
                height:30px;
                text-indent: 10px;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 95%;
                border-radius: 4px;
                border:1px solid rgb(240,240,240);
                border-bottom:1px solid rgb(180,180,180);
            }
           	a.addfriend
            {
            	position:absolute;
            	top:70px;
            	right:25px;
            	padding:5px;
            	height:30px;
            	border-radius:4px;
            	border-bottom:1px solid rgb(180,180,180);
            	text-decoration:none;
            	background-color: rgb(240,240,240);
            }
            a.addfriend:hover
            {

            	background-color: rgb(200,200,200);
            }
            div.msggrid
            {
				padding:15px;
            	position:relative;
            	width:100%;
            	border-radius:5px;
                transition: background-color 0.5s ease;
                
            }
             
            div.msggrid:hover
            {
            	position:relative;
            	width:100%;
            	background-color:rgb(240,240,240);
            }
            div.contact
            {
                position: relative;
                margin-bottom:5px;
                width:300px;
                height:70px;
                border-radius: 6px;
                transition: background-color 0.5s ease;
            }
            div.contact:hover
            {
                background-color:rgb(240,240,240);
            }
            a.contactinfo
            {
            	position:relative;
            	padding:15px;
            	top:20px;
            	text-decoration:none;
            }
            a.contactinfo::before
			{
				content:"";
				display:block;
				position:absolute;
				top:-8px;
				left:0;
				width:300px;
				height:70px;
				text-decoration:none;
			}
        </style> 
    </head>
    <body>
        <div class="mainframe">
            <div class="bannertop"></div>
            <div class="bannerleft"></div>
            <div class="chatcol">
            <br><br>
                <c:forEach var="message" items="${messages}">
                	<c:if test="${message.getSender_nickname().equals(user.getNickname())}">
                		<div class="msggrid" style="text-align:right;padding-right:420px;"><b> ${message.getSender_nickname()}</b>
                			<br>${message.getContent()}
                		</div>
                	</c:if>
      	            <c:if test="${!message.getSender_nickname().equals(user.getNickname())}">
                		<div class="msggrid" style="padding-left:30px;"><b> ${message.getSender_nickname()}</b>
                			<br>${message.getContent()}
                		</div>
      	            </c:if>
                </c:forEach>
            </div>
            <div class="contactcol">
                <h4 style="padding-left:25px;padding-top:8px"><b>Chat</b></h4>
                <form:form action="TODO" method="post" modelAttribute="key">
                    <input type="search" name="key" placeholder="Cerca o inizia una chat">
                </form:form>
                <a class="addfriend" href="addfriend">+</a>
                <div class="contactlist">
     				<c:forEach var="friend" items="${user.getAllFriends()}">
     					<div class="contact">
     						<a class="contactinfo"href="openchat?id=${friend.getId()}">${friend.getNickname()}</a>
     					</div>
     				</c:forEach>
                </div>         
            </div>
        </div>

    </body>
</html>