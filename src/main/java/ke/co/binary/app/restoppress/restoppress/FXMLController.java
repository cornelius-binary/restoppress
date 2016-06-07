package ke.co.binary.app.restoppress.restoppress;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import ke.co.binary.app.restoppress.restoppress.Service.GenerateRequests;
import ke.co.binary.app.restoppress.restoppress.Service.ImplGenRequests;
import ke.co.binary.app.restoppress.restoppress.domain.Reply;
import org.apache.commons.validator.routines.UrlValidator;

public class FXMLController implements Initializable {

    @FXML
    private TableColumn keyColumn, valueColumn;
    @FXML
    private TableView paramTable;
    @FXML
    private ChoiceBox requestMethod;
    @FXML
    private TextField urlView;

    private GenerateRequests genReq;
    @FXML
    protected TextArea textArea;
    @FXML
    protected Label failed, success, rejected, time;
    @FXML
    protected TextField rounds;
    @FXML
    protected FlowPane widgets;
    @FXML
    protected ProgressBar progress;

    private final String url = "";//"http://localhost:8080/api/response";
    private final String default_json = "{\"code\": \"07\",\n"
            + "  \"message\": {\n"
            + "    \"amount\": \"999\",\n"
            + "    \"currency\": \"KES\",\n"
            + "    \"merchantID\": \"00000125\",\n"
            + "    \"referenceNo\": \"1464932326566\",\n"
            + "    \"orderInfo\": \"1464932326566\",\n"
            + "    \"receiptNo\": \"\",\n"
            + "    \"authId\": \"\",\n"
            + "    \"responseCode\": \"07\",\n"
            + "    \"transactionNo\": \"\"\n"
            + "  }\n"
            + "}";

    /**
     * Used to close the application
     *
     * @param event
     */
    public void closeApplcaction(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Used to listen to go action events. Validates number of rounds and
     * generates number of requests a per rounds
     *
     * @param event Action event of go button
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //reset response labels        
        success.setText("");
        rejected.setText("");
        failed.setText("");
        time.setText("");  
        
        progress.setVisible(true);
        
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                prepareRequests();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisible(false);
                        //update response
                       failed.setText("Failed: "+ImplGenRequests.failed_count);
                       success.setText("Success: "+ImplGenRequests.successful_count);
                       rejected.setText("Rejected: "+ImplGenRequests.rejected_count);
                       time.setText("Time: "+ImplGenRequests.time_taken+"ms");
                       
                       //reset static variables
                       ImplGenRequests.failed_count = 0;
                       ImplGenRequests.successful_count = 0;
                       ImplGenRequests.rejected_count = 0;
                       ImplGenRequests.time_taken = 0;
                       
                    }

                });
                return null;
            }

        };
        Thread t = new Thread(task);
        t.setDaemon(false);
        t.start();

    }

    private void prepareRequests() {
        String url2 = urlView.getText();
        //validate enable on release
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(url2)) {
            JOptionPane.showMessageDialog(null, "Please provide a valid url e.g. http://www.binary.co.ke", "Url Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //validate rounds not less than 1 and a valid int, json
        int number = 0;
        try {
            number = Integer.parseInt(rounds.getText());
            if (number < 1) {
                throw new NumberFormatException("");
            }
            String json = textArea.getText();

            //create a reply object to be send
            Reply json_obj = new ObjectMapper().readValue(json, Reply.class);
            System.out.println("Request Object"+json_obj);

            genReq.sendRequests(number, url2, json_obj, requestMethod.getSelectionModel().getSelectedItem().toString()/*, success, rejected, failed, time*/);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Number of requests should be a valid integer and greater than zero", "Invalid entry for Rounds", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            System.out.println("Message error " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Sorry invalid json entry", "JSON Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set request methods
        requestMethod.setItems(FXCollections.observableArrayList("POST"));
        requestMethod.getSelectionModel().selectFirst();

        textArea.setText(default_json);

        genReq = new ImplGenRequests();

        //configure url view
        urlView.setText(this.url);

        //configure labels
        success.setText("");
        success.setTextFill(Color.GREEN);
        time.setText("");
        time.setTextFill(Color.BLUEVIOLET);
        rejected.setText("");
        rejected.setTextFill(Color.ORANGE);
        failed.setText("");
        failed.setTextFill(Color.RED);
        
        //configure progress bar
        progress.setVisible(false);

    }//

}
