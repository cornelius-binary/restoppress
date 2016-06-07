/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.binary.app.restoppress.restoppress.Service;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import ke.co.binary.app.restoppress.restoppress.domain.Reply;

/**
 *
 * @author minion
 */
public interface GenerateRequests {
    /**
     * Creates a threadpool and sends all requests as per number
     * @param number number of requests to be send
     * @param url url with parameters
     * @param jsonString
     * @param method request method post or get
     * @param success used to update success control
     * @param rejected used to update rejected control
     * @param failed used to update failed contro
     * @param time used to update processing time
     * @param progress used to update progressbar on user control
     */
    public void sendRequests(int number, String url, Reply json_obj, String method/*, Label success, Label rejected, Label failed, Label time*/);
   
    /**
     * Used to generate random String
     * @return returns the generated String
     */
    public String generateRandom();
}
