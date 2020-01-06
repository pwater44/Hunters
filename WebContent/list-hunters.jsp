<%@ page import="java.util.*, com.simtekgamedevelopment.jdbc.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<!DOCTYPE html>
<html>
<head>
<title>Bigfoot Hunter App</title>
</head>

<%
	List<Hunter> hlist = (List<Hunter>) request.getAttribute("HUNTER_LIST");
%>

<body>
	<div align="center" id="buttonwrapper">
	<h2>Add Hunter</h2>
	<input type="button" value="Register"
					onClick="window.location.href='add-hunter-form.jsp'; return false;"
					class="add-hunter-button"
				/>
	</div>

	<div id="wrapper">
		<div id="header">
			<h2 align="center">Bigfoot Hunters</h2>
		</div>
		<div id="container">
			<div id="content">
				<table border="2" align="center">
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
					</tr>
					<%
						for (Hunter h : hlist) {
					%>
					<tr>
						<td><%=h.getFname()%></td>
						<td><%=h.getLname()%></td>
						<td><%=h.getEmail()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
</body>
</html>