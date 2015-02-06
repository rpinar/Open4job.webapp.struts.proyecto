<%@page import="open4job.web.common.form.AparcamientoForm"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h1>DETALLE DE APARCAMIENTO</h1>
	<!-- le decimos que pinte del holaMundoForm lo que queramos -->
	<br>
	<h2>ID</h2>
	<bean:write name="aparcamientoForm" property="id" />
	<br>
	</body>
</html>