
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
			<span class="menuGaucheTxt"> Ajout d'un adhérent </span>
			</html:link>
		</td>
	</tr>
	<tr>
		<td>
			<html:link action="/listeAdherent">
			<span class="menuGaucheTxt"> Liste des adhérents </span>
			</html:link>
		</td>
		</tr>
		<tr>
		<td>
			<html:link action="/ficheReglement">
			<span class="menuGaucheTxt"> Fiche de réglement </span>
			</html:link>
		</td>
	</tr>
</table>
