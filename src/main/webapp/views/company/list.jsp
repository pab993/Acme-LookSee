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

<!-- Listing table -->
	
	<display:table name = "companies" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

			<spring:message code = "company.name" var = "nameHeader" />
			<display:column property = "name" title = "${nameHeader}" />
		
			<spring:message code = "company.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />
			
			<spring:message code = "company.email" var = "emailHeader" />
			<display:column property = "email" title = "${emailHeader}" />
			
			<spring:message code = "company.phone" var = "phoneHeader" />
			<display:column property = "phone" title = "${phoneHeader}" />

			<spring:message code = "company.postalAddress" var = "postalAddressHeader" />
			<display:column property = "postalAddress" title = "${postalAddressHeader}" />
			
			<display:column>
					<a href="offer/listByCompany.do?companyId=${row.id}">
						<spring:message code="company.listByCompany" />
					</a>
			</display:column>
			
			<security:authorize access="hasRole('ADMIN')">
				<display:column>
					<jstl:choose>
						<jstl:when test="${row.isBan == false }">
								<a href="company/administrator/ban.do?companyId=${row.id}"><spring:message code="company.ban"/></a>			
						</jstl:when>
	
						<jstl:otherwise>
							<a href="company/administrator/unBan.do?companyId=${row.id}"><spring:message code="company.unBan"/></a>
						</jstl:otherwise>
					</jstl:choose>
				</display:column>
			</security:authorize>
									
</display:table>

