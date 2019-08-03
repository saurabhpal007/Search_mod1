<?php

$conn=mysqli_connect("localhost","root","","test3");
$query="SELECT DISTINCT college FROM lo";
	$result=mysqli_query($conn,$query);
	$response =array();
		while($row=mysqli_fetch_assoc($result)){
		array_push($response,
		array(
			'college'=>$row['college']
			)
			);
	
	
}
echo json_encode($response);







mysqli_close($conn);

?>