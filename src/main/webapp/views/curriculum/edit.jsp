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

<form:form action="curriculum/edit.do" modelAttribute="curriculum">

	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<form:hidden path="ticker" />
	<form:hidden path="copy" />
	<form:hidden path="candidate" />
	<form:hidden path="professionalRecords" />
	<form:hidden path="miscellaneouss" />
	<form:hidden path="educationRecords" />
	<form:hidden path="endorsers" />
	
	<div>
		<fieldset>
			<b> <spring:message code="edit.information" /></b>
		</fieldset>
	</div>
	<br/>


	<acme:textbox code="curriculum.name" path="name" mandatory="true" />
	<br />

	<acme:textbox code="curriculum.picture" path="picture" mandatory="true" />
	<br />

	<acme:textbox code="curriculum.email" path="email" mandatory="true" />
	<br />

	<acme:textbox code="curriculum.phoneNumber" path="phoneNumber"
		mandatory="true" />
	<br />

	<acme:textbox code="curriculum.linkToLinkedIn" path="linkToLinkedIn"
		mandatory="true" />
	<br />

	<acme:submit id="submitButton" name="save" code="curriculum.submit" />
	<acme:cancel code="curriculum.cancel" url="curriculum/list.do" />

</form:form>