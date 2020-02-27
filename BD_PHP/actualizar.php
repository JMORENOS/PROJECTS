<!-- CODIGO PHP -->
<?php
	#incluimos script desde otro script
	#include("conexion.php");
	
 		//Conectamos el servidor con los parametros de conexion
		$con=mysql_connect('localhost','root','Luna77_13j')or die("Problemas al conectar");
		//conectamos la bd..seleccionamos la bd con select
		mysql_select_db("bd1",$con)or die("Problemas al conectar la BD");

		//seleccionamos la tabla de la bd
		$registro=mysql_query("update codigo set pw='$_POST[pw]' where nombre='$_POST[nombre]'")or die("Problemas en la consulta".mysql_error()); 
		echo "Datos Actualizados:"."<br>";







?>
<!-- FIN DEL CODIGO PHP  -->