public class WebServer {
    public static void main(String[] args) throws Exception {
        Server srv = new Server();
        new Thread(srv).start();
        
    }
    
}