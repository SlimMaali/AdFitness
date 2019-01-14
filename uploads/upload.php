<?php 

	define('DB_HOST','localhost');
	define('DB_USERNAME','root');
	define('DB_PASSWORD','');
	define('DB_NAME','adfitness');


	$upload_path = '../uploads/user_image/';

	$server_ip = '10.0.2.2';

	$upload_url = 'http://10.0.2.2/AdFitness/uploads/user_image/';

	$response = array(); 

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		//if(isset($_POST['name']) and isset($_FILES['image']['name'])){
			$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('unable to connect to database ');
			

			$user_id = $_POST['user_id'];
			$gender = $_POST['gender'];
			$weight = $_POST['weight'];
			$height = $_POST['height'];

			$fileinfo = pathinfo($_FILES['image']['name']);

			$extension = $fileinfo['extension']; 

			$file_url = $upload_url . getFileName() . '.'.png; 

			$file_path = $upload_path. getFileName(). '.'.png; 

			try{

			$moved = 	move_uploaded_file($_FILES['image']['tmp_name'], $file_path);
			if( $moved ) {
			  echo "Successfully uploaded";         
			} else {
			  echo "Not uploaded because of error #".$_FILES["image"]["error"];
			}
				$sql = "INSERT INTO profile (id, user_id, gender, weight, height, image) VALUES (NULL, $user_id, '$gender', $weight, $height, '$file_url');
";
				if(mysqli_query($con,$sql)){
					$response['error'] = false; 
					$response['url'] = $file_url; 
					$response['name'] = $name; 
					$response['id']=getFileName();
				}

			}catch(Exception $e){
				$response['error'] = false; 
				$response['message'] = $e->getMessage(); 
			}
			mysqli_close($con);

		//}//else{
			//$response['error'] = true; 
			//$response['message'] = 'please choose a file';
		//}
		
		echo json_encode($response);
	}

	function getFileName(){
		$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('Unable to connect');
		$sql = "SELECT max(id) as id FROM profile";
		$result = mysqli_fetch_array(mysqli_query($con,$sql));
		mysqli_close($con);
		if($result['id']==null){
			return 1; 
		}else{
			return ++$result['id'];
		}
	}