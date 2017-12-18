<%--
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="offer/search.do" modelAttribute="searchTemplateForm">

	<form:label path="keyword">
			<spring:message code="offer.keyword" />:
	</form:label>
	<input type="text" name="keyword" />
	
	<form:label path="currency">
			<spring:message code="offer.currency" />:
	</form:label>
	<input type="text" name="currency" />
	
	<input type="submit" name="search"
		value="<spring:message code = "offer.search" />" />

</form:form>

		
		<display:table name = "offers" id = "row" requestURI="offer/searchForm.do" pagesize = "10" class = "displaytag" >

			<spring:message code = "offer.createMoment" var = "createMomentHeader" />
			<display:column property = "createMoment" title = "${createMomentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "offer.title" var = "titleHeader" />
			<display:column property = "title" title = "${titleHeader}" />
		
			<spring:message code = "offer.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" />
			
			<spring:message code = "offer.deadline" var = "deadlineHeader" />
			<display:column property = "deadline" title = "${deadlineHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "offer.minSalary" var = "minSalaryHeader" />
			<display:column property = "salary.minSalary" title = "${minSalaryHeader}" />

			<spring:message code = "offer.maxSalary" var = "maxSalaryHeader" />
			<display:column property = "salary.maxSalary" title = "${maxSalaryHeader}" />
			
			<spring:message code = "offer.currency" var = "currencyHeader" />
			<display:column property = "salary.currency" title = "${currencyHeader}" />
			
			<display:column>
				<a href="actor/seeProfileUnregistered.do?actorId=${row.company.id}"><spring:message code="offer.company.profile"/></a>
			</display:column>

	</display:table>
