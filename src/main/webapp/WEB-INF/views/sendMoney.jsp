<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Make transfer</title>
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
	<h1>Please select people you want to transfer</h1>

	<h2>please input firstName LastName an Email who you want to send
		money</h2>


	<%-- <form class="form-horizontal">
	 --%>
	  <c:if test="${!empty requestScope.error}">
                <p style="color:red">Invalid credentials:make sure all information is correct: such as receiver name, and take care you account money</p>
       </c:if>
	<form:form action="${contextPath}/transfer/sendMoney"
		commandName="transferHistory" method="post" class="form-horizontal">
		<div class="form-group">

			<label for="sender" class="col-sm-2 control-label">Hello:${sessionScope.user.username}</label>
			<div class="col-sm-10">
				<!--  <input type="text" class="form-control" id="inputRecieveFrisName" placeholder="First Name"> -->
				<form:hidden path="postBy"
						value="${sessionScope.user.personID}" class="form-control" id="sender" />
					
				<font color="red"><form:errors path="firstName" /></font>
			</div>
		</div>

		<div class="form-group">

			<label for="inputRecieveFrisName" class="col-sm-2 control-label">First
				Name</label>
			<div class="col-sm-10">
				<!--  <input type="text" class="form-control" id="inputRecieveFrisName" placeholder="First Name"> -->
				<form:input path="firstName" size="30" required="required"
					class="form-control" id="inputRecieveFrisName"
					placeholder="First Name" />
				<font color="red"><form:errors path="firstName" /></font>
			</div>
		</div>
		<div class="form-group">
			<label for="inputLastName" class="col-sm-2 control-label">LastName</label>
			<div class="col-sm-10">
				<form:input path="recieverLastName" size="30" required="required"
					class="form-control" id="inputPassword3" placeholder="LastName" />
				<font color="red"><form:errors path="recieverLastName" /></font>
				<!-- <input type="password" class="form-control" id="inputPassword3" placeholder="Password"> -->
			</div>
		</div>
		<div class="form-group">
			<label for="inputAmount" class="col-sm-2 control-label">$$$</label>
			<div class="col-sm-10">
				<form:input path="amount" size="30" required="required" />
				<font color="red"><form:errors path="amount"
						class="form-control" id="inputPassword3" placeholder="amount" /></font>

				<!-- <input type="password" class="form-control" id="inputPassword3" placeholder="Password"> -->
			</div>
		</div>

		<div class="form-group">
			<label for="inputType" class="col-sm-2 control-label">Type</label>
			<div class="col-sm-10">
				<select name=type>
					<option value="public" selected>Public</option>
					<option value="friends">Friends</option>
					<option value="priavte">Private</option>
				</select>
				<!-- <input type="password" class="form-control" id="inputPassword3" placeholder="Password"> -->
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputAmount" class="col-sm-2 control-label">what
				is for</label>
			<div class="col-sm-10">
				<form:textarea path="comment" rows="8" cols="30" required="required" />
				<font color="red"><form:errors path="comment" /></font>

				<!-- <input type="password" class="form-control" id="inputPassword3" placeholder="Password"> -->
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Transfer</button>
			</div>
		</div>
	</form:form>


	<%-- <form:form action="${contextPath}/transfer/sendMoney"
		commandName="transferHistory" method="post">
		<table>
			<tr>
				<td>Hello Customer</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>
			<tr>
				<td>reciever FirstName</td>
				<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="recieverLastName" size="30"
						required="required" /> <font color="red"><form:errors
							path="recieverLastName" /></font></td>
			</tr>
			<tr>
				<td>$$$$:</td>
				<td><form:input path="amount" size="30" required="required" />
					<font color="red"><form:errors path="amount" /></font></td>
			</tr>
			<tr>
				<td>Post situation:</td>
				<td><select name=type>
						<option value="public" selected>Public</option>
						<option value="friends">Friends</option>
						<option value="priavte">Private</option>
				</select> <font color="red"> <form:errors
			path="amount" />
				</font></td>
			</tr>
			<tr>
				<td>What is for:</td>
				<td><form:textarea path="comment" rows="8" cols="30"
						required="required" /> <font color="red"><form:errors
							path="comment" /></font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="transfer" /></td>
			</tr>
		</table>
	</form:form> --%>
	<a>see who can transfer</a>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>FirstName</b></td>
			<td><b>LastName</b></td>

		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>


			</tr>
		</c:forEach>
	</table>

</body>
</html>