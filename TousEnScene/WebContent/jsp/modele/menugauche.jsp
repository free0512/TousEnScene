
<meta charset="UTF-8"/>
<%@ taglib uri="struts-html"   prefix = "html"  %>
<%@ taglib uri="struts-bean"   prefix = "bean"  %>
<%@ taglib uri="struts-logic"  prefix = "logic" %>
<%@ taglib uri="struts-nested" prefix = "nested"%>
<meta http-equiv="Content-style-Type" content="text/css" />

<table style="border:2px solid black">
	<tr style="border:2px solid black"> 
	<th  id="centrer" style="border:2px solid black">
		<span > Menu </span>
	</th>
	</tr>	
	<tr>
		<td>
			<html:link action="/creationFiche">
			<span class="menuGaucheTxt"> <bean:message key="saisieAdherent"/> </span>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link action="/listeAdherent">
			<span class="menuGaucheTxt"> <bean:message key="listeAdherent"/> </span>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link action="/listeReglements">
			<span class="menuGaucheTxt"> <bean:message key="saisieFicheReglement"/> </span>
			</html:link>
		</td>
	</tr>
	<tr> <td> ------------------------------- </td></tr>
	<tr>
		<td> 
			<html:link action="/recapAdhSeances">
			<span class="menuGaucheTxt"> <bean:message key="recapAdhSeanceTitre"/> </span>
			</html:link>
		</td>
	</tr>			
</table>
