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

    public String getClientFirstName(){
        return clientFirstName;
    }
    public String getClientLastName(){
        return clientLastName;
    }
    public String getClientPhoneNo(){
        return clientPhoneNo;
    }
    public String getClientEmail(){
        return clientEmail;
    }
    public int getId() {
        return id;
    }
    public int getStylistId() {
        return stylistId;
    }
    


}
