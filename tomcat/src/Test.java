import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        socket.close();
    }
}
