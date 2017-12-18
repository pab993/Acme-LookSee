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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<security:authorize access="hasRole('ADMIN')">	

	<div>
		<h2>
			<spring:message code="dashboard.avg.curriculum.per.candidate" />
		</h2>
		<h4>
			<spring:message code="dashboard.avg.applications.candidate"/>
			<jstl:out value="${avgNumberCurriculumPerCandidate}" />
		</h4>
	</div>
	
	<br>

	<div>
		<h2>
			<spring:message code="dashboard.avg.offer.per.company" />
		</h2>
		<h4>
			<spring:message code="dashboard.avg.applications.candidate"/>
			<jstl:out value="${avgNumberOffersPerCompany}" />
		</h4>
	</div>

	<br>

	<div>
		<h2>
			<spring:message code="dashboard.minAvgMax.applications.candidate" />
		</h2>
		<h4>
			<spring:message code="dashboard.min.applications.candidate"/><jstl:out value="${minNumberOfApplicationsPerCandidate}" />
			<br>
			<spring:message code="dashboard.max.applications.candidate"/><jstl:out value="${maxNumberOfApplicationsPerCandidate}" />
			<br>
			<spring:message code="dashboard.avg.applications.candidate"/><jstl:out value="${avgNumberOfApplicationsPerCandidate}" />
		</h4>
	</div>
	
	<br>
	
	<div>
		<h2>
			<spring:message code="dashboard.minAvgMax.applications.offer" />
		</h2>
		<h4>
			<spring:message code="dashboard.min.applications.offer"/><jstl:out value="${minNumberOfApplicationsPerOffer}" />
			<br>
			<spring:message code="dashboard.max.applications.offer"/><jstl:out value="${maxNumberOfApplicationsPerOffer}" />
			<br>
			<spring:message code="dashboard.avg.applications.offer"/><jstl:out value="${avgNumberOfApplicationsPerOffer}" />
		</h4>
	</div>
	
	<br>
	
	<div>
		<h2>
			<spring:message code="dashboard.minAvgMax.applications.pending" />
		</h2>
		<h4>
			<spring:message code="dashboard.min.applications.pending"/><jstl:out value="${minNumberOfPendingApplicationsPerCompany}" />
			<br>
			<spring:message code="dashboard.max.applications.pending"/><jstl:out value="${maxNumberOfPendingApplicationsPerCompany}" />
			<br>
			<spring:message code="dashboard.avg.applications.pending"/><jstl:out value="${avgNumberOfPendingApplicationsPerCompany}" />
		</h4>
	</div>
	
	<br>
	
	<div>
		<h2>
			<spring:message code="dashboard.minAvgMax.applications.accepted" />
		</h2>
		<h4>
			<spring:message code="dashboard.min.applications.acccepted"/><jstl:out value="${minNumberOfAcceptedApplicationsPerCompany}" />
			<br>
			<spring:message code="dashboard.max.applications.accepted"/><jstl:out value="${maxNumberOfAcceptedApplicationsPerCompany}" />
			<br>
			<spring:message code="dashboard.avg.applications.accepted"/><jstl:out value="${avgNumberOfAcceptedApplicationsPerCompany}" />
		</h4>
	</div>
	
	<br>
	
	<div>
		<h2>
			<spring:message code="dashboard.minAvgMax.applications.rejected" />
		</h2>
		<h4>
			<spring:message code="dashboard.min.applications.rejected"/><jstl:out value="${minNumberOfRejectedApplicationsPerCompany}" />
			<br>
			<spring:message code="dashboard.max.applications.rejected"/><jstl:out value="${maxNumberOfRejectedApplicationsPerCompany}" />
			<br>
			<spring:message code="dashboard.avg.applications.rejected"/><jstl:out value="${avgNumberOfRejectedApplicationsPerCompany}" />
		</h4>
	</div>
	
	<br>

	
	<h2>
		<spring:message code="dashboard.candidates.number.curricula" />
	</h2>
	<jstl:forEach items="${findCandidatesByNumberOfCurricula}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>
	
	<br>
	
	
	<h2>
		<spring:message code="dashboard.companies.number.offer" />
	</h2>
	<jstl:forEach items="${findCompaniesByNumberOfOffers}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>
	
	<br>

	
	<h2>
		<spring:message code="dashboard.candidates.more.curricula" />
	</h2>
	<jstl:forEach items="${findCandidatesWithMoreCurricula}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>
	
	<br>
	
	<h2>
		<spring:message code="dashboard.companies.more.offers" />
	</h2>
	<jstl:forEach items="${findCompaniesWithMoreOffers}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>


</security:authorize>