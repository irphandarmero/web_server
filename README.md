# web_server
membuat web server sendiri menggunakan java

didalam repo ini, kita membuat web server sendiri menggunakan java sebagai bahasa pemrogramannya dan kita menggunakan Visual Studio Code (VS code) sebagai editor.
file utama dari webserver ini adalah WebServer.java , jadi aplikasi yang nantinya kita jalankan pertama adalah WebServer.java ..
pada aplikasi webserver.java berisi perintah untuk menjalankan thread() server pada file Server.java..
pada aplikasi server.java berisi perintah Run() atau Runnable Implements yang didalamnya menjalankan file http_protocol.java..
didalam file server.java kita bisa setting PORT, dimana port itu nantinya digunakan sebagai alamat dari webserver kita
semua kontrol yang ada di aplikasi webserver ini berpusat di http_protocol.java didalamnya berisi perintah untuk menjalankan method yang dikirim oleh browser..
didalah file http_protocol.java terdapat metode sebagai berikut
- handleRequest()
  berisi perintah untuk handle request atau membaca informasi data / request dari browser, seperti metode "GET", "POST", "PUT", "DELETE" semua informasi yang dikirimkan browser kita olah di fungsi ini
- callPHP()
  berisi perintah untuk memanggil file .php , dengan cara menyertakan command "php --path file", didalam fungsi ini kita butuh bantuan file lain yaitu file bridge.php yang fungsinya untuk menerjemahkan atau sebagai jembatan untuk memanggil data yang diminta oleh browser
- handleResponse()
  berisi perintah untuk memberikan data yang diminta oleh browser
- guessContetType()
  menampilkan content Type dari data yang dikirimkan atau diterima
- getFilePath()
  menentukan path dari file atau data yang diminta browser
