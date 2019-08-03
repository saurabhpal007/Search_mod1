<?php

$conn=mysqli_connect("localhost","root","","test3");

	$key0=$_GET['key0'];
	$key2=$_GET['key2'];
	$key3=$_GET['key3'];	

if(!is_null($_GET['key0'])){

	

	$query="SELECT *  FROM `lo` WHERE MATCH(college) Against('$key0') OR MATCH(branch) Against('$key2') AND MATCH(start_year) Against('$key3')"; 
	
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
}
elseif(!is_null($_GET['key2'])){

	

	$query="SELECT *  FROM `lo` WHERE MATCH(college) Against('$key0') AND MATCH(branch) Against('$key2') OR MATCH(start_year) Against('$key3')"; 
	
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
}
//if key remains empty all data printed just for testing
else{
	$query="SELECT * FROM lo";
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