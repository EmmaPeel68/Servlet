<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Idiomas</title>
</head>
	<body>
		<form action="hello" method="post" name="form1">
			<h1 align="center">Servlet Idiomas</h1>
			<table align="center" border="1" bgcolor="red">
				<tr>
					<td>Pais</td>
					<td><input type="text" name="pais" size="8"></td>
				</tr>
				<tr>
					<td>Idioma</td>
					<td>
					
						<select name="idioma">
		  						<option value="español">Español</option>
		 						<option value="ingles">Inglés</option>
						</select>
						
					</td>
				</tr>
				<tr>
					<td>Idioma</td>
					<td>
					
					<input type="text" name="idioma" size="10">
						
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