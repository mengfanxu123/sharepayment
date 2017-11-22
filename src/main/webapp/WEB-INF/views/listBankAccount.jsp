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
<%-- <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" /> --%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	function deleteAccount(contextpath, accountNum) {
		/* alert("account number is: " + accountNum); */
		alert(contextpath);
		var account = accountNum;
		var path = contextpath;
		$.ajax({
			url : encodeURI(encodeURI(path + '/bankAccount/delete?accountNum='
					+ account)),
			type : 'get',
			cache : false,
			success : function(data) {
				if (data == "success") {
					alert("delete success!");
					window.location.reload();
				} else {
					alert("Server error!");
				}
			},
			error : function() {
				alert("Error in delete!");
			}
		});
	}
</script>
<script type="text/javascript">
	function bindCard(contextpath, accountNum) {
		/* alert("account number is: " + accountNum); */
		alert(contextpath);
		var account = accountNum;
		var path = contextpath;
		$.ajax({
			url : encodeURI(encodeURI(path + '/bankAccount/bind?accountNum='
					+ account)),
			type : 'get',
			cache : false,
			success : function(data) {
				if (data == "isbind") {
					alert("this card already bind");
					window.location.reload();
				} else if(data=="change") {
					
					alert("changed bind card!");
					
				}else{
					alert("server error!")
					
				}
			},
			error : function() {
				alert("Error in delete!");
			}
		});
	}
</script>
<title>ListYouBankAccount</title>
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
	
	<h1>you can manage you bankAccount and load money to you Vanmi
		Account</h1>
	<table border="1" cellpadding="5" cellspacing="5">
		<c:forEach var="BankAccountHistory" items="${bankAccountList}">
			<tr>
				<td>you Bank Account: ${BankAccountHistory.accountNum }</td>
				<td><a
					href="javascript:onclick=deleteAccount('${contextPath}', '${BankAccountHistory.accountNum }')">
						Delete</a></td>
				<td><a
					href="javascript:onclick=bindCard('${contextPath}','${BankAccountHistory.accountNum}')">
						Bind you card</a>
			</tr>

		</c:forEach>
	</table>
</body>
</html>