package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public Message createMessage(Message message) {
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()
                || message.getMessage_text().length() > 255) {
            return null;
        }
        Account existingAccount = accountDAO.getAccountById(message.getPosted_by());
        if (existingAccount == null) {
            return null;
        }

        return messageDAO.createMessage(message);

    }

    public List<Message> getAllMessages(){
        List <Message> messages = new ArrayList<>();

        messages.addAll(messageDAO.getAllMessages());

        return messages;
    }

    public Message getMessageById(int id){
        return messageDAO.getMessageById(id);
    }



    public Message deleteMessage(int id) {

        Message message = messageDAO.getMessageById(id);
        if (message != null) {
            messageDAO.deleteMessageById(id);
            return message;
        }

        return null;
    }

    public Message updateMessage(int id, String messageText) {
        if (messageText == null || messageText.trim().isEmpty() || messageText.length() > 255) {
            return null;
        }

        Message existingMessage = messageDAO.getMessageById(id);
        if (existingMessage == null) {
            return null;
        }

        existingMessage.setMessage_text(messageText);
        int rowsAffected = messageDAO.updateMessage(id, messageText);
        if (rowsAffected > 0) {
            return existingMessage;
        }

        return null;
    }

    public List<Message> getAllMessagesByUserId(int id){
        List<Message> messages = new ArrayList<>();

        messages.addAll(messageDAO.getAllMessagesByUserId(id));

        return messages;
    }

    

}
