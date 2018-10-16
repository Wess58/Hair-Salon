import org.sql2o.*;
import static org.junit.Assert.*;
import org.junit.*;

public class ClientTest {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "postgres" ,"makena58");
    }
    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients *;";
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }
    @Test
    public void save_returnsTrueIfClientFirstNamesAretheSame() {
        Client myClient = new Client("Nashie","Mbate","0732459873","nashie@gmail.com",1);
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }
    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Nashie","Mbate","0732459873","nashiemg@gmail.com",1);
        firstClient.save();
        Client secondClient = new Client("Shie","Kairu","0732568296","shiekk@gmail.com",2);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }
    @Test
    public void save_savesStylistIdIntoDB_true() {
        Stylist myStylist = new Stylist("Wes", "Githua", "Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        myStylist.save();
        Client myClient = new Client("Nashie","Mbate","0732459873","nashiemg@gmail.com", myStylist.getId());
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertEquals(savedClient.getStylistId(), myStylist.getId());
    }


}
