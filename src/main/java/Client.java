import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
    private String clientFirstName;
    private String clientLastName;
    private String clientPhoneNo;
    private String clientEmail;
    private int id;
    private int stylistId;


    public Client(String clientFirstName, String clientLastName, String clientPhoneNo, String clientEmail, int stylistId){
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPhoneNo = clientPhoneNo;
        this.clientEmail = clientEmail;
        this.stylistId = stylistId;
    }

  

}
