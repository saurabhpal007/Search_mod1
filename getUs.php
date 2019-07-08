<?php

$conn=mysqli_connect("localhost","root","","test2");

if(isset($_GET['key'])){
	$key=$_GET['key'];
	

	$query="SELECT * FROM pm WHERE MATCH(name) Against('$key') "; //will use boolean mode after sucessful deployment to app
	
	$result=mysqli_query($conn,$query);	
	
		$response =array();
		while($row=mysqli_fetch_assoc($result)){
		array_push($response,
		array(
			'id'=>$row['id'],
			'name'=>$row['name'])
			);			
			}		
		
			
	echo json_encode($response);
}
//if key remains empty all data printed just for testing
else{
	$query="SELECT * FROM pm";
	$result=mysqli_query($conn,$query);
	$response =array();
		while($row=mysqli_fetch_assoc($result)){
		array_push($response,
		array(
			'id'=>$row['id'],
			'name'=>$row['name'])
			);
	
	
}
echo json_encode($response);
}

mysqli_close($conn);

?>