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

<form:form action="curriculum/search.do">

	<input type="text" name="keyword" />
	<input type="submit" name="search"
		value="<spring:message code = "curriculum.search" />" />

</form:form>

<jstl:if test="${!firstTime}">
		
		<display:table name = "curriculums" id = "row" requestURI="curriculum/searchForm.do" pagesize = "10" class = "displaytag" >

			<spring:message code = "curriculum.name" var = "nameHeader" />
			<display:column property = "name" title = "${nameHeader}" />
			
			<spring:message code = "curriculum.picture" var = "pictureHeader" />
			<display:column property = "picture" title = "${pictureHeader}" />
		
			<spring:message code = "curriculum.email" var="emailHeader" />
			<display:column property="email" title="${emailHeader}" />
			
			<spring:message code = "curriculum.phoneNumber" var = "phoneNumberHeader" />
			<display:column property = "phoneNumber" title = "${phoneNumberHeader}" />

			<spring:message code = "curriculum.linkToLinkedIn" var = "linkToLinkedInHeader" />
			<display:column property = "linkToLinkedIn" title = "${linkToLinkedInHeader}" />
			
			<display:column>
					<a href="curriculum/display.do?curriculumId=${row.id}">
						<spring:message code="curriculum.display" />
					</a>
			</display:column>

	</display:table>
</jstl:if>