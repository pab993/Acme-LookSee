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

<form:form action="educationRecord/edit.do" modelAttribute="educationRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="curriculum" />
	
	<acme:textbox code="educationRecord.diplomaTitle" path="diplomaTitle" mandatory="true" />
	<br />

	<acme:textbox code="educationRecord.attachment" path="attachment" mandatory="false" />
	<br />

	<acme:textarea code="educationRecord.comments" path="comments" mandatory="false" />
	<br />
	
	<acme:textbox code="educationRecord.startPeriod" path="period.startPeriod" mandatory="true" />
	<br />
	
	<acme:textbox code="educationRecord.endPeriod" path="period.endPeriod" mandatory="true" />
	<br />

	<acme:submit id="submitButton" name="save" code="educationRecord.submit" />
	<input type="button" name="cancel" value="<spring:message code="educationRecord.cancel" />"
		onclick="javascript: window.location.replace('curriculum/display.do?curriculumId=${educationRecord.curriculum.id}')" />

</form:form>