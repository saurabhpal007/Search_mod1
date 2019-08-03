<?php

$conn=mysqli_connect("localhost","root","","test3");
$query="SELECT DISTINCT branch FROM lo";
	$result=mysqli_query($conn,$query);
	$response =array();
		while($row=mysqli_fetch_assoc($result)){
		array_push($response,
		array(
			'branch'=>$row['branch']
			)
			);
	
	
}
echo json_encode($response);







mysqli_close($conn);

?>