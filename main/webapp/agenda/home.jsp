<%@ include file="../base_begin.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@ include file="sidebar.jsp"%>
		<c:choose>
			<c:when test="${ path.equals('/agenda.path') }">
				<%@ include file="body.jsp"%>
			</c:when>
			<c:when test="${ path.equals('/event.path') }">
				<%@ include file="event.jsp"%>
			</c:when>
			<c:when test="${ path.equals('/category.path') }">
				<%@ include file="category.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="body.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%@ include file="../base_end.jsp"%>