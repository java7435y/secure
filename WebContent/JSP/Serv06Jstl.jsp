<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>

<html>
	<head>
		<title>商品削除(Serv06)</title>
	</head>
	<body>
	<h3>商品マスタテーブルのレコードを削除します(Serv06)</h3>
	<font color="red">※</font>は必須項目です。<br>
	<!--START Form-->
	<form action ="<%=request.getContextPath()%>/Serv06" method="post">
		<!--START Table-->
		<table border = "0">
			<tr>
				<td align="right">商品ID：</td>
				<td><input type="text" size="10" maxlength="8" name="id"
							value="${fn:escapeXml(msgId)}">
				<font color="red">※${fn:escapeXml(msgId_err)}</font></td>
			</tr>

		</table>
		<!--END Table-->
		<br>

		<input type="submit" value="削除" >
		<input type="reset" value="取消" >
		</form>

		<!--END Form-->
		<font color="red">${fn:escapeXml(msg)}</font>
		<br><br>
		<a href="../HTML/menu.html">戻る</a>
		<% session.invalidate(); %>
	 	</body>
</html>