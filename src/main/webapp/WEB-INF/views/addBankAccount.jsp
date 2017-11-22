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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Please add you credit card in you account!</title>
</head>

<body>
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
<a>take care! when you first bind your credit card, you credit card will be used when you user account total is not enough to use</a>
<form:form action="${contextPath}/bankaccount/addBankAccount" commandName="bankAccount"
		method="post">
	<table>
	
	<tr>
		<td>Credit Card Vaild Date</td>
		<td><form:input path="valiDate" size="30" required="required" />
					<font color="red"><form:errors path="valiDate" /></font></td>
	</tr>
	<tr>
		<td>FirstName</td>
		<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
	</tr>
	<tr>
		<td>LastName</td>
		<td><form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
	</tr>
	<tr>
		<td>CSV</td>
		<td><form:input path="csv" size="30" required="required" />
					<font color="red"><form:errors path="csv" /></font></td>
	</tr>
	<tr>
				<td colspan="2"><input type="submit" value="add bankAccount" /></td>
	</tr>
	</table>
		
</form:form>

</body>
</html>