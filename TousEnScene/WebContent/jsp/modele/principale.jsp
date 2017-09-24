<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<%@ taglib uri="struts-html"   prefix = "html"  %>  
<%@ taglib uri="struts-bean"   prefix = "bean"  %>
<%@ taglib uri="struts-logic"  prefix = "logic" %>
<%@ taglib uri="struts-nested" prefix = "nested"%>
<%@ taglib uri="struts-tiles"  prefix = "tiles"%>
<title> <tiles:getAsString name="titre"/> </title>
<link rel="stylesheet" type="text/css" 
	  href="${pageContext.request.contextPath}/css/demo.css"/>
</head>
<body>
	<table style="width:100%; height:600px">
<!-- 		 1er ligne  couleur de fond rouge brique--> 
		<tr style="background-color:#C9103B; height:10%">
			<td colspan="1" style=" width:10%" >
				<tiles:insert attribute="enteteImage" />
			</td>
			<td colspan="3" style=" width:90%">
				<tiles:insert attribute="enteteText"/>
			</td>
		</tr>		
<!-- 		 Page Principale  Corps  -->
		<tr style="border:2px solid black; height:85%">
			<td style="border:2px solid black; width:160px"> 
				<tiles:insert attribute="menugauche"/>
			</td> 
<!-- 			<td style="width:2%"> col2 </td> -->
			<td colspan="2" style="border:2px solid black; witdh:80%; vertical-align:top;">
				<tiles:insert attribute="corps"/>
			</td>
			<td style="border:2px solid black; width:10%"> 
			
			</td>
		</tr>
		
<!-- 		 3eme ligne -->
		<tr  style="border:2px solid black; height:5%" >
			<td colspan="4">
				<tiles:insert attribute="pieds"/>
			</td>
		</tr>
		
	</table> 
</body>
</html>