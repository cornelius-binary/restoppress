/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.binary.app.restoppress.restoppress.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ke.co.binary.app.restoppress.restoppress.domain.Reply;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author minion
 */
public class ImplGenRequests implements GenerateRequests {

    public static int successful_count = 0;   
    Thread t;
    public static int count = 0;
    public static int failed_count = 0;
    public static int rejected_count = 0;
    public static long time_taken = 0;

    @Override
    public void sendRequests(int number, String url, Reply json_obj, String method) {

        long starttime = System.currentTimeMillis();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < number; i++) {
            //generate random ref code and order info
            json_obj.getMessage().setReferenceNo(generateRandom());
            json_obj.getMessage().setOrderInfo(generateRandom());
            //System.out.println("Request Method: "+json_obj.getMessage());
            Runnable worker = new ProcessRequest(url, method, json_obj);
            executor.execute(worker);
        }
        executor.shutdown();
        //wait for all the threads to terminate
        while (!executor.isTerminated()) {
        }
        long finishtime = System.currentTimeMillis();
        time_taken = (finishtime - starttime);

    }

    @Override
    public String generateRandom() {
        String generatedString = RandomStringUtils.random(7, true, true);
        return generatedString.toUpperCase();
    }

    /**
     * Used to synchronize failed_count variable
     */
    public static synchronized void updateFailed() {
        failed_count++;
    }

    /**
     * Used to synchronize rejected_count variable
     */
    public static synchronized void updateRejected() {
        rejected_count++;
    }

    /**
     * Used to synchronize successful_count variable
     */
    public static synchronized void updatedSuccessful() {
        successful_count++;
    }

}
