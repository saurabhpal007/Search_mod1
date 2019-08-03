<?php

$conn=mysqli_connect("localhost","root","","test3");
	$key=$_GET['key'];
	$key0=$_GET['key0'];
	$key2=$_GET['key2'];
	$key3=$_GET['key3'];	
	
if($key0=="null" && $key2=="null" && $key3=="null" ){	

	$query="SELECT * FROM `lo` WHERE `name` LIKE '%$key%'";		
}

elseif($key0=="null" && $key2==!"null" && $key3==!"null" ){
	echo "dono null2";
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(branch) Against('$key2') AND MATCH(start_year) Against('$key3')";	
	
}
elseif($key0==!"null" && $key2=="null" && $key3==!"null" ){
	echo "dono null3";
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(college) Against('$key0') AND MATCH(start_year) Against('$key3')";	
	
}
elseif($key0==!"null" && $key2==!"null" && $key3=="null" ){
	echo "dono null4";
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(college) Against('$key0') AND MATCH(branch) Against('$key2')";	
	
}
elseif($key0=="null" && $key2=="null"){
	echo "dono null5";
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(start_year) Against('$key3')";	
	
}
elseif($key0=="null" && $key3=="null" ){
	echo "dono null6";
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(branch) Against('$key2')";	
	
}
elseif($key3=="null" && $key2=="null"){
	echo "dono null7";
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(college) Against('$key0')";	
	
}
else{
	$query="SELECT *  FROM `lo` WHERE `name` LIKE '%$key%' AND MATCH(college) Against('$key0') AND MATCH(branch) Against('$key2') AND MATCH(start_year) Against('$key3')"; 
}



$result=mysqli_query($conn,$query);		
		$response =array();
		while($row=mysqli_fetch_assoc($result)){
		array_push($response,
		array(
			'id'=>$row['id'],
			'name'=>$row['name'],
			'college'=>$row['college'],
			'branch'=>$row['branch'],
			'start_year'=>$row['start_year'])
			);			
			}		
		
			
	echo json_encode($response);


mysqli_close($conn);

?>