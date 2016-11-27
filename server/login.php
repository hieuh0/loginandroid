<?php
 mysql_connect('localhost','root','');
 mysql_select_db('loginvolley');

$email = $_POST["Email"];
$pass = md5($_POST["Pass"]);

$qr = mysql_query("SELECT * FROM login WHERE Email='$email' AND Pass = '$pass'");
$check = mysql_num_rows($qr);
if($check > 0){
  echo "OK";
}else{
  echo "Loi";
}

?>
