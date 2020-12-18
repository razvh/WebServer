import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;


public class WebServetTest {
    @Test
    public void checkContentTypeHtml() {
        String contentType = "text/html";
        Assert.assertEquals(WebServer.getContentType("index.html"),contentType);
    }
    @Test
    public void checkContentTypeCss() {
        String contentType = "text/css";
        Assert.assertEquals(WebServer.getContentType("style.css"),contentType);
    }
    @Test
    public void checkContentTypeJpeg() {
        String contentType = "image/jpeg";
        Assert.assertEquals(WebServer.getContentType("lala.jpeg"),contentType);
    }
    @Test
    public void checkContentTypePng() {
        String contentType = "image/png";
        Assert.assertEquals(WebServer.getContentType("lala.png"),contentType);
    }
    @Test
    public void checkContentTypeJs() {
        String contentType = "text/javascript";
        Assert.assertEquals(WebServer.getContentType("https://code.jquery.com/jquery-3.2.1.slim.min.js"),contentType);
    }
    @Test
    public void checkContentTypeOther() {
        String contentType = "text/plain";
        Assert.assertEquals(WebServer.getContentType("fisier.cpp"),contentType);
    }
    @Test
    public void checkSocketConnected()throws IOException
    {
        WebServer webServer = new WebServer(9090,State.Running);
        webServer.creareSocket();
        Assert.assertTrue(WebServer.getSocket().isConnected());
        WebServer.getSocket().close();
    }
    @Test
    public void checkReadFromRequest() throws IOException {
        WebServer webServer = new WebServer(9090,State.Running);
        webServer.creareSocket();
        //ServerSocket serverSocket = new ServerSocket(9090);
        Assert.assertEquals(WebServer.readLineRequest(WebServer.getSocket()),"GET /index.html HTTP/1.1");
        WebServer.getSocket().close();
    }

    @Test(expected = IOException.class)
    public void checkReadFromRequestSocketClosed() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9091);
        Socket socket = serverSocket.accept();
        socket.close();
        WebServer.readLineRequest(socket);
    }
    @Test
    public void checkStatusCodeRunningOk()
    {
        File f = new File("lala.html");
        Assert.assertEquals(WebServer.getStatusCodeRunning(f),"200 Ok");
    }
    @Test
    public void checkStatusCodeRunningNotFound()
    {
        File f = new File("404.html");
        Assert.assertEquals(WebServer.getStatusCodeRunning(f),"404 Not Found");
    }
    @Test
    public void checkStatusCodeStopped()
    {
        Assert.assertEquals(WebServer.getStatusCodeStopped(),"522 Timed out");
    }
    @Test
    public void checkStatusCodeMaintenance()
    {
        Assert.assertEquals(WebServer.getStatusCodeMaintenance(),"112 Maintenance");
    }
    @Test
    public void getCorrectPath()
    {
        String s = "index.html";
        Assert.assertEquals(WebServer.addFileToPath(s),"C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\index.html");
    }
    @Test
    public void getMaintenanceCorrectPath()
    {
        String s = "maintenance.html";
        Assert.assertEquals(WebServer.addFileToPath(s),"C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\maintenance.html");
    }
    @Test
    public void getFileFromRequest() {
        String requestLine = "GET /index.html HTTP/1.1";
        Assert.assertEquals(WebServer.getFileFromRequest(requestLine),"index.html");
    }
    @Test
    public void getEmptyFileFromRequest() {
        String requestLine = "GET / HTTP/1.1";
        Assert.assertEquals(WebServer.getFileFromRequest(requestLine),"index.html");
    }
    @Test
    public void getFileFromPathRunningOk() throws  InvalidDirectoryException {
        String file="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\index.html";
        File f = new File(file);
        Assert.assertEquals(WebServer.getFileFromPathRunningOrStoppedState(file),f);
    }
    @Test
    public void getFileFromPathRunningNotFound() throws InvalidDirectoryException {
        String file="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\index2.html";
        String file2="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\404.html";
        File f = new File(file2);
        Assert.assertEquals(WebServer.getFileFromPathRunningOrStoppedState(file),f);
    }
    @Test(expected = InvalidDirectoryException.class)
    public void getFileFromRunningInvalidPath() throws InvalidDirectoryException{
        String file="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\index.html";
        WebServer.getFileFromPathRunningOrStoppedState(file);
    }
    @Test
    public void getFileFromPathStopped() throws InvalidDirectoryException {
        String file2="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\time_out.html";
        File f = new File(file2);
        Assert.assertEquals(WebServer.getFileFromPathRunningOrStoppedState(file2),f);
    }
    @Test
    public void getFileFromPathMaintenance() throws InvalidDirectoryException {
        new WebServer(9090,State.Maintenance);
        File f = new File("C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\maintenance.html");
        Assert.assertEquals(WebServer.getFileFromPathMaintenanceState(),f);
    }
    @Test(expected = InvalidDirectoryException.class)
    public void getFileFromPathMaintenanceInvalidDirectory() throws InvalidDirectoryException {
        new WebServer(9090,State.Maintenance);
        WebServer.setMaintenancepath("C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\");
        WebServer.getFileFromPathMaintenanceState();
    }
    @Test
    public void checkResponseRunningState() throws IOException {
        WebServer webServer = new WebServer(9090,State.Running);
        WebServer.pornire(webServer);
        //WebServer.socket.close();
    }

    @Test
    public void checkResponseStoppedState() throws IOException {
        WebServer webServer = new WebServer(9092,State.Stopped);
        WebServer.pornire(webServer);
        //serverSocket.close();
    }
    @Test
    public void checkResponseMaintenanceState() throws IOException {
        //ServerSocket serverSocket = new ServerSocket(9093);
        WebServer webServer = new WebServer(9093,State.Maintenance);
        WebServer.pornire(webServer);
        //serverSocket.close();
    }
}
