<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/webjars/jquery/2.1.4/jquery.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/webjarslocator/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }${urls.getForLookupPath('/js/common.js') }"></script>
<html lang="en">
<script type="text/javascript">
    $(function () {
      // alert("ok");
    });
</script>
<body>
<c:url value="/resources/text.txt" var="url"/>
<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
Spring URL: ${springUrl} at ${time}
<br>
JSTL URL: ${url}
<br>
Message: ${message}

<div>
    <img alt="读取默认配置中的图片" src="${pageContext.request.contextPath }/pic.jpg">
    <br/>
</div>
</body>

</html>