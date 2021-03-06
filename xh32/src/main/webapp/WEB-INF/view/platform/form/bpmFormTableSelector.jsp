<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code="bpmFormTable.title"/></title>
<script type="text/javascript">
	function getTable(tableId,tableName){
		
		var obj={tableId:tableId,tableName:tableName};
		window.returnValue=obj;
		
		window.close();
	}
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	html,body{width:100%;height:100%;margin: 0 0 0 0;padding: 0 0 0 0 ;overflow: hidden;}
</style>
</head>
<body>
	<c:set var="url" value="dialog.ht"></c:set>
	<c:choose>
		<c:when test="${param.isExternal!=null && param.isExternal==1 }">
			<c:set var="url" >${url  }?isExternal=1</c:set>
		</c:when>
		<c:when test="${param.isExternal!=null && param.isExternal==0 }">
			<c:set var="url" >${url  }?isExternal=0</c:set>
		</c:when>
	</c:choose>
	
	<iframe src="${url }" width="100%" height="100%" frameborder="0"></iframe>
</body>
</html>


