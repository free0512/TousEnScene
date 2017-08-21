<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
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
	    	  
<title> List des adh�rents </title>
</head>

<body>
	<h1> Liste des adh�rents de la saison </h1>
		
	<html:form styleId="form" action="/listeAdherentJSP.do">
	
	<html:errors bundle="erreur"  />	
	
	<fieldset>
	<legend> <bean:message key="rechecheEleve"/>  </legend>
<!-- 	----Nom   ------ -->	
	<span class="labelData150"> <bean:message key="nom" />  </span> 
	<span class="valueData"> 
		<html:text name="listeAdherentsForm" property="choixNomEleve"/> 
	</span>
	<span class="labelDataLine"> <bean:message key="prenom"/>  </span>
	<span class="valueDataLine">
		<html:text name="listeAdherentsForm" property="choixPrenomEleve"/> 
	</span>
	</fieldset>
	
<!-- 	Les boutons de recherche -->
	<html:submit  styleId="btnvalider" styleClass="bouton" property="action" > 
		<bean:message key="valider"/> 
	</html:submit>
	<html:submit  styleClass="bouton" property="action" > 
		<bean:message key="initialiser"/> 
	</html:submit>
	<html:submit styleClass="bouton" property="action">
		<bean:message key="annuler"/> 
	</html:submit>
	<br>
	
<!-- 	Les enregistrements d�tail -->
	<fieldset>
		<legend> <bean:message key="listeadh"/> </legend>
		<div id="success" class="tableContainer">
		<table>
			<thead>
				<tr class="theadTab">
					<th class="theadTab"> <bean:message key="nom"/> </th>
					<th class="theadTab"> <bean:message key="prenom"/> </th>
					<th class="theadTab"> <bean:message key="DateNaissance"/> </th>
					<th class="theadTab"> <bean:message key="adresse"/> </th>
					<th class="theadTab"> <bean:message key="codePostal"/> </th>
					<th class="theadTab"> <bean:message key="ville"/> </th>
					<th class="theadTab"> <bean:message key="classe"/> </th>
					
			</thead>
			<tbody>
				<logic:iterate id="id" name="listeAdherentsForm" property="listeAdherents" indexId="i">
					<tr class="ligneTab">
						<td class="colonneTab" title="Acc�der au d�tail" >
							<strong><bean:write name="id" property="nomEleve"/> </strong>
						</td>
						<td class="colonneTab" title="Acc�der au d�tail">
							<strong><bean:write name="id" property="prenomEleve"/> </strong>
						</td>
						<td class="colonneTab" title="Acc�der au d�tail">
							<strong> <bean:write name="id" property="dateDeNaissanceEleve"/> </strong>
						</td>
						<td class="colonneTab" title="Acc�der au d�tail">
							<strong> <bean:write name="id" property="adresse1"/> 
							 		<bean:write name="id" property="adresse2"/> 
							</strong>
						</td>
						<td class="colonneTab" title="Acc�der au d�tail">
							<strong> <bean:write name="id" property="codePostal"/> </strong>
						</td>
						<td class="colonneTab" title="Acc�der au d�tail">
							<strong> <bean:write name="id" property="ville"/> </strong>
						</td>
						<td class="colonneTab" title="Acc�der au d�tail">
							<strong> <bean:write name="id" property="classeScolaire"/> </strong>
						</td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
		</div>
	</fieldset>
<!-- 	Boutons de d�placement et de positionnement -->

	<html:select styleId="choixPage" 
				 name="listeAdherentsForm" property="choixPage">
		<logic:present name="listeAdherentsForm" property="listePages">		 
			<html:optionsCollection name="listeAdherentsForm" property="listePages"
 									label="label" value="value"/>   
		</logic:present>
	</html:select>
	
	<logic:equal   name="listeAdherentsForm" property="afficherSuivant" value="true">
		<html:submit  styleId="BtnSuivant" styleClass="bouton" property="action">
			<bean:message key="suivant"/>
		</html:submit>
	</logic:equal>
	
	<logic:equal name="listeAdherentsForm" property="afficherPrecedent" value="true">
		<html:submit styleId="BtnPrecedent" styleClass="bouton" property="action">
			<bean:message key="precedent"/>
		</html:submit>
	</logic:equal>
	
	<logic:present name="listeAdherentsForm" property="listeAdherents">
		<logic:empty name="listeAdherentsForm" property="listeAdherents">
			<bean:message key="listeVide"/>
		</logic:empty>
	</logic:present>
	<html:hidden property="action" styleId="action" value=""/>	
	</html:form>
	
	
</body>
</html>