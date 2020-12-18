import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
enum State {Stopped, Running, Maintenance}
class InvalidDirectoryException extends Exception{
    public InvalidDirectoryException(String s)
    {
        super(s);
    }
}
public class WebServer implements Runnable {
    public void setState(State state) {
        this.state = state;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void setMaintenancepath(String maintenancepath) {
        WebServer.maintenancepath = maintenancepath;
    }

    public static void setRootpath(String rootpath) {
        WebServer.rootpath = rootpath;
    }
    public static Socket getSocket() {
        return socket;
    }

    private State state;
    private static Socket socket;
    private static ServerSocket serverSocket;
    private int port;
    private final static String Maintenance_File = "maintenance.html";
    private final static String Stopped_File = "time_out.html";
    private static String maintenancepath = "C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\";
    private static String rootpath = "C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\";

    public WebServer(int port, State state) {
        this.port = port;
        this.state = state;
    }

    public void creareSocket() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            WebServer.socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebServer(int port, String rootpath, String maintenancepath, State state) {
        this(port, state);
        WebServer.rootpath = rootpath;
        WebServer.maintenancepath = maintenancepath;
    }

    public static String getFileFromRequest(String s) {
        String[] l;
        l = s.split(" ");
        for (int i = 0; i < l.length; i++)
            System.out.println(l[i]);
        String fisier = l[1].substring(1);
        if (fisier.isEmpty())
            fisier = "index.html";
        return fisier;
    }

    public static String getContentType(String s) {
        String content_type = "text/plain";
        if (s.endsWith(".html") || s.endsWith(".php"))
            content_type = "text/html";
        else if (s.endsWith(".jpeg") || s.endsWith(".jpg"))
            content_type = "image/jpeg";
        else if (s.endsWith(".png"))
            content_type = "image/png";
        else if (s.endsWith(".css"))
            content_type = "text/css";
        else if (s.endsWith(".js"))
            content_type = "text/javascript";
        return content_type;
    }

    public static String addFileToPath(String file) {
        if (maintenancepath.charAt(maintenancepath.length() - 1) != '\\')
            maintenancepath = maintenancepath + "\\";
        if (rootpath.charAt(rootpath.length() - 1) != '\\')
            rootpath = rootpath + "\\";
        //System.out.println(file+rootpath);
        if (file.equals("maintenance.html"))
            return maintenancepath + file;
        return rootpath + file;
    }

    public static File getFileFromPathRunningOrStoppedState(String file) throws InvalidDirectoryException {
        File f = new File(file);
        if (f.isFile() && f.exists())
            return f;
        else {
            int i= file.lastIndexOf('\\');
            String file2 = file.substring(0,i+1)+"index.html";
            System.out.println(file2);
            File f2 = new File(file2);
            if(f2.isFile() && f2.exists())
            {
                f = new File(rootpath + "404.html");
                return f;
            }
            else{
                throw new InvalidDirectoryException("Webroot invalid");
            }
        }
    }

    public static File getFileFromPathMaintenanceState() throws InvalidDirectoryException {
        File f = new File(maintenancepath + "maintenance.html");
        if (f.isFile() && f.exists())
            return f;
        throw new InvalidDirectoryException("folderul mentenanta e gresit");
    }
    public static String readLineRequest(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = bufferedReader.readLine();
        return line;
    }

    public static String getStatusCodeRunning(File f) {
        if (f.getName().endsWith("404.html"))
            return "404 Not Found";
        else
            return "200 Ok";
    }

    public static String getStatusCodeStopped() {
        return "522 Timed out";
    }

    public static String getStatusCodeMaintenance() {
        return "112 Maintenance";
    }

    private void printResponse(PrintWriter printWriter, String statusCodeResponse, String content_type, File f, BufferedReader b) throws IOException {
        printWriter.println("HTTP/1.1" + statusCodeResponse);
        printWriter.println("Content-Type: " + content_type);
        printWriter.println("Content-Length: " + f.length());
        printWriter.println("");
        String inputLine = "";
        while ((inputLine = b.readLine()) != null) {
            printWriter.println(inputLine);
        }
        printWriter.flush();
        printWriter.close();
    }

    public State getState() {
        return this.state;
    }

    public static void pornire(WebServer mywebserver) throws IOException {
        System.out.println("Waiting for connection");
        System.out.println("Connection opened. (" + new Date() + ")");
        mywebserver.creareSocket();
        Thread thread = new Thread(mywebserver);
        thread.start();
        serverSocket.close();
    }

    public void update(String port, String root, String maintenance, State state) {
        this.setState(state);
        this.setPort(Integer.parseInt(port));
        setRootpath(root);
        setMaintenancepath(maintenance);
    }
    public void run() {
        System.out.println("New Thread Started");
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            String path;
            String content_type;
            String statusCode;
            File f;
            if (getState() == State.Running) {
                String line = readLineRequest(socket);
                String s = getFileFromRequest(line);
                path = addFileToPath(s);
                f = getFileFromPathRunningOrStoppedState(path);
                content_type = getContentType(f.getName());
                statusCode = getStatusCodeRunning(f);
            } else if (getState() == State.Stopped) {
                content_type = getContentType(Stopped_File);
                path = addFileToPath(Stopped_File);
                f = getFileFromPathRunningOrStoppedState(path);
                statusCode = getStatusCodeStopped();
            } else {
                content_type = getContentType(Maintenance_File);
                //path = addFileToPath(Maintenance_File);
                statusCode = getStatusCodeMaintenance();
                f = getFileFromPathMaintenanceState();
            }
            BufferedReader b = new BufferedReader(new FileReader(f));
            printResponse(printWriter, statusCode, content_type, f, b);
            b.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InvalidDirectoryException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] argv) {
        while (true) {
            try {
                WebServer myWebServer = new WebServer(9090, State.Running);
                pornire(myWebServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}