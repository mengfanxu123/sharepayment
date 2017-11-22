<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	function accept(contextpath,friendMsgID) {
		 alert("account number is: " + friendMsgID);
		alert(contextpath);
		var id = friendMsgID;
		var path = contextpath;
		$.ajax({
			url : encodeURI(encodeURI(path + '/user/accpet?friendMsgID='
					+ id)),
			type : 'get',
			cache : false,
			success : function(data) {
				if (data == "success") {
					alert("accept successfull!");
					window.location.reload();
				} else if(data == "adderror") {
					alert("addr error!");
				}else{
					alert("sever error!");
				}
			},
			error : function() {
				alert("Error in delete!");
			}
		});
	}
</script>

<script type="text/javascript">
	function reject(contextpath,friendMsgID) {
		 alert("account number is: " + friendMsgID);
		alert(contextpath);
		var id = friendMsgID;
		var path = contextpath;
		$.ajax({
			url : encodeURI(encodeURI(path + '/user/reject?friendMsgID='
					+ id)),
			type : 'get',
			cache : false,
			success : function(data) {
				if (data == "success") {
					alert("reject successfull!");
					window.location.reload();
				
				}else{
					alert("sever error!");
				}
			},
			error : function() {
				alert("Error in delete!");
			}
		});
	}
</script>
<title>accept you friends request</title>
</head>
<body>
<h1>accept or reject you friend request </h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<table border="1" cellpadding="5" cellspacing="5">
		<c:forEach var="list" items="${requestList}">
			<tr>
				<td>who want add you friends: ${list.sender}</td>
				<td>Date: ${list.date}</td>
				 <td><a
					href="javascript:onclick=accept('${contextPath}', '${list.friendMsgID}')">
						accept</a></td>
				<td><a
					href="javascript:onclick=reject('${contextPath}','${list.friendMsgID}')">
						reject</a> 
			</tr>

		</c:forEach>
	</table>
</body>
</html>