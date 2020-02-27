<!-- CODIGO PHP -->
<?php
	#incluimos script desde otro script
	#include("conexion.php");
	
	//verificamos que no este vacio
	if(isset( $_POST['nombre'])&& !empty( $_POST['nombre'])&& 
	isset( $_POST['pw'])&& !empty( $_POST['pw']))

 	{ 
 		//Conectamos el servidor con los parametros de conexion
		$con=mysql_connect('localhost','root','Luna77_13j')or die("Problemas al conectar");
		//conectamos la bd..seleccionamos la bd con select
		mysql_select_db("bd1",$con)or die("Problemas al conectar la BD");

		//insertamos datos a la bd
		mysql_query("insert into codigo (nombre,pw) VALUES ('$_POST[nombre]','$_POST[pw]')",$con); 
		echo "Datos insertados correctamente";
	}else{
		echo "Problemas al insertar datos";
	}





?>
<!-- FIN DEL CODIGO PHP  -->