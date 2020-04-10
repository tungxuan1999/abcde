/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abcd.data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

@Path("test")
public class Test {
    
    String strKQ = "KETQUA",strX1="X1",strX2="X2";
    String strSUCCESS = "success",strDATA = "data";
    
    @GET
    @Path("/getdata/A={a}&B={b}&C={c}/data.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataPT2JSON(@PathParam("a") double a,@PathParam("b") double b,@PathParam("c") double c) throws JSONException
    {
        JSONObject js = getJsonPT2(a,b,c);
        return js.toString();
    }
    
    @GET
    @Path("/getdata/A={a}&B={b}&C={c}/data.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getDataPT2XML(@PathParam("a") double a,@PathParam("b") double b,@PathParam("c") double c) throws JSONException
    {
        JSONObject js = getJsonPT2(a,b,c);
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<note>"+XML.toString(js)+"</note>";
    }
    
    @GET
    @Path("/getdata/year={a}/data.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataNamNhuanJSON(@PathParam("a") int a) throws JSONException
    {
        JSONObject js = getJsonNamNhuan(a);
        return js.toString();
    }
    
    @GET
    @Path("/getdata/year={a}/data.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getDataNamNhuanXML(@PathParam("a") int a) throws JSONException
    {
        JSONObject js = getJsonNamNhuan(a);
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<note>"+XML.toString(js)+"</note>";
    }
    
    public JSONObject getJsonNamNhuan(int year) throws JSONException
    {
        JSONObject js = new JSONObject();
        JSONObject jsdata = new JSONObject();
        if(year%100==0&&year%4==0||year%100!=0&&year%4==0)
        {
            js.put(strSUCCESS,true);
            jsdata.put(strKQ, "Năm "+year+" là năm nhuần");
        }
        else{
            js.put(strSUCCESS,true);
            jsdata.put(strKQ, "Năm "+year+" không phải là năm nhuần");
        }
        js.put(strDATA, jsdata);
        return js;
    }
    
    public JSONObject getJsonPT2(double a,double b, double c) throws JSONException
    {
        JSONObject js = new JSONObject();
        JSONObject jsdata = new JSONObject();
        if(a!=0)
        {
            js.put(strSUCCESS,true);
        double denta = b*b - 4*a*c;
        if(denta<0)
        {
            jsdata.put(strX1, "null");
            jsdata.put(strX2, "null");
            jsdata.put(strKQ, "Phương trình vô nghiệm");
        }
        else if(denta == 0)
        {
            jsdata.put(strX1, (double) Math.round(-b/(2*a)*100)/100);
            jsdata.put(strX2,(double) Math.round(-b/(2*a)*100)/100);
            jsdata.put(strKQ, "Phương trình có nghiệm kép");
        }
        else if(denta>0)
        {
            jsdata.put(strX1,(double) Math.round((-b-Math.sqrt(denta))/(2*a)*100)/100);
            jsdata.put(strX2,(double) Math.round((-b+Math.sqrt(denta))/(2*a)*100)/100);
            jsdata.put(strKQ, "Phương trình có 2 nghiệm phân biệt");
        }
        }
        else
        {
            js.put(strSUCCESS,false);
        }
        js.put(strDATA, jsdata);
        return js;
    }
}
