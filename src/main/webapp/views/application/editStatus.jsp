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

<form:form action="application/editStatus.do"
	modelAttribute="application">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<div>
		<form:select path="status" itemLabel="status" code="choose">
			<option value="ACCEPTED">
				<spring:message code="apply.status.accepted" />
			</option>
			<option value="PENDING">
				<spring:message code="apply.status.pending" />
			</option>
			<option value="REJECTED">
				<spring:message code="apply.status.rejected" />
			</option>
		</form:select>
	</div>
	<br />

	<acme:submit id="submitButton" name="save" code="application.submit" />
	<acme:cancel code="application.cancel" url="application/listByOffer.do?offerId=${application.offer.id}" />

</form:form>