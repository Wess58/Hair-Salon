import org.sql2o.*;
import java.util.List;

public class Stylist {
    private String firstName;
    private String secondName;
    private String lastName;
    private String phoneNo;
    private String idNo;
    private String email;
    private int id;

    public Stylist(String firstName, String secondName, String lastName, String phoneNo, String idNo, String email){
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.idNo = idNo;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getSecondName(){
        return secondName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public String getIdNo(){
        return idNo;
    }
    public String getEmail(){
        return email;
    }
    public int getId(){
        return id;
    }
    //DATABASE SASA
    public static List<Stylist> all(){
        String sql = "SELECT id, firstName, secondName, lastName, phoneNo, idNo, email" +
                "FROM stylists";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }
    public List<Client> getClients() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }
    }
    @Override
    public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylist)) {
            return false;
        } else {
            Stylist newStylist = (Stylist) otherStylist;
            return this.getFirstName().equals(newStylist.getFirstName()) &&
                    this.getSecondName().equals(newStylist.getSecondName()) &&
                    this.getLastName().equals(newStylist.getLastName()) &&
                    this.getPhoneNo().equals(newStylist.getPhoneNo()) &&
                    this.getIdNo().equals(newStylist.getIdNo()) &&
                    this.getEmail().equals(newStylist.getEmail()) &&
                    this.getId() == newStylist.getId();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists (firstName, secondName, lastName, phoneNo, idNo, email) " +
                    "VALUES (:firstName, :secondName, :lastName, :phoneNo, :idNo, :email)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("firstName", this.firstName)
                    .addParameter("secondName", this.secondName)
                    .addParameter("lastName", this.lastName)
                    .addParameter("phoneNo", this.phoneNo)
                    .addParameter("idNo", this.idNo)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Stylist find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }


}
