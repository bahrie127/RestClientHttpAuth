/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bahri.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.security.crypto.codec.Base64;

/**
 *
 * @author ITDEV03
 */
public class ApacheHttpClientGet {
    public static void main(String[] args) {
        try {
            DefaultHttpClient httpClient=new DefaultHttpClient();
            HttpGet getRequest=new HttpGet(
                    "http://localhost/rest-server-sample-php/index.php/person_c/person");
           getRequest.addHeader("accept", "application/json");
           
           String authorisation = "admin" + ":" + "1234";
byte[] encodedAuthorisation = Base64.encode(authorisation.getBytes());
getRequest.addHeader("Authorization", "Basic " + new String(encodedAuthorisation));
           
           HttpResponse response=httpClient.execute(getRequest);
           
           if(response.getStatusLine().getStatusCode() !=200){
               throw new RuntimeException("failed : HTTP error code : "+response.getStatusLine().getStatusCode());
           }
           BufferedReader br=new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
           
           String output;
            System.out.println("output from server .... \n");
            while((output=br.readLine())!=null){
                System.out.println(output);
            }
            
            httpClient.getConnectionManager().shutdown();
        } catch (IOException ex) {
            Logger.getLogger(ApacheHttpClientGet.class.getName()).log(Level.SEVERE, null, ex);
        }
              
       
    }
}
