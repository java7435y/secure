<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>

<html>
	<head>
		<title>商品更新(Serv07)</title>
	</head>
	<body>
	<h3>商品マスタテーブルのレコードを更新します(Serv07)</h3>
	<font color="red">※</font>は必須項目です。<br>
	<!--START Form-->
	<form action ="<%=request.getContextPath()%>/Serv07" method="post">
		<!--START Table-->
		<table border = "0">
			<tr>
				<td align="right">商品ID：</td>
				<td><input type="text" size="10" maxlength="8" name="id"
							value="${fn:escapeXml(msgId)}">
				<font color="red">※${fn:escapeXml(msgId_err)}</font></td>
			</tr>
			<tr>
				<td align="right">商品名：</td>
				<td><input type="text" size="40" maxlength="30" name="name"
							 value="${fn:escapeXml(msgName)}">
				<!--<font color="red">※${fn:escapeXml(msgName_err)}</font>--></td>
			</tr>
			<tr>
				<td align="right">グループ名：</td>
				<td><input type="text" size="30" maxlength="20" name="group"
							value="${fn:escapeXml(msgGroup)}">
				<!--<font color="red">※${fn:escapeXml(msgGroup_err)}</font>--></td>
			</tr>
			<tr>
				<td align="right">仕入単価：</td>
				<td><input type="text" size="15" maxlength="10" name="cost"
							value="${fn:escapeXml(msgCost)}" />
				<font color="red">${fn:escapeXml(msgCost_err)}</font></td>
			</tr>
			<tr>
				<td align="right">販売単価：</td>
				<td><input type="text" size="15" maxlength="10" name="wholesale"
							value="${fn:escapeXml(msgWholesale)}" />
				<font color="red">${fn:escapeXml(msgWholesale_err)}</font></td>
			</tr>
		</table>
		<!--END Table-->
		<br>

		<input type="submit" value="更新" >
		<input type="reset" value="取消" >
		</form>

		<!--END Form-->
		<font color="red">${fn:escapeXml(msg)}</font>
		<br><br>
		<a href="../HTML/menu.html">戻る</a>
		<% session.invalidate(); %>
	 	</body>
</html>