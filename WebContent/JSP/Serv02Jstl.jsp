<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<c:if test="${empty title}">
	<c:redirect url="/Serv02" />
</c:if>
<c:if test="${not empty title}">
<html>
	<head>
	<title>${fn:escapeXml(title)}</title></head>
	<body>
	<c:if test="${empty db}">
		<h2>データがありませんでした</h2>
	</c:if>


	<div align="center">
		<c:if test="${not empty db}">
			<h1>${fn:escapeXml(midashi)}</h1>
			<table border="1">
			<tr>
				<c:forEach var="head" items="${itemHeader}">
						<th>${fn:escapeXml(head)}</th>
				</c:forEach>
			</tr>
				<c:forEach var="sData" items="${db}">
					<tr>
						<td align="right">${fn:escapeXml(sData.goodsId)}</td>
						<td>${fn:escapeXml(sData.goodsName)}</td>
						<td>${fn:escapeXml(sData.goodsGroupName)}</td>
						<c:if test="${empty sData.cost}">
							<td>&nbsp;</td>
						</c:if>
						<c:if test="${not empty sData.cost}">
							<td align="right">
							<fmt:formatNumber value="${fn:escapeXml(sData.cost)}"
								type="CURRENCY"	maxFractionDigits="0"
								currencySymbol="&yen;" />
							</td>
						</c:if>

						<c:if test="${empty sData.wholesale}">
							<td>&nbsp;</td>
						</c:if>
						<c:if test="${not empty sData.wholesale}">
							<td align="right">
							<fmt:formatNumber value="${fn:escapeXml(sData.wholesale)}"
								type="CURRENCY"	maxFractionDigits="0"
								currencySymbol="&yen;" />
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br>
	</div>
	</body>
</html>
</c:if>