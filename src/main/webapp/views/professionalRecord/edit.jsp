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

<form:form action="professionalRecord/edit.do" modelAttribute="professionalRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="curriculum" />
	
	<acme:textbox code="professionalRecord.companyName" path="companyName" mandatory="true" />
	<br />
	
	<acme:textbox code="professionalRecord.role" path="role" mandatory="true" />
	<br />

	<acme:textbox code="professionalRecord.attachment" path="attachment" mandatory="false" />
	<br />

	<acme:textarea code="professionalRecord.comments" path="comments" mandatory="false" />
	<br />
	
	<acme:textbox code="professionalRecord.startPeriod" path="period.startPeriod" mandatory="true" />
	<br />
	
	<acme:textbox code="professionalRecord.endPeriod" path="period.endPeriod" mandatory="true" />
	<br />

	<acme:submit id="submitButton" name="save" code="professionalRecord.submit" />
	<input type="button" name="cancel" value="<spring:message code="professionalRecord.cancel" />"
		onclick="javascript: window.location.replace('curriculum/display.do?curriculumId=${professionalRecord.curriculum.id}')" />

</form:form>