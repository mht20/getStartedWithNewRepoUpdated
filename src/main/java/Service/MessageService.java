package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    //The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
    //persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web orSQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
    //actions undertaken by the API to a logging file.
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    //constructor for a Sms when a SocialMediaDAO is provided
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    public Message addMessages(Message message){
        return messageDAO.getMessageById(message.message_id);
    }
    public Message updateMessages(int message_id, Message message) {
        if (messageDAO.getMessageById(message_id) == null) {
            //return null;
        } else {
            return messageDAO.getMessageById(message_id);
        }
        return null;
    }
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    public List<Message> getAllTypeOfMessages(int message_id,int posted_id, String message_text
    ,long time_posted_epoch){
        return messageDAO.getAllTypeOfMessages(message_id,posted_id,message_text,time_posted_epoch);
    }
}
