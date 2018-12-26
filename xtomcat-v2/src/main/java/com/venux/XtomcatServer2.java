package com.venux;/**
 * Created by Administrator on 2018/12/26.
 */

import com.venux.http.Request;
import com.venux.http.Response;
import com.venux.servlet.ServletProcessor;
import com.venux.servlet.StaticResponseProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Xtomcat
 *
 * @author Alan Liu
 * @create 2018-12-26 16:42
 **/
public class XtomcatServer2 {

    private boolean shutdown =false;
    private static final String SHUT_DOWN="/SHUT_DOWN";
    public static void main(String[] args)
    {
        XtomcatServer2 server = new XtomcatServer2();
        server.await();
    }

    public void await()
    {
        ServerSocket serverSocket=null;
        int port=80;
        try{
            serverSocket=new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
        }

        while(!shutdown)
        {
            Socket socket=null;
            InputStream input=null;
            OutputStream output=null;
            try{
                socket=serverSocket.accept();
                input=socket.getInputStream();
                output=socket.getOutputStream();
                Request request=new Request(input);
                request.parse();

                Response response=new Response(output);
                response.setRequest(request);
                if(request.getUri().startsWith("/servlet/"))
                {
                    ServletProcessor processor=new ServletProcessor();
                    processor.process(request,response);
                }else{
                    StaticResponseProcessor processor=new StaticResponseProcessor();
                    processor.process(request,response);
                }
                socket.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
