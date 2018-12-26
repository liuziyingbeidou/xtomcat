package com.venux.servlet;/**
 * Created by Administrator on 2018/12/26.
 */

import com.venux.http.Request;
import com.venux.http.Response;

import java.io.IOException;

/**
 * StaticResponseProcessor
 *
 * @author Alan Liu
 * @create 2018-12-26 16:59
 **/
public class StaticResponseProcessor {

    public void process(Request request, Response response){
        try {
            response.sendSaticResource();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
