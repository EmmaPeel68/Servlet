<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Idiomas</title>
</head>
	<body>
		<form action="welcome" method="post" name="form1">
			<h1 align="center">Servlet Idiomas</h1>
			<table align="center" border="1" bgcolor="red">
				<tr>
					<td>Pais</td>
					<td><input type="text" name="country" size="8"></td>
				</tr>
				<tr>
					<td>Idioma</td>
					<td>
					
						<select name="language1">
		  						<option value="Spanish">Spanish</option>
		 						<option value="Irish">Irish</option>
						</select>
						
					</td>
				</tr>
				<tr>
					<td>Idioma</td>
					<td>
					
					<input type="text" name="language2" size="25">
						
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>