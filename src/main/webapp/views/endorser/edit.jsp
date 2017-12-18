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

<form:form action="endorser/edit.do" modelAttribute="endorserForm">

	<form:hidden path="endorserId" />
	<form:hidden path="endorserVersion"/>
	<form:hidden path="curriculum"/>
	
	<acme:textbox code="endorser.name" path="name" mandatory="true" />
	<br />

	<acme:textbox code="endorser.email" path="email" mandatory="true" />
	<br />

	<acme:textbox code="endorser.phoneNumber" path="phoneNumber" mandatory="true" />
	<br />
	
	<acme:textbox code="endorser.linkToLinkedIn" path="linkToLinkedIn" mandatory="true" />
	<br />
	
	<acme:textarea code="endorser.comments" path="comments"/>
	<br />

	<acme:submit id="submitButton" name="save" code="endorser.submit" />
	<input type="button" name="cancel" value="<spring:message code="endorser.cancel" />"
		onclick="javascript: window.location.replace('curriculum/display.do?curriculumId=${endorserForm.curriculum.id}')" />
	<%-- <acme:cancel code="endorser.cancel" url="curriculum/display.do?curriculumId=${curriculum.id }"/> --%>

</form:form>