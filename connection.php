<?php


    define('DB_HOST','localhost');
    define('DB_USER','root');
    define('DB_PASSWORD','');
    define('DB_NAME','test1');
	$conn=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME) or die('unable to connect');
	

?>