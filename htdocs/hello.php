<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Test PHP</title>
    </head>
    <body>
        <?php 
        $nama = $_GET['nama'];
        $alamat = $_GET['alamat'];
        echo "<p>Nama : ".$nama."</p>";
        echo "<p>Alamat : ".$alamat."</p>";

        for($i = 0; $i <= 10; $i++){
        echo "<p>Perulangan ke : ".$i."</p>";
        }
        ?>
    </body>
</html>