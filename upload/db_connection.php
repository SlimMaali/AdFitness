<?php
    /**
    *Database config variables,
    */
    define("DB_HOST","127.0.0.1"); 
    define("DB_USER","root");
    define("DB_PASSWORD","");
    define("DB_DATABASE","ADFitness");
 
    $connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
 
    if(mysqli_connect_errno()){
        die("Database connnection failed " . "(" .
            mysqli_connect_error() . " - " . mysqli_connect_errno() . ")"
                );
    }
?>