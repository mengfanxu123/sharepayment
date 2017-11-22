<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.navbar {
	background-color: #BBDEFB;
}

a {
	color: #21212;
}

a:hover {
	color: #90CAF9;
}
</style>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">EasyTransfer</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${contextPath}/user/edit">profile</a></li>
			<li class="active"><a href="${contextPath}/bankAccount/list">show
					payment list</a></li>
			<!-- <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
          
        </ul>
      </li> -->
			<li><a href="${contextPath}/bankAccount/add">bind card</a></li>
			<li><a href="${contextPath}/user/addFriends">add friends</a></li>
			<li><a href="${contextPath}/user/seeRequest">friend massage</a></li>

		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Sign Up</a></li>
			<!-- <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li> -->
		</ul>
	</div>
	</nav>


	<div class="btn-group btn-group-justified">
		<a href="${contextPath}/user/sendRequest" class="btn btn-primary">add
			friends,send</a> <a href="${contextPath}/transfer/listPublic"
			class="btn btn-primary">show public active</a> <a
			href="${contextPath}/transfer/listFriends" class="btn btn-primary">show
			friends</a> <a href="${contextPath}/transfer/history"
			class="btn btn-primary">show your transfer</a> <a
			href="${contextPath}/transfer/history" class="btn btn-primary">manage
			your payment</a>


	</div>
	<div class="jumbotron">
		<h1>Welcome;${user.firstName},begin quick and easy transfer</h1>
		<p>only click one button, complete all1!</p>
		<p>
			<a class="btn btn-primary btn-lg"
				href="${contextPath}/transfer/sendMoney" role="button">transfer
				now</a>
		</p>
	</div>




	<a>Welcome, ${user.firstName} </a>
	<a href="${contextPath}/user/edit">edit profile</a>
	<br />
	<a href="${contextPath}/bankAccount/add">add your bankAccount</a>
	<br />
	<a href="${contextPath}/transfer/history">see transfer history</a>
	<br />
	<a href="${contextPath}/transfer/sendMoney">send money </a>
	<br />
	<a href="${contextPath}/bankAccount/list">Show account list </a>
	<br />
	<a href="${contextPath}/user/addFriends">add friends </a>
	<br />
	<a href="${contextPath}/user/sendRequest">send request to friends
	</a>
	<br />
	<a href="${contextPath}/user/seeRequest">see add friend massage </a>
	<br />
	<a href="${contextPath}/transfer/listFriends">list friends </a>
	<br />
	<a href="${contextPath}/transfer/listPublic">list public </a>
	<br />


</body>
</html>