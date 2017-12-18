<%--
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->

<security:authorize access="hasRole('CANDIDATE')">
	<fieldset>
		<legend>
			<b> <spring:message code="application.order" /></b>
		</legend>
		<a href="application/listOrderByCreateMomentAsc.do"> <spring:message
				code="application.orderCreateAsc" />
		</a> <br> <a href="application/listOrderByCreateMomentDesc.do"> <spring:message
				code="application.orderCreateDesc" />
		</a> <br> <a href="application/listOrderByStatus.do"> <spring:message
				code="application.orderStatus" />
		</a> <br> <a href="application/listOrderByDeadline.do"> <spring:message
				code="application.orderDeadline" />
		</a>

	</fieldset>
</security:authorize>
<display:table name="applications" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<jstl:choose>
		<jstl:when
			test="${row.status eq 'PENDING'}">
			<spring:message code="application.createMoment"
				var="createMomentHeader" />
			<display:column property="createMoment" title="${createMomentHeader}"
				style="background:orange" format="{0,date,dd/MM/yyyy HH:mm}"/>

			<spring:message code="application.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}"
				style="background:orange" />

			<spring:message code="application.offer" var="offerHeader" />
			<display:column property="offer.title" title="${offerHeader}"
				style="background:orange" />

			<security:authorize access="hasRole('COMPANY')">
				<jstl:if test="${row.offer.company.id == offer.company.id}">
					<jstl:choose>
						<jstl:when test="${row.status eq 'PENDING'}">
							<display:column style="background:orange">
								<a href="application/editStatus.do?applicationId=${row.id}">
									<spring:message code="application.edit" />
								</a>
							</display:column>
						</jstl:when>
						<jstl:otherwise>
							<display:column style="background:orange">
								<spring:message code="application.notEdit"></spring:message>
							</display:column>
						</jstl:otherwise>
					</jstl:choose>
				</jstl:if>
			</security:authorize>
						
 			<display:column>
				<a href="curriculum/displayCopy.do?curriculumId=${row.curriculum.id}">
					<spring:message code="curriculum.display" />
				</a>
			</display:column> 
			
		</jstl:when>

		<jstl:when test="${row.status eq 'REJECTED'}">
			<spring:message code="application.createMoment"
				var="createMomentHeader" />
			<display:column property="createMoment" title="${createMomentHeader}"
				style="background:red" format="{0,date,dd/MM/yyyy HH:mm}"/>

			<spring:message code="application.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}"
				style="background:red" />

			<spring:message code="application.offer" var="offerHeader" />
			<display:column property="offer.title" title="${offerHeader}"
				style="background:red" />

			<security:authorize access="hasRole('COMPANY')">
				<jstl:if test="${row.offer.company.id == offer.company.id}">
					<jstl:choose>
						<jstl:when test="${row.status eq 'PENDING'}">
							<display:column style="background:red">
								<a href="application/editStatus.do?applicationId=${row.id}">
									<spring:message code="application.edit" />
								</a>
							</display:column>
						</jstl:when>
						<jstl:otherwise>
							<display:column style="background:red">
								<spring:message code="application.notEdit"></spring:message>
							</display:column>
						</jstl:otherwise>
					</jstl:choose>
				</jstl:if>
			</security:authorize>
			
						
 			<display:column>
				<a href="curriculum/displayCopy.do?curriculumId=${row.curriculum.id}">
					<spring:message code="curriculum.display" />
				</a>
			</display:column> 

		</jstl:when>

		<jstl:when test="${row.status eq 'ACCEPTED'}">

			<spring:message code="application.createMoment"
				var="createMomentHeader" />
			<display:column property="createMoment" title="${createMomentHeader}"
				style="background:green" format="{0,date,dd/MM/yyyy HH:mm}"/>

			<spring:message code="application.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}"
				style="background:green" />

			<spring:message code="application.offer" var="offerHeader" />
			<display:column property="offer.title" title="${offerHeader}"
				style="background:green" />

			<security:authorize access="hasRole('COMPANY')">
				<jstl:if test="${row.offer.company.id == offer.company.id}">
					<jstl:choose>
						<jstl:when test="${row.status eq 'PENDING'}">
							<display:column style="background:green">
								<a href="application/editStatus.do?applicationId=${row.id}">
									<spring:message code="application.edit" />
								</a>
							</display:column>
						</jstl:when>
						<jstl:otherwise>
							<display:column style="background:green">
								<spring:message code="application.notEdit"></spring:message>
							</display:column>
						</jstl:otherwise>
					</jstl:choose>
				</jstl:if>
			</security:authorize>
			
 			<display:column>
				<a href="curriculum/displayCopy.do?curriculumId=${row.curriculum.id}">
					<spring:message code="curriculum.display" />
				</a>
			</display:column> 

		</jstl:when>

		<jstl:otherwise>
			<spring:message code="application.createMoment"
				var="createMomentHeader" />
			<display:column property="createMoment" title="${createMomentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

			<spring:message code="application.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}" />

			<spring:message code="application.offer" var="offerHeader" />
			<display:column property="offer.title" title="${offerHeader}" />

			<security:authorize access="hasRole('COMPANY')">
				<jstl:if test="${row.offer.company.id == offer.company.id}">
					<jstl:choose>
						<jstl:when test="${row.status eq 'PENDING'}">
							<display:column>
								<a href="application/editStatus.do?applicationId=${row.id}">
									<spring:message code="application.edit" />
								</a>
							</display:column>
						</jstl:when>
						<jstl:otherwise>
							<display:column>
								<spring:message code="application.notEdit"></spring:message>
							</display:column>
						</jstl:otherwise>
					</jstl:choose>
				</jstl:if>
			</security:authorize>
			
 			<display:column>
				<a href="curriculum/displayCopy.do?curriculumId=${row.curriculum.id}">
					<spring:message code="curriculum.display" />
				</a>
			</display:column> 
		</jstl:otherwise>
	</jstl:choose>
	
</display:table>

