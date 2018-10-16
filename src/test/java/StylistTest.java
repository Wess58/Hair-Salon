import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import  org.sql2o.*;

public class StylistTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "postgres" ,"makena58");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients *;";
            String deleteStylistQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistQuery).executeUpdate();
        }
    }

    @Test
    public void stylist_instantiatesCorrectly_true() {
        Stylist testStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        assertEquals(true, testStylist instanceof Stylist);
    }
    @Test
    public void getFirstName_StylistInstantiatesWithFirstName_Wes() {
        Stylist testStylist = new Stylist("Wes", "Githua", "Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        assertEquals("Wes", testStylist.getFirstName());
    }
    @Test
    public void getSecondName_StylistInstantiatesWithSecondName_Githua() {
        Stylist testStylist = new Stylist("Wes", "Githua", "Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        assertEquals("Githua", testStylist.getSecondName());
    }
    @Test
    public void getLastName_StylistInstantiatesWithLastName_Mbate() {
        Stylist testStylist = new Stylist("Wes", "Githua", "Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        assertEquals("Mbate", testStylist.getLastName());
    }
    @Test
    public void getEmail_StylistInstantiatesWithEmail() {
        Stylist testStylist = new Stylist("Wes", "Githua", "Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        assertEquals("wessmg@gmail.com", testStylist.getEmail());
    }
    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
        Stylist firstStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        Stylist secondStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        assertTrue(firstStylist.equals(secondStylist));
    }
    @Test
    public void save_savesIntoDatabase_true() {
        Stylist myStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
    }
    @Test
    public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("Wes", "Githua", "Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Breh", "Gith", "Bruh", "0703456085", "34876841", "brehBB@gmail.com");
        secondStylist.save();
        assertTrue( Stylist.all().get(0).equals(firstStylist));
        assertTrue( Stylist.all().get(1).equals(secondStylist));
    }
    @Test
    public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
    }
    @Test
    public void getId_stylistsInstantiateWithAnId_1() {
        Stylist testStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
    }
    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Breh", "Gith", "Bruh", "0703456085", "34876841", "brehBB@gmail.com");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
    @Test
    public void getClients_retrievesAllClientsFromDatabase_ClientsList() {
        Stylist myStylist = new Stylist("Wes", "Githua","Mbate", "0703964085", "34562841", "wessmg@gmail.com");
        myStylist.save();
        Client firstClient = new Client("Nashie","Mbate","0732459873","nashiemg@gmail.com", myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Shie","Kairu","0732568296","shiekk@gmail.com", myStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
    }
}
