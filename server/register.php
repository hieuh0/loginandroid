<?php
mysql_connect("localhost","root","");
mysql_select_db("loginvolley");

$name = $_POST["Name"];
$email = $_POST["Email"];

//ma hoa mat khau
$pass = md5($_POST["Pass"]);
//bat loi email da ton tai
$qr = "SELECT * FROM login WHERE EMAIL= '$email'";
$result = mysql_query($qr);

if(mysql_num_rows($result) > 0){
  echo "Email Da Ton Tai";
}else{
  $insert = "INSERT INTO login VALUES (null,'$name','$email','$pass')";
  if(mysql_query($insert)){
    echo "Thanh Cong";
  }else{
    echo "Loi";
  }
}

?>
