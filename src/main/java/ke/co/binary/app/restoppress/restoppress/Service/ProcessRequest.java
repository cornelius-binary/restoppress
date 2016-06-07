/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.binary.app.restoppress.restoppress.Service;

import ke.co.binary.app.restoppress.restoppress.domain.Reply;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author minion
 */
public class ProcessRequest implements Runnable {

    HttpHeaders headers;
    HttpEntity<Reply> process;
    RestTemplate request;
    private String url;
    private String method;

    public ProcessRequest(String url, String method, Reply json_obj) {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        process = new HttpEntity<Reply>(json_obj, headers);
        
        //configure rest 
        request = new RestTemplate();
        
        this.method = method;
        this.url = url; 
    }
 
    @Override
    public void run() {
        try {            
            Reply response;
            if (method.equals("POST")) {
                response = request.postForObject(url, process, Reply.class);
            } else {                
                response = request.getForObject(url, Reply.class);                
            }
            if(response.getCode() == 0){
                ImplGenRequests.updatedSuccessful();
            }else{
                ImplGenRequests.updateRejected();
            }
                
        } catch (Exception ex) {
            System.out.println("Thread Exception: " + ex.getMessage());
            ImplGenRequests.updateFailed();
        }
       
    }

}
