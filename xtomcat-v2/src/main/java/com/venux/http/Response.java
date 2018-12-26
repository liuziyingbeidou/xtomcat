package com.venux.http;/**
 * Created by Administrator on 2018/12/26.
 */

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Response
 *
 * @author Alan Liu
 * @create 2018-12-26 14:18
 **/
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE=1024;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendSaticResource() throws IOException
    {
        byte[] bytes=new byte[BUFFER_SIZE];
        FileInputStream fis=null;
        System.out.println(Constants.WEB_ROOT);
        try{
            File file=new File(Constants.WEB_ROOT,request.getUri());
            fis=new FileInputStream(file);
            int ch=fis.read(bytes,0, BUFFER_SIZE);
            while (ch!=-1)
            {
                output.write(bytes,0,ch);
                ch=fis.read(bytes,0, BUFFER_SIZE);
            }
        }catch (FileNotFoundException e){
            String errorMessage="HTTP/1.1 404 File Not Found\r\n"+
                    "Content-Type:text/html\r\n"+
                    "Content-Length:23\r\n"+
                    "\r\n"+
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        }finally {
            if(fis!=null)
            {
                fis.close();
            }
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
