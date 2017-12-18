<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="offer/company/edit.do" modelAttribute="offerForm">

	<form:hidden path="createMoment"/>
	<form:hidden path="offerId"/>

	<acme:textbox code="offer.title" path="title" mandatory="true"/>
	<br/>
	
	<acme:textarea code="offer.description" path="description" mandatory="true"/>
	<br/>
	
	<acme:textbox code="offer.deadline" path="deadline" mandatory="true" placeholder="offer.deadline.pattern"/>
	<br/>
	
	<acme:textbox code="offer.salary.min" path="minSalary" mandatory="true"/>
	<br/>
	
	<acme:textbox code="offer.salary.max" path="maxSalary" mandatory="true"/>
	<br/>
	
	<acme:textbox code="offer.salary.currency" path="currency" mandatory="true"/>
	<br/>
	
	<spring:message code="offer.draft" />(*):
	<form:select path="draft">
		<spring:message code="draft.yes" var="draftYesHeader"/><form:option value="true" label="${draftYesHeader}"/>	
		<spring:message code="draft.no" var="draftNoHeader"/><form:option value="false" label="${draftNoHeader}" />				
	</form:select>
	<br/>
	<br/>
	<br/>
	
	<acme:submit id="submitButton" name="save" code="offer.submit"/>
	<acme:cancel code="offer.cancel" url="offer/company/list.do" />

</form:form>