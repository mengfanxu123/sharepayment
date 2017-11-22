<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add you BanckAccount</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}">Go Back</a><br/>
<h2>please add you credit card!</h2>
<form:form action="${contextPath}/bankAccount/addAccount" commandName="bankAccount"
method="post">
<table>		<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>
			<tr>
				<td>Account Number:</td>
				<td><form:input path="accountNum" size="50" required="required" />
					<font color="red"><form:errors path="accountNum" /></font></td>
			</tr>

			<tr>
				<td>first Name:</td>
				<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>


			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>

			<tr>
				<td>CSV:</td>
				<td><form:input path="csv" size="30"
						required="required" /> <font color="red"><form:errors
							path="csv" /></font></td>
			</tr>

			<tr>
				<td>ValiDate:</td>
				<td><form:input path="validate" size="30"
						 required="required" /> <font color="red"><form:errors
							path="validate" /></font></td>
			</tr>
			
			

			<tr>
				<td colspan="2"><input type="submit" value="transfer" /></td>
			</tr>
		</table>
</form:form>
</body>
</html>