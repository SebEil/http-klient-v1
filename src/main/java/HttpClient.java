import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {
    private final int responseCode;
    private Map<String, String> responseHeaders = new HashMap<>();

    public HttpClient(String hostName, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(hostName, port);
        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + hostName + "\r\n\r\n";
        socket.getOutputStream().write(request.getBytes());

        String line = readLine(socket);
        System.out.println(line);
        String[] responseLineParts = line.toString().split(" ");
        responseCode = Integer.parseInt(responseLineParts[1]);

        String headerLine;
        while (!(headerLine = readLine(socket)).isEmpty()) {
            System.out.println(headerLine);
            int colonPos = headerLine.indexOf(':');
            String name = headerLine.substring(0, colonPos);
            String value = headerLine.substring(colonPos+1).trim();
            responseHeaders.put(name, value);
        }
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != -1) {
            if (c == '\r' ) {
                socket.getInputStream().read();
                break;
            }
            line.append((char)c);
        }
        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        String hostName = "urlecho.appspot.com";
        int port = 80;
        String requestTarget = "/echo?status=200&body=Hello%20World!";
        new HttpClient(hostName, port, requestTarget);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseHeader(String headerName) {
        return responseHeaders.get(headerName);
    }
}
