import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这是我们自己写的一个web服务，可以返回hello.html
 *
 * 浏览器输入：http://localhost:9999 表示浏览器向localhost(127.0.0.1)表示本机的9999端口发出请求
 */
public class MyTomCat {
    public static void main(String[] args) throws IOException {
        // 1. 在9999端口监听
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println(InetAddress.getLocalHost());
        // 如果serverSocket没有关闭，就等待连接，不停地等待
        while (!serverSocket.isClosed()) {
            System.out.println("======web服务在 9999端口监听======");
            // 2. 等待浏览器。客户端的连接，得到socket
            //  该socket用于通信
            Socket socket = serverSocket.accept();
            // 3. 通过socket得到 输出流
            OutputStream outputStream = socket.getOutputStream();
            //  返回给浏览器/客户端
            // 4. 读取 hello.html 文件并返回
            // 得到文件的字符输入流
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("src/hello.html"));
            String buf = null;
            while ((buf = bufferedReader.readLine()) != null) {
                outputStream.write(buf.getBytes());
            }
//            outputStream.write("hello, i am mytomcat".getBytes());
            // 关闭资源
            outputStream.close();
            socket.close();
        }
        serverSocket.close();
    }
}
