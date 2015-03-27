<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>

<c:if test="${empty title}">
	<c:redirect url="/HTML/Serv04.html" />
</c:if>
<c:if test="${not empty title}">
<html>
	<head>
	<title>${fn:escapeXml(title)}</title></head>
	<body>
	<div align="center">
		<h1>${fn:escapeXml(midashi)}</h1>
		<c:if test="${empty db}">
		    入力された商品名のデータはありません<br>
		</c:if>
		<c:if test="${not empty db}">
			<table border="1">
			<tr>
				<c:forEach var="head" items="${itemHeader}">
						<th>${fn:escapeXml(head)}</th>
				</c:forEach>
			</tr>
				<c:forEach var="sData" items="${db}">
					<tr>
						<td align="right">
						<a href="<%=request.getContextPath()%>/Serv04_2?id=${fn:escapeXml(sData.goodsId)}">
						${fn:escapeXml(sData.goodsId)}</a></td>
						<td>${fn:escapeXml(sData.goodsName)}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br/>
		<a href="<%=request.getContextPath()%>/HTML/Serv04.html">条件入力画面に戻る</a>
	</div>
  </body>
</html>
</c:if>
