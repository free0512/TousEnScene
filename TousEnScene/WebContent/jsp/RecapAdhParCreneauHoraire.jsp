<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<%@ taglib uri="struts-html"   prefix = "html"  %>
<%@ taglib uri="struts-bean"   prefix = "bean"  %>
<%@ taglib uri="struts-logic"  prefix = "logic" %>
<%@ taglib uri="struts-nested" prefix = "nested"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-style-Type" content="text/css" />

<link rel="stylesheet" type="text/css" 
	  href="${pageContext.request.contextPath}/css/ficheInscription.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ficheInscription.js"> </script>
	    	  
<title>  </title>
</head>

<body>
	<h1> <bean:message key="recapAdhSeanceTitre"/> </h1>
		
	<html:form styleId="form" action="/recapAdhEntJSP.do">

	<html:errors bundle ="erreur"/>	

<!-- 	Synthése des adhérents par séance  -->
	<fieldset>
		<legend> <bean:message key="nbreAdhSeance"/> </legend>
		<div id="success" class="tableContainer">
		<table>
			<thead>
				<tr class="theadTab">
					<th class="theadTab"> <bean:message key="crenauHoraire"/> </th>
					<th class="theadTab"> <bean:message key="nbrAdherents"/> </th>		
				</tr>		
			</thead>
			<tbody>
				<logic:iterate 	id="id" name="recapAdhParCreneauForm" property="recapAdhList" 
								indexId="i">
					<tr class="ligneTab">
						<td class="colonneTab">
						<html:link action="/recapAdhSeances.do">
						 	<strong> <bean:write  name="id" property="libellCreneau"/> </strong>
						 </html:link>
						</td>
						<td class="colonneTab">
							<strong><bean:write name="id" property="nombreAdherents"/> </strong> 
						</td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
		</div>
	</fieldset>
	
	<!-- 	Liste des adhérents correspndants à la séance  -->
	<fieldset>
		<legend> <bean:message key="listeAdhDeLaSeance"/> </legend>
		<div id="success" class="tableContainer">
		<table>
			<thead>
				<tr class="theadTab">
					<th class="theadTab"> <bean:message key="sel"/> </th>
					<th class="theadTab"> <bean:message key="nom"/> </th>
					<th class="theadTab"> <bean:message key="prenom"/> </th>
					<th class="theadTab"> <bean:message key="DateNaissance"/> </th>
					<th class="theadTab"> <bean:message key="adresse"/> </th>
					<th class="theadTab"> <bean:message key="codePostal"/> </th>
					<th class="theadTab"> <bean:message key="ville"/> </th>
					<th class="theadTab"> <bean:message key="classe"/> </th>					
			</thead>
			<tbody>
				<logic:iterate id="id" name="recapAdhParCreneauForm" property="listeAdherents" indexId="i">
					<tr class="ligneTab">
						<td class="colonnePointer" title="Accéder au détail"> 
<%-- 							<html:radio name="listeAdherentsForm"  --%>
<%-- 								property="selRadio"  title="Accéder au détail" --%>
<%-- 								styleClass ="selRadioOpt"	value=""  --%>
<%-- 								onclick="selectionAdherent(${id.numeroInterne})" /> 	 		 --%>
						</td> 
						<td class="colonneTab">
<%-- 						    onclick="action('<c:set target="${listeAdherentsForm}"  --%>
<!-- 						  							 property="rang"  -->
<%-- 													 value="${i}" />'); " > --%>
							 	<strong><bean:write name="id" property="nomEleve"/> </strong>
							
						</td>
						<td class="colonneTab">
							<strong><bean:write name="id" property="prenomEleve"/> </strong>
						</td>
						<td class="colonneTab">
							<strong> <bean:write name="id" property="dateDeNaissanceEleve"/> </strong>
						</td>
						<td class="colonneTab">
							<strong> <bean:write name="id" property="adresse1"/> 
							 		<bean:write name="id" property="adresse2"/> 
							</strong>
						</td>
						<td class="colonneTab">
							<strong> <bean:write name="id" property="codePostal"/> </strong>
						</td>
						<td class="colonneTab">
							<strong> <bean:write name="id" property="ville"/> </strong>
						</td>
						<td class="colonneTab">
							<strong> <bean:write name="id" property="classeScolaire"/> </strong>
						</td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
		</div>
	</fieldset>
	
	<html:hidden property="action" styleId="action" value=""/>
	<html:hidden property="creneauSel" styleId="creneauSel"/>	
	</html:form>
</body>
</html>