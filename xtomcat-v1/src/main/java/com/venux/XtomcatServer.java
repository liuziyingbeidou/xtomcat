package com.venux; /**
 * Created by Administrator on 2018/12/25.
 */

import com.venux.http.Request;
import com.venux.http.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 静态文件
 *
 * @author Alan Liu
 * @create 2018-12-25 16:05
 **/
public class XtomcatServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "xtomcat-v1/webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args){
        XtomcatServer server = new XtomcatServer();
        server.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 80;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

}
