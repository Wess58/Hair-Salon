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
    public static Client find (int id){
        try(Connection con = DB.sql2o.open() ){
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }
    public static List<Client> all() {
        String sql = "SELECT id, clientFirstName, clientLastName, clientPhoneNo, clientEmail, stylistId FROM clients";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }
    @Override
    public boolean equals(Object otherClient) {
        if (!(otherClient instanceof Client)) {
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return this.getClientFirstName().equals(newClient.getClientFirstName()) &&
                    this.getClientLastName().equals(newClient.getClientLastName()) &&
                    this.getClientPhoneNo().equals(newClient.getClientPhoneNo()) &&
                    this.getClientEmail().equals(newClient.getClientEmail()) &&
                    this.getId() == newClient.getId()  &&
                    this.getStylistId() == newClient.getStylistId();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (clientFirstName, clientLastName, clientPhoneNo, clientEmail, stylistId) VALUES (:clientFirstName, :clientLastName, :clientPhoneNo, :clientEmail, :stylistId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("clientFirstName", this.clientFirstName)
                    .addParameter("clientLastName", this.clientLastName)
                    .addParameter("clientPhoneNo", this.clientPhoneNo)
                    .addParameter("clientEmail", this.clientEmail)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }
    }


}
