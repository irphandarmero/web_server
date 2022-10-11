<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Test PHP</title>
    </head>
    <body>
        <h1>Tampilkan data POST</h1>
        <?php
        $nama_user = $_POST['namauser'];
        $pass = $_POST['pwd'];
        echo "Data dari browser $nama_user dan $pass";
        ?>
    </body>
</html>