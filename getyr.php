<?php

$conn=mysqli_connect("localhost","root","","test3");

$query="SELECT DISTINCT start_year FROM lo ORDER BY start_year ASC";

	$result=mysqli_query($conn,$query);
	$response =array();
		while($row=mysqli_fetch_assoc($result)){
		array_push($response,
		array(
			'start_year'=>$row['start_year']
			)
			);
	
	
}
echo json_encode($response);







mysqli_close($conn);

?>