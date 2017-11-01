<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="struts-html"   prefix = "html"  %>
<%@ taglib uri="struts-bean"   prefix = "bean"  %>
<%@ taglib uri="struts-logic"  prefix = "logic" %>
<%@ taglib uri="struts-nested" prefix = "nested"%>
<meta http-equiv="Content-style-Type" content="text/css" />

<link rel="stylesheet" type="text/css" 
	  href="${pageContext.request.contextPath}/css/ficheInscription.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ficheInscription.js"> 
</script>
	    	  
<title> fiche Réglement adhérent </title>
</head>
<body>

<span class="ModeAccess">
<%-- 		 <bean:message key="${ficheInscription.modeAcces}"/> --%>
	</span>	  
	<h1> Fiche de réglement de l'adhérent </h1>
	 <html:errors bundle="erreur"  />
	<html:form styleId="form" action="/ficheReglementsJSP.do">

	<fieldset>
	<legend> <bean:message key="cadreSelAdhrent"/>  </legend>
<!-- 	Sélection d'un adhérent -->

	<!-- 	----Nom   ------ -->	
	<span class="labelData150"> <bean:message key="nom" /> </span>
	<span class="valueData"> 
	 	<html:text name="ficheReglementsForm" property="nom" disabled="true" /> 	
	</span>
	<!-- 	----Prénom ----- -->
	<span class="labelData150"> <bean:message key="prenom"/> </span>
	<span class="valueData"> 
		<html:text name="ficheReglementsForm" property="prenom"  disabled="true"/>
		<logic:notEqual name="ficheReglementsForm" property="numeroAdherent" value="0">
			n° <bean:write name="ficheReglementsForm" property="numeroAdherent"/>
		</logic:notEqual>
	</span>	

		<html:submit styleClass="bouton" property="action"> 
			<bean:message key="selAdhrent"/>
		</html:submit>
<!-- 		---- Age --------- -->
	<br>
	<span class="labelData150"> <bean:message key ="age"/> </span>
	<span class="valueData">
		<html:text name="ficheReglementsForm" property="ageEleve" disabled="true"/>
	</span>
<!-- 	------- Atelier ---------- -->
	<br>
	<span class="labelData150"> <bean:message key="atelier2"/> </span>
	<span class="valueData">
		<html:text name="ficheReglementsForm" property="atelierEleve" disabled="true"/>
	</span>		

	</fieldset>
	<br>
	<fieldset>
		<legend> <bean:message key="legendeReg"/> </legend>
		<br>
		<span class="labelData150"> <bean:message key="modReg"/> </span>
		<span class="valueData"> 
			<html:text name="ficheReglementsForm" property="modeReglement" 
						maxlength="2" size="2"/>  
		</span>
		<br> <br>
		<span class="labelData150"> <bean:message key="desc"/> </span>
		<span class="valueData"> 
			<html:text name="ficheReglementsForm" property="description"/>
		</span>
		
	</fieldset>
	<br>
		<html:submit styleClass="bouton" property="action"> 
			<bean:message key="initialiser"/>
		</html:submit>
	</html:form>
	
<!-- 	<div id= "popupListAdherent"> -->
<%-- 		<%@ include file="./SelectionAdherents.jsp" %> --%>
<!-- 	</div>	  -->

</body>
</html>