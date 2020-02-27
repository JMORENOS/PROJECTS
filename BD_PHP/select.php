<!-- CODIGO PHP -->
<?php
	#incluimos script desde otro script
	#include("conexion.php");
	
 		//Conectamos el servidor con los parametros de conexion
		$con=mysql_connect('localhost','root','Luna77_13j')or die("Problemas al conectar");
		//conectamos la bd..seleccionamos la bd con select
		mysql_select_db("bd1",$con)or die("Problemas al conectar la BD");

		//seleccionamos la tabla de la bd
		$registro=mysql_query("select *from codigo where nombre='$_POST[nombre]'")or die("Problemas en la consulta".mysql_error()); 
		echo "Datos registrados:"."<br>";

//while nos agarra los valores 1 por 1 sin dejar de seleccionar
while ($reg=mysql_fetch_array($registro)) {  //mysql_fetch_array trae los datos ordenados
	//decimos que campos son los que nos va a mostrar
	echo "Usuario: ".$reg['nombre']."<br>";
	echo "Password: ".$reg['pw'];
}





?>
<!-- FIN DEL CODIGO PHP  -->