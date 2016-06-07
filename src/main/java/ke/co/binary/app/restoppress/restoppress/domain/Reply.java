/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.binary.app.restoppress.restoppress.domain;

import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 *
 * @author minion
 */
@Component
public class Reply implements Serializable{
    
    private int code;
    private Message message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    public String toString(){
        return "Code=> "+code+", Message=> "+message.toString();
    }
    
}

