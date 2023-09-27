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
                max-height: 90vh;
                background-color: rgb(230, 240, 236);
            }
            div.bannertop
            {
            	z-index:50;
                position:relative;
                width:100vw;
                height:50px;
                background-color: rgb(230, 240, 236);
            }
            div.bannerleft
            {
                position:relative;
                width:50px;
                height:50vw;
                background-color: rgb(230, 240, 236);
            }
            div.chatcol
            {
            	overflow-x: hidden;
  				overflow-y: auto;
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
                background-clip:border-box;;
                border:1px solid rgb(220,220,220);
                background-color: rgb(255, 255, 255);
            }
            div.contactlist
            {
            	overflow-x: hidden;
  				overflow-y: auto;
                position:relative;
                top:30px;
                left:10px;
                width:300px;
                height:73vh;
                border-radius: 5px;   
                border:1px solid  rgb(240,240,240);
                border-bottom:1px solid rgb(180,180,180);
            }
            input
            {
                position:relative;
                top:12px;
                left: 25px;
                width:270px;
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
            	top:87vh;
            	right:25px;
            	padding:5px;
            	width:270px;
            	height:35px;
            	text-align:center;
            	border-radius:100px;
            	font-weight:bold;
            	font-size:120%;
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
            	 font-family: Arial, Helvetica, sans-serif;
                font-size: 105%;
            	width:100%;
            	border-radius:5px;
                transition: background-color 0.5s ease;
                
            }
             
            div.msggrid:hover
            {
            	position:relative;
            	width:100%;
            	background-color:rgb(255,255,255);
            	background-clip: border-box;
            }
            div.contact
            {
                position: relative;
                margin-bottom:5px;
                font-family: Arial, Helvetica, sans-serif;
                font-weight:bold;
                text-indent:15px;
                width:300px;
                height:55px;
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
            	padding:0px;
            	top:5px;
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
			div.msgbar
			{
				z-index:0;
				position:absolute;
				bottom:-5vh;
				left:50px;
				width:100%;
				height:15vh;
				background-color: rgb(230, 240, 236);
				border-top:1px solid rgb(220,220,220);
			}
			.send
			{
				position:absolute;
				top:1vw;
				left:400px;
				width:50vw;
				height:50px;
				border-radius:100px;
				border:1px solid rgb(220,220,220);
				text-indent:30px;
			}
			a.logout
			{
				position:relative;
				top:5px;
				left:8px;
				text-decoration:none;
				font-weight:bold;
			}
			img.logout
			{
				width:40px;
			}
        </style> 
    </head>
    <body>
        <div class="mainframe">
            <div class="bannertop">
            	  <a class="logout" href="logout"><img class="logout" alt="" src="http://localhost:8080/logout.png"></a>
            </div>
            <div class="bannerleft"></div>
            <div class="chatcol">
            <br><br>
            	<img style="position:absolute;bottom:10vh;height:100vh;opacity: 0.15" alt="!" src="http://localhost:8080/img.jpg">
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
            <div class="msgbar">
            	<c:if test="${messages!=null}">
	            	 <form:form action="sendmessage" method="post" modelAttribute="message">
	            		<input class="send" name="content" type="text" placeholder="Inizia a scrivere">
	            		<input type="hidden" name="sender_nickname" value="${user.getNickname()}">
	            		<input type="hidden" name="id_f" value="${friendship.getId()}">
	            	  </form:form>
            	 </c:if>
            </div>
            
            
            <div class="contactcol">
                <h4 style="padding-left:25px;padding-top:8px"><b>Chat</b></h4>
                <form:form action="TODO" method="post" modelAttribute="key">
                    <input type="search" name="key" placeholder="Cerca o inizia una chat">
                </form:form>
                
                <div class="contactlist">
     				<c:forEach var="friend" items="${user.getAllFriends()}">
     					<div class="contact">
     						<a class="contactinfo"href="openchat?id=${friend.getId()}">
     							<b>${friend.getNickname()}</b>
     						</a>
     						<c:if test="${user.getFriendship(friend).getMessages().size()>0}">
	     						<p style="color:rgb(180,180,180);font-weight:normal;font-size:105;position:absolute;top:10px;"> 
	     							${user.getFriendship(friend).getLastMessage().getSender_nickname()}:${user.getFriendship(friend).getLastMessage().getContent()}
	     						</p>
	     					</c:if>
     					</div>
     				</c:forEach>
                </div>
                <a class="addfriend" href="addfriend">+</a>         
            </div>
        </div>

    </body>
</html>