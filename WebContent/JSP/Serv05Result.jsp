<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>

<c:if test="${empty resTitle}">
	<c:redirect url="Serv05Jstl.jsp" />
</c:if>
<c:if test="${not empty resTitle}">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${fn:escapeXml(resTitle)}</title>
	</head>
	<body>
		<font size="4">追加処理が正しく実行されました。</font><br/><br/>
		<a href="<%=request.getContextPath()%>/JSP/Serv05Jstl.jsp">新規追加</a>
		<%session.invalidate();%>
	</body>
</html>
</c:if>