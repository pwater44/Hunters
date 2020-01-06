<!DOCTYPE html>
<html>
<head>
<title>Update Hunter</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Bigfoot Hunters of NoVa - Update</h2>
		</div>
	</div>
	<div id="container">
		<h3>Add Hunter</h3>
		<form action="HunterControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>