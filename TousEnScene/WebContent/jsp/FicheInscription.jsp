<!DOCTYPE html>
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
	    	  
<title> fiche Inscription adhérent </title>
</head>
<body>
	<span class="ModeAccess">
		 <bean:message key="${ficheInscription.modeAcces}"/>
	</span>	  
	<h1> Bulletin d'Inscription de la saison </h1>
	
	<html:form styleId="form" action="/ficheInscription.do">
	
	<html:hidden name="ficheInscription" property="numeroInterne"/>
	<html:hidden name="ficheInscription" property="statusInsert"/>
		   
	 <html:errors bundle="erreur"  />	 
	 
<!-- 	 Afficher le résultat de l'insertion  -->
	 <logic:equal name="ficheInscription" property="statusInsert" value="0">
	 	<span class="rouge">
	 			<bean:message key="resultatInsert"/>
	 			<bean:write name="ficheInscription" property="statusInsert" />
	 	</span>
	 </logic:equal>
<!-- 	 Si la création est concluante, afficher le numéro interne -->
	 <logic:equal name="ficheInscription" property="statusInsert" value="1">
 		<logic:notEqual name="ficheInscription" property="numeroInterne" value="0"> 
			<span class="vert">
				<bean:message key="numInterne"/>
 				<bean:write name="ficheInscription" property="numeroInterne"/> 
			</span>
		</logic:notEqual>
	</logic:equal>
	
	<fieldset>
	<legend> <bean:message key="idEleve"/>  </legend>
	
<!-- 	Numéro Interne de l'éléve -->
	<span class="labelData150Num"> 
		<bean:message key="numEleve"/>
		<bean:write name="ficheInscription" property="numeroInterne"/> 
	</span>
	<br>
<!-- 	----Nom   ------ -->	
	<span class="labelData150"> <bean:message key="nom" /> <span class="etoileRouge"> * </span> </span> 
	<span class="valueData"> 
		<logic:messagesPresent property="nomKO"> 
		 	<html:text name="ficheInscription" property="nomEleve"
				 		styleId="ErrRed"/> 
		</logic:messagesPresent>		
		<logic:messagesNotPresent property="nomKO">
		 	<html:text name="ficheInscription" property="nomEleve"/> 
		</logic:messagesNotPresent>			
	</span>
<!-- 	---- Classe Scolaire ------ -->	
	<span class="labelDataLine"> <bean:message key="classe"/>  </span>
	<span class="valueDataLine">
	<bean:define id="classID" name="ficheInscription" property="classRadio"/>
<%-- 		 <html:select name="ficheInscription" property="classeScolaire" value="${classID}"> --%>
		<html:select name="ficheInscription" property="classeScolaire" >
			<html:options collection="classID" property="key" labelProperty="value"/>
		</html:select>			 
	</span>	
		<br>
<!-- 	----Prénom  et Date de naissance  ---- -->
<!-- 	----Prénom ----- -->
	<span class="labelData150"> 
		<bean:message key="prenom"/>  
		<span class="etoileRouge"> * </span>
	</span>
	<span class="valueData"> 
		<logic:messagesPresent property="prenomKO">
			<html:text name="ficheInscription" property="prenomEleve" styleId="ErrRed"/>  
		</logic:messagesPresent>
		<logic:messagesNotPresent property="prenomKO">
			<html:text name="ficheInscription" property="prenomEleve"/>  
		</logic:messagesNotPresent>
		
	</span>
<!--   ----  Date de Naissance----- -->	
	<span class="labelDataLine"> 
		<bean:message key="DateNaissance"/> 
		<span class="etoileRouge"> * </span>
	 </span>
	<span class="valueDataLine"> 			
		<logic:messagesPresent property="datNaissKO">
		 	<html:text name="ficheInscription" property="dateDeNaissanceEleve" 
						styleId="ErrRed" />	
		</logic:messagesPresent>
		<logic:messagesNotPresent property="datNaissKO">
		 	<html:text name="ficheInscription" property="dateDeNaissanceEleve" 
		 			   styleClass="blurDateNaiss"/>
		</logic:messagesNotPresent>
	</span>
	<span class="labelDataLineAge"> <bean:message key="age"/> </span>
	<span class="valueDataLineAge">
	<html:text name="ficheInscription" property="ageEleve" size="4" disabled="true"/>
	<bean:message key="ans"/>
	</span>
	<br>
<!-- 	----Adresse  ------ -->		
	<span class="labelData150"> <bean:message key="adresse"/>  </span>
	<span class="valueData"> <html:text name="ficheInscription" property="adresse1" size="50"/> </span>
	<span class="valueData"> <html:text name="ficheInscription" property="adresse2" size="49"/> </span>
	
	
	<br>
<!-- 	----Code Postal et Ville ------ -->
	<span class="labelData150"> <bean:message key="codePostal"/>  </span> 
	<span class="valueData"> 
		<html:text name="ficheInscription" property="codePostal" size="5" maxlength="5" /> 
	</span>
	
	<span class="labelDataLine"> <bean:message key="ville"/>  </span>
	<span class="valueDataLine"> <html:text name="ficheInscription" property="ville"/> </span>
	
	<br>
<!-- 	----Téléphones ------ -->
	<span class="labelData150"> 
		<bean:message key="tel1"/> 
	</span>
	<span class="valueData"> 
			<html:text name="ficheInscription" property="telephoneEleve1" /> 
	</span>
	
	<span class="labelDataLine"> <bean:message key="tel2"/>  </span>
	<span class="valueDataLine"> <html:text name="ficheInscription" property="telephoneEleve2"/> </span>
	
	<br>
<!-- 	----Email ------ -->		
	<span class="labelData150"> <bean:message key="email"/> </span> 
	<span class="valueData"> <html:text name="ficheInscription" property="emailEleve"/> </span>
	
	<br>
<!-- 	----Sexe et majorité ------ -->				
	<span class="labelData150"> <bean:message key="sexe"/>   </span>	
	<span class="valueData"> 
		<logic:iterate id="id" name="ficheInscription" property="sexeRadio" indexId="i">
			<bean:define id="idRadio">
				<bean:write name="id" property="key"/>
			</bean:define>
			<html:radio styleId="flagDC${i}" name="ficheInscription" property="sexeEleve" 						
 						value="${idRadio}" > 			  						
			</html:radio>		
			<bean:write name="id" property="value"/>
		</logic:iterate>
		
		<logic:iterate id="id" name="ficheInscription" property="majoriteRadio">
			<bean:define id="idMajorite">
				<bean:write name="id" property="key"/>
			</bean:define>
			<html:radio   name="ficheInscription" property="majoriteEleve"  
						value="${idMajorite}"/>
			<bean:write name="id" property="value"/>
		</logic:iterate>
	</span>
			  
	</fieldset>	
	<fieldset>
	<legend> <bean:message key="cordParent"/> </legend>
	<span class="labelData150"> <bean:message key="nomPere"/></span>
	<span class="valueData"> <html:text name="ficheInscription" property="nomPere"/> </span>
	<span class="labelDataLine"> <bean:message key="prenomPere"/></span>
	<span class="valueDataLine"> <html:text name="ficheInscription" property="prenomPere"/> </span>
	<br>
	<span class="labelData150"> <bean:message key="nomMere"/> </span> 
	<span class="valueData"> <html:text name="ficheInscription" property="nomMere"/> </span>
	<span class="labelDataLine"> <bean:message key="prenomMere"/></span> 
	<span class="valueDataLine"> <html:text name="ficheInscription" property="prenomMere"/> </span>
	<br>
	<span class="labelData150"> <bean:message key="telPere"/></span>
	<span class="valueData"> <html:text name="ficheInscription" property="telephonePere"/> </span>
	<span class="labelDataLine"> <bean:message key="telMere"/></span>
	<span class="valueDataLine"> <html:text name="ficheInscription" property="telephoneMere"/> </span>
	<br>
	<span class="labelData150"> <bean:message key="emailPere"/></span>
	<span class="valueData"> <html:text name="ficheInscription" property="emailPere"/> </span>
	<span class="labelDataLine"> <bean:message key="emailMere"/></span>
	<span class="valueDataLine"> <html:text name="ficheInscription" property="emailMere"/> </span>
	</fieldset>	
	
	<fieldset>
		<legend> <bean:message key="atelierTitre"/> </legend>
		<span class="labelData800"> <bean:message key="atelier"/> </span>
		<br>
		<span class="valueData"> 
			<logic:iterate id="id" name="ficheInscription" property="atelierRadio">
				<bean:define id="idAtelier">
					<bean:write name="id" property="key"/>
				</bean:define>
				<html:radio name="ficheInscription" property="atelierEleve" 
							value="${idAtelier}"/>
				<bean:write name="id" property="value"/>
				<logic:equal name="id" property="key" value="3">
					<br>
				</logic:equal>
			</logic:iterate>
		</span>
		<br>
		<span class="labelData800"> <bean:message key="reglement"/>	</span>
		<br>
		<span class="valueData">
		<bean:message key="atelierMoins18"/>
		<br>
		<logic:iterate id="id" name="ficheInscription" property="reglementRadio">
			<bean:define id="idReglement">
				<bean:write name="id" property="key"/>
			</bean:define>
			<html:radio name="ficheInscription" property="reglementEleve" 
						value="${idReglement}"/>
			<bean:write name="id" property="value"/> &euro;
			<logic:equal name="id" property="key" value="f">
				<br> 
				<bean:message key="atelierAdulte"/>
				<br>
			</logic:equal>
			<logic:equal name="id" property="key" value="k">
				<br> 
			</logic:equal>
		</logic:iterate>
		</span>
		<br>
		<span class="basdepage"> Tous les ch&eacute;ques sont à remettre à l'inscription, Encaissement le 5 de chaque mois.  
		</span>
		<br>
	</fieldset>
	
<!-- 	L'autorisation de rentrer seul  -->
	<fieldset>
	<legend> <bean:message key="autorisation"/></legend>
	<span class="labelData400"> 
		<bean:message key="autoriser"/> <span class="etoileRouge"> * </span> 
	</span>
	<span class="valueData"> 
		<logic:iterate id="id" name="ficheInscription" property="ouiNonRadio">
		<bean:define id="autorisationID">
			<bean:write name="id" property="key"/>
		</bean:define>
			<html:radio name="ficheInscription" property="autoriser" 
						value="${autorisationID}"/>
			<bean:write name="id" property="value"/>
		</logic:iterate>
	</span>
	<br>
<!-- 	La personne qui viendra chercher l'enfant -->
	<span class="labelData400"> <bean:message key="sinonqui"/>  </span>
	<span class="valueData">
		<logic:messagesPresent property="siOuiQuiKO">	
			<html:text name="ficheInscription" property="siNonQui" size="70"  styleId="ErrRed"/>
		</logic:messagesPresent>
		<logic:messagesNotPresent property="siOuiQuiKO">	
			<html:text name="ficheInscription" property="siNonQui" size="70"/>
		</logic:messagesNotPresent>		
	</span>
	<br>
	
<!-- 	Autorisation de se photographier -->
	<span class="labelData600"> 
		<bean:message key="photo"/> <span class="etoileRouge"> * </span> 
	</span>
	<span class="valueData">
		<logic:iterate id="id" name="ficheInscription" property="ouiNonRadio">
		<bean:define id="photoID">
			<bean:write name="id" property="key"/>
		</bean:define>
			<html:radio name="ficheInscription" property="photographier" 
						value="${photoID}"/>
			<bean:write name="id" property="value"/>			
		</logic:iterate>
	</span>
	<br>
	
	<span class="labelData800"> <bean:message key="textFin1"/> 
		<html:link href="www.tousenscene-bda78.fr" >
			<span style="color:red"> 
				www.tousenscene-bda78.fr. 
			</span>	
		</html:link>
		<bean:message key="textFin2"/>
	</span>
	</fieldset>
	
	<br>
	
	<html:submit  styleClass="bouton" property="action" > 
		<bean:message key="valider"/> 
	</html:submit>
	<html:submit styleClass="bouton" property="action">
		<bean:message key="annuler"/> 
	</html:submit>
	<br>&nbsp; 
	
	</html:form>
</body>
</html>