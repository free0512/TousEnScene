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
<%-- 	 <html:errors bundle="erreur"/> --%>
	<html:form styleId="form" action="/listeReglementsJSP.do">

	<fieldset>
	<legend> <bean:message key="cadreSelAdhrent"/>  </legend>
<!-- 	Sélection d'un adhérent -->

	<!-- 	----Nom   ------ -->	
	<span class="labelData150"> <bean:message key="nom" /> </span>
	<span class="valueData"> 
	 	<html:text name="listeReglementsForm" property="nom" disabled="true" /> 	
	</span>
	<!-- 	----Prénom ----- -->
	<span class="labelData150"> <bean:message key="prenom"/> </span>
	<span class="valueData"> 
		<html:text name="listeReglementsForm" property="prenom"  disabled="true"/>
		<logic:notEqual name="listeReglementsForm" property="numeroAdherent" value="0">
			n° <bean:write name="listeReglementsForm" property="numeroAdherent"/>
		</logic:notEqual>
	</span>	

		<html:submit styleClass="bouton" property="action"> 
			<bean:message key="selAdhrent"/>
		</html:submit>
<!-- 		---- Age --------- -->
	<br>
	<span class="labelData150"> <bean:message key ="age"/> </span>
	<span class="valueData">
		<html:text name="listeReglementsForm" property="ageEleve" disabled="true"/>
	</span>
<!-- 	------- Atelier ---------- -->
	<br>
	<span class="labelData150"> <bean:message key="atelier2"/> </span>
	<span class="valueData">
		<html:text name="listeReglementsForm" property="atelierEleveDesc" 
				   maxlength="50" size="50" disabled="true"/>
	</span>
<!-- 	------- Mode réglement ---------- --> 
	<br>
	<span class="labelData150"> <bean:message key="modReg"/> </span>
	<span class="valueData">
		<html:text name="listeReglementsForm" property="reglementEleveDesc" 
				   maxlength="50" size="50" disabled="true"/>
	</span>			

	</fieldset>
 
 	<logic:notEmpty name="listeReglementsForm" property="listeReglements" >
		<h3> <bean:message key="legendeReg"/> </h3>
		
		<table>
		<thead>
			<tr class="theadTabReg">
				<th class="theadTabReg"> <bean:message key="action"/> </th>
				<th class="theadTabReg"> <bean:message key="modReg"/> </th>
				<th class="theadTabReg"> <bean:message key="desc"/> </th>
				<th class="theadTabReg"> <bean:message key="dateReg"/> </th>
				<th class="theadTabReg"> <bean:message key="montantReg"/> </th>
			</tr>
		</thead>
		<tbody>
		<logic:iterate id="id" name="listeReglementsForm" property="listeReglements" indexId="i">
			<tr class="ligneTab">
			<td class="colonneTab">
				<html:image styleClass="boutonImage" page="/images/modif.png" 
							title="Modification" />
				<html:image styleClass="boutonImage" page="/images/suppr.jpg" 
							title="suppression"/>
			</td>	
			<td class="colonneTab">
				<strong><bean:write name="id" property="modeReglement"/> </strong>
			</td>
			<td class="colonneTab">
				<strong><bean:write name="id" property="description"/> </strong>
			</td>
			<td class="colonneTab">
				<strong><bean:write name="id" property="dateReglement"/> </strong>
			</td>
			<td class="colonneTab">
				<strong><bean:write name="id" property="montantReglement"/> </strong>
			</td>
			</tr>
		</logic:iterate>
		</tbody>	
				
		</table>		
		
		<!-- 	Boutons de déplacement et de positionnement -->
	
		<html:select styleId="choixPage" 
				 name="listeReglementsForm" property="choixPage"> 
			<logic:present name="listeReglementsForm" property="listePages">		
			<html:optionsCollection name="listeReglementsForm" property="listePages"
 									label="label" value="value"/>   
			</logic:present>
		</html:select>
	
	<logic:equal   name="listeReglementsForm" property="suivant" value="true">
		<html:submit  styleId="BtnSuivant" styleClass="bouton" property="action">
			<bean:message key="suivant"/>
		</html:submit>
	</logic:equal>
	
	<logic:equal name="listeReglementsForm" property="precedent" value="true">
		<html:submit styleId="BtnPrecedent" styleClass="bouton" property="action">
			<bean:message key="precedent"/>
		</html:submit>
	</logic:equal>
	
	<logic:present name="listeReglementsForm" property="listeReglement">
		<logic:empty name="listeReglementsForm" property="listeReglement">
			<bean:message key="listeVide"/>
		</logic:empty> 
	</logic:present>
	
	<br>
	</logic:notEmpty>	
	
	<html:hidden property="action" styleId="action" value=""/>	
	</html:form>
</body>
</html>