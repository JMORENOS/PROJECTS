<!DOCTYPE html>
<html>
<!-- ENCABEZADO DE LA PAGINA -->
<head>
<!-- TITULO DE LA PAGINA -->
<title>PHp04</title>
</head>

<!-- CUERPO DE LA PAGINA -->
<body>
<center>
<h1>LOGIN</h1>

<p>Conexion con mysql.</p>
<!-- FORMULARIO  para crear-->
	<form action="insertar.php" method="post" name="form"> <!-- creamos el formularios y manda los datos a procesar mediante post y su nombre es frm  -->
	
	<!-- Creamos nuestros campos de testo  -->	
		  Nombre:<br>
		  <input type="text" name="nombre">
		  <br>
		  Password<br>
		  <input type="Password" name="pw" >
		  <br><br>
		  <!-- boton de envio me manda a procesar-->
		  <input type="submit" value="Insertar Datos">

	</form> 
<br><br>

<!-- FORMULARIO  para consultar -->
<h1>CONSULTA</h1>
<!-- INICIO DEL FORMULARIO  -->
	<form action="select.php" method="post" name="form"> <!-- creamos el formularios y manda los datos a procesar mediante post y su nombre es frm  -->
	
	<!-- Creamos nuestros campos de testo  -->	
		  Nombre a buscar:<br>
		  <input type="text" name="nombre">
		  <br><br>
		  <!-- boton de envio me manda a procesar-->
		  <input type="submit" value="Seleccionar">

	</form> 

<!-- FORMULARIO  para eliminar -->
<h1>ELIMINAR</h1>
<!-- INICIO DEL FORMULARIO  -->
	<form action="eliminar.php" method="post" name="form"> <!-- creamos el formularios y manda los datos a procesar mediante post y su nombre es frm  -->
	
	<!-- Creamos nuestros campos de testo  -->	
		  Nombre a buscar:<br>
		  <input type="text" name="nombre">
		  <br><br>
		  <!-- boton de envio me manda a procesar-->
		  <input type="submit" value="Eliminar">

	</form> 

<!-- FORMULARIO  para Actualizar datos -->
<h1>Actualizar</h1>
<!-- INICIO DEL FORMULARIO  -->
	<form action="actualizar.php" method="post" name="form"> <!-- creamos el formularios y manda los datos a procesar mediante post y su nombre es frm  -->
	
	<!-- Creamos nuestros campos de testo  -->	
		  Nombre:<br>
		  <input type="text" name="nombre">
		  <br><br>
		  Password:<br>
		  <br>
		  <input type="Password" name="pw" >
		  <br><br>
		  <!-- boton de envio me manda a procesar-->
		  <input type="submit" value="Actualizar Datos">
		  <br><br>
		  

	</form> 


<!-- FIN DEL FORMULARIO  -->
</center>
</body> <!-- Fin del Cuerpo en HTML -->
</html> <!-- Fin de el archivo HTML -->