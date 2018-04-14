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
	
	 <html:errors bundle="erreur"  />
	<html:form styleId="form" action="/ficheReglementSaisie.do">
	
	<table>
	<tr class="theadTabReg">
		<td class="theadTabReg">
			<bean:define id="id" name="ficheReglementsForm" property="modeRegList"/>
			<html:select name="ficheReglementsForm" property="modeReglement"> 
				<html:options collection="id" property="key" labelProperty="value" />
		 	</html:select>
		</td>
		<td class="theadTabReg"> <html:text name="ficheReglementsForm" property="description"/></td>
		<td class="theadTabReg"> <html:text name="ficheReglementsForm" property="dateReglement"/> </td>
		<td class="theadTabReg"> <html:text name="ficheReglementsForm" property="montantReglement"/> </td>
	</tr>
	</table>
	<bean:write name="ficheReglementsForm" property="numeroId"/>
		
	<br>  
		<html:submit styleClass="bouton" property="action"> 
			<bean:message key="valider"/>
		</html:submit>
	
	</html:form>
</body>
</html>