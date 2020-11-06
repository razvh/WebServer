import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
enum State {Stopped, Running, Maintenance}
public class WebServer implements Runnable{
    private State state ;
    private Socket socket;
    final static int port = 9090;
    final static String Maintenance_File = "maintenance.html";
    final static String Stopped_File = "time_out.html";
    final static String maintenancepath="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\";
    final static String rootpath="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\";
    public WebServer(Socket socket, State state){
        this.socket = socket;
        this.state = state;
    }
    public static String getFileFromRequest(String s)
    {
        String [] l;
        l = s.split(" ");
        for(int i = 0 ; i < l.length ; i++)
            System.out.println(l[i]);
        String fisier = l[1].substring(1);
        if(fisier.isEmpty())
            fisier = "index.html";
        return fisier;
    }
    public static String getContentType(String s)
    {
        String content_type = "text/plain";
        if(s.endsWith(".html"))
            content_type = "text/html";
        else
            if(s.endsWith(".jpeg")||s.endsWith(".jpg"))
                content_type = "image/jpeg";
        else
            if(s.endsWith(".png"))
                content_type = "image/png";
        else
            if (s.endsWith(".css"))
                content_type = "text/css";
        else
            if(s.endsWith(".js"))
                content_type = "text/javascript";
            return content_type;
    }
    public static String addFileToPath(String file)
    {
        //System.out.println(file+rootpath);
        if(file.equals("maintenance.html"))
            return maintenancepath + file;
        return rootpath + file;
    }
    public static File getFileFromPathRunningOrStoppedState(String file)
    {
        File f = new File(file);
        if(f.isFile() && f.exists())
            return f;
        else
            {
                f = new File(rootpath + "404.html");
                return f;
            }
    }
    public static File getFileFromPathMaintenanceState(String file)
    {
        File f = new File(maintenancepath + "maintenance.html");
        return f;
    }
    public void run(){
        System.out.println("New Thread Started");
        try{

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            String path;
            String content_type;
            String statusCode;
            File f;
            if(getState()==State.Running)
            {
                String line = readLineRequest(socket);
                String s = getFileFromRequest(line) ;
                path = addFileToPath(s);
                f = getFileFromPathRunningOrStoppedState(path);
                content_type = getContentType(f.getName());
                statusCode = getStatusCodeRunning(f);
            }
            else if(getState()==State.Stopped) {
                    content_type = getContentType(Stopped_File);
                    path = addFileToPath(Stopped_File);
                    f = getFileFromPathRunningOrStoppedState(path);
                    statusCode = getStatusCodeStopped();
                }
                else {
                        content_type=getContentType(Maintenance_File);
                        path = addFileToPath(Maintenance_File);
                        statusCode = getStatusCodeMaintenance();
                        f = getFileFromPathMaintenanceState(path);
                    }
            BufferedReader b = new BufferedReader(new FileReader(f));
            printResponse(printWriter, statusCode,content_type, f, b);
            b.close();

            socket.close();
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    public static String readLineRequest(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = bufferedReader.readLine();
        return line;
    }
    public static String getStatusCodeRunning(File f) {
        if(f.getName().endsWith("404.html"))
            return "404 Not Found";
        else
            return "200 Ok";
    }
    public static String getStatusCodeStopped() {
            return "522 Timed out";
    }
    public static String getStatusCodeMaintenance()
    {
        return "112 Maintenance";
    }
    private void printResponse(PrintWriter printWriter,String statusCodeResponse, String content_type, File f, BufferedReader b) throws IOException {
        printWriter.println("HTTP/1.1" + statusCodeResponse);
        printWriter.println("Content-Type: " + content_type);
        printWriter.println("Content-Length: " + f.length());
        printWriter.println("");
        String inputLine="";
        while ((inputLine = b.readLine()) != null) {
            printWriter.println(inputLine);
        }
        printWriter.flush();
        printWriter.close();
    }

    public State getState()
    {
        return this.state;
    }
    public static void main(String[] argv) {
        try {
            while(true)
            {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Waiting for connection");
                WebServer myWebServer = new WebServer(serverSocket.accept(),State.Running);
                System.out.println("Connection opened. (" + new Date() + ")");
                Thread thread = new Thread(myWebServer);
                thread.start();
                serverSocket.close();
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}