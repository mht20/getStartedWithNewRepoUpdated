package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    public SocialMediaController(){
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.get("example-endpoint", this::exampleHandler);
        //app.get("/", ctx -> ctx.result("Hello world")); // context path default
        app.post("localhost:8080/{newAccount}",this::registerHandler);
        app.post("localhost:8080/{login}",this::loginHandler);
        app.get("localhost:8080/accounts{account_id}", this::account_idHandler);
        app.get("localhost:8080/messages{message_id}",this::message_idHandler);
        app.get("localhost:8080/retrieveMessages",this::retrieveMessagesHandler);
        app.delete("localhost:8080/messages/{message_id}",this::deleteMessageByIdHandler);
        app.put("PATCH localhost:8080/messages/{message_id}",this::updateMessagesHandler);

        // app.start(8080);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
//    private void exampleHandler(Context context) {
//        context.json("sample text");
//    }

    private void registerHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(),Account.class);
        Account addedAccount = AccountService.addAccount(account);
        if(addedAccount == null){
            ctx.status(400);
        }else {
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
        //ctx.json("new account created:" );
    }
    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(),Account.class);
        //???back to it soon

        ctx.json("sample text");
    }
    private void account_idHandler(Context ctx) {
        ctx.json("sample text");
    }
    private void message_idHandler(Context ctx) {
        ctx.json("sample text");
    }
    private void posted_by(Context ctx) {
        ctx.json("this is my first post!");
    }

    private void retrieveMessagesHandler(Context ctx) {
        ctx.json("sample text");
    }

    private void  time_posted_epochHandler(Context ctx) {
        ctx.json("sample text");
    }

    private void updateMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updateMessage = messageService.updateMessages(message_id,message);
        System.out.println(updateMessage);
        if(updateMessage == null){
            ctx.status(400);
        }else {
            ctx.json(mapper.writeValueAsString(updateMessage));
        }
        //ctx.json("sample text");
    }
    private  void deleteMessageByIdHandler(Context ctx) {
        ctx.json("sample text");
    }

}