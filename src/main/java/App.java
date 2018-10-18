import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
    public static void main(String[] args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

//HEROKU
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);
//end heroku
        //LANDING PAGE && STYLISTS DISPLAY && NEW STYLISTS FORM
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //View individual stylists
        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //basically to post and display in stylists.vtl
        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String firstName = request.queryParams("firstName");
            String secondName = request.queryParams("secondName");
            String lastName = request.queryParams("lastName");
            String phoneNo = request.queryParams("phoneNo");
            String idNo = request.queryParams("idNo");
            String email = request.queryParams("email");
            Stylist newStylist = new Stylist(firstName, secondName, lastName, phoneNo, idNo, email);
            newStylist.save();
            response.redirect("/");
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //form for new clients in a specific stylist
        get("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("clients", Client.all());
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //to view ALL clients in one page
        get("/clients/all", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //to view each clients further details
        get("/clients/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Client posting
        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
            String clientFirstName = request.queryParams("clientFirstName");
            String clientLastName = request.queryParams("clientLastName");
            String clientPhoneNo = request.queryParams("clientPhoneNo");
            String clientEmail = request.queryParams("clientEmail");
            Client newClient = new Client(clientFirstName, clientLastName, clientPhoneNo, clientEmail, stylist.getId());
            newClient.save();
            response.redirect("/stylists");
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());



    }
}
