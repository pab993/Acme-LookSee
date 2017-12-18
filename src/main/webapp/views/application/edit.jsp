<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="application/edit.do" modelAttribute="application">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="createMoment" />
	<form:hidden path="status" />
	
	<form:hidden path="candidate" />
	<form:hidden path="offer" />	

	<div>
		<acme:select items="${curriculums}" itemLabel="ticker" code="choose"
			path="curriculum" />
	</div>
	<br />

	<acme:submit id="submitButton" name="save" code="application.submit" />
	<acme:cancel code="application.cancel" url="offer/list.do" />

</form:form>