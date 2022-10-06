import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class http_protocol {

    private Socket client;
    public http_protocol(Socket s) {
        this.client = s;
    }

    public void handleRequest() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

        StringBuilder requestBuilder = new StringBuilder();
        String line;

        while (!(line = br.readLine()).isBlank()){
            requestBuilder.append(line+ "\r\n");

        } 
        String request = requestBuilder.toString();
        
        if(request.isEmpty()){
            throw new IOException("kosong");
        }

        System.out.println(request + "dari client");
        String[] requestsLines = request.split("\r\n");
        String[] requestLine = requestsLines[0].split(" ");
        String method = requestLine[0];
        String path = requestLine[1];
        String version = requestLine[2];
        String host = requestsLines[1].split(" ")[1];

        /*List<String> headers = new ArrayList<>();
        for (int h =2; h < requestsLines.length; h++){
            String header = requestsLines[h];
            headers.add(header);
        }

        String accessLog = String.format("Client %s, Method %s, path %s, version %s, host %s , header %s", this.client.toString(), method, path, version, host, headers.toString());
        System.out.println(accessLog);
        */
        if (method.equals("GET")){
            Path filePath = getFilePath(path);
            if (Files.exists(filePath)){
                //
                String contentType = guessContentType(filePath);
                String extension = filePath.toString().toLowerCase();
                if (extension.endsWith(".php")){
                    byte[] script_php = callPHP(filePath.toString().toLowerCase());
                    handleResponse("200 OK", contentType, script_php); 
                } else {
                    handleResponse("200 OK", contentType, Files.readAllBytes(filePath));
                }
            } else {
                byte[] notFoundContent = "<h1>Not found :(</h1>".getBytes();
                handleResponse("404 Not Found", "text/html", notFoundContent);
            }
        }
    }

    private byte[] callPHP(String filePath) {
        String result = "";
        try {
            String command ="php "+filePath;
            Process child = Runtime.getRuntime().exec(command);
            DataInputStream in = new DataInputStream(child.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null){
                result+=line;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.getBytes();
    }

    private void handleResponse(String status, String contentType, byte[] content) throws IOException {
        OutputStream clientOutput = this.client.getOutputStream();
        clientOutput.write(("HTTP/1.1 "+ status +"\r\n").getBytes());
        clientOutput.write(("ContentType: "+contentType+"\r\n").getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write(content);
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        this.client.close();
    }

    private String guessContentType(Path filePath) throws IOException {
        return Files.probeContentType(filePath);
    }

    private Path getFilePath(String path) {
        if("/".equals(path)){
            path = "/index.html";
        }
        String folder = "D:\\Java_algoritma\\Web_Server_part_2\\htdocs";
        return Paths.get(folder, path);
    }
    
}
