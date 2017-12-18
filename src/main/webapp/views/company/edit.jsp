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

<form:form action="company/register.do" modelAttribute="companyForm" onsubmit="return validateForm()">
	
	<fieldset > 
	
	<legend><b> <spring:message code="company.accountData" /> </b></legend>
	
		<acme:textbox code="company.username" path="username" mandatory="true"/>
		<br/>
	
		<acme:password code="company.password" path="password" mandatory="true"/>
		<br/>
	
		<acme:password code="company.repeatPassword" path="repeatPassword" mandatory="true"/>
	
	</fieldset>
	
	
	<fieldset > 
	
		<legend><b> <spring:message code="company.personalData" /></b> </legend>
	
	
		<acme:textbox code="company.name" path="name" mandatory="true"/>
		<br />
			
		<acme:textbox code="company.surname" path="surname" mandatory="true"/>
		<br />
			
<%-- 		<acme:textbox code="company.phone" path="phone"/>
		<br /> --%>
		
		<form:label path="phone">
			<spring:message code="company.phone" />
		</form:label>
		<form:input id="phoneId" path="phone" />
		<form:errors cssClass="error" path="phone" />
		<br />
		<br />
			
		<acme:textbox code="company.email" path="email" mandatory="true"/>
		<br />
		
		<acme:textbox code="company.vat" path="vat" mandatory="true"/>
		<br />
		
		<acme:textbox code="company.postalAddress" path="postalAddress"/>
		<br/>
	
	</fieldset>
	
	
	<div>
	<form:checkbox id="myCheck" onclick="comprobar();" path="check"/>
		<form:label path="check">
			<spring:message code="company.accept" />
			<a href="termAndCondition/termAndCondition.do"><spring:message code="company.lopd"/></a>
		</form:label>
	</div>
	

	<acme:submit id="submitButton" name="save" code="company.submit"/>

</form:form>


<script type="text/javascript">

document.getElementById("submitButton").disabled = true;
document.getElementById("myCheck").checked = false;

function comprobar() {
	
	var x = document.getElementById("myCheck").checked;
	
	if(x == true){
		document.getElementById("submitButton").disabled = false;
	}
	else{
		document.getElementById("submitButton").disabled = true;
	}
}

function validateForm() {
	<spring:message code="phone.ask" var="ask"/>
    var x = document.getElementById("phoneId").value;
    var patt = new RegExp("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$");
    if(x != "" && !patt.test(x)){
        return confirm('<jstl:out value="${ask}"/>');
    } 

}


</script>