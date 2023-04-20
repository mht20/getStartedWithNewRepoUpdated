package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.get("localhost:8080/accounts{account_id}", this::account_idHandler);
        //app.get("example-endpoint", this::exampleHandler);
        //app.get("/", ctx -> ctx.result("Hello world")); // context path default
        app.post("localhost:8080/{newAccount}",this::postRegisterHandler);
        app.post("localhost:8080/{login}",this::postLoginHandler);
        app.post("localhost:8080/messages{messages}",this::getMessagesHandler);
        app.get("localhost:8080/retrieveMessages",this::getRetrieveAllMessagesHandler);
        app.get("PATCH localhost:8080/messages/{message_id}",this::getMessageBy_IdHandler);
        app.delete("localhost:8080/messages/{message_id}",this::deleteMessageByIdHandler);
        app.patch("PATCH localhost:8080/messages/{message_id}",this::updateMessageByIdHandler);
        app.get("localhost:8080/accounts/{account_id}/messages",this::getRetrieveAllMessageByUserHandler);
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

    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(),Account.class);
        Account addedAccount = AccountService.addAccount(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));

        }else {
            ctx.status(400);
            //ctx.json("new account created:" );
        }
    }
    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        String password="";
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(),Account.class);
        Account login = AccountService.addAccount(account);
        if(password.length() < 4) {
            ctx.json(mapper.writeValueAsString(login));

        }else {
            ctx.status(401);
        }
    }
    private void getMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message newmessage = mapper.readValue(ctx.body(),Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message newMessage = messageService.newMessage(message_id, newmessage);
        System.out.println(newMessage);
        if(newMessage == null){
            ctx.status(400);
        }else {
            ctx.json(mapper.writeValueAsString(newMessage));
        }
    }

    private void getRetrieveAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
        //ctx.json(messageService.getAllMessages());
    }
    private void getMessageBy_IdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message messageById = mapper.readValue(ctx.body(),Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("messageById"));
        ctx.json(messageById);
    }
    private void  time_posted_epochHandler(Context ctx) {
        ctx.json("sample text");
    }
    private  void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message deleteMessageById = mapper.readValue(ctx.body(),Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("deleteMessageById"));
        ctx.json(deleteMessageById);
    }

    private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException {
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

    }
    public  void getRetrieveAllMessageByUserHandler(Context ctx){
        ctx.json(messageService.getAllTypeOfMessages());

    }


}