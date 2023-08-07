package Service;

import Model.Message;
import Model.Account;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    // Req 4
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }


    // Req 3 
    public Message addMessage(Message message){

        if (message.getMessage_text()!=""&&message.getMessage_text().length()<254){

            return messageDAO.insertMessage(message);
        }else{
            return null;
        }
    }

    // Req 5
    public Message getMessageByID(int id){
        return messageDAO.getMessageByID(id);
    }

    // Req 6
    public Message deleteMessageByID(int id){
        return messageDAO.getMessageByID(id);
    }

    //Req 7
        /**
     * TODO: Use the MessageDAO to update an existing message to the database.
     * I first check that message is not blank and less than 254 chars.
     *
     * @param message_id the ID of the message to be modified.
     * @param message an object containing all data that should replace the values contained by the existing message_id.
     *         the message object does not contain a message ID.
     * @return the newly updated message
     */
    public Message updateMessage(int message_id, Message message){
        Message msg = messageDAO.getMessageByID(message_id);

        if(message.getMessage_text()!=""&&message.getMessage_text().length()<254){
            if(msg==null){
                return null;
            }else{
                //messageDAO.updateMessage(message_id, message);
                msg.setMessage_text(message.getMessage_text());
                System.out.println(msg.getMessage_text());
                return msg ;
            }
        }else{
            return null;
        }
    }

            /**
     * TODO: 
     * I first check that message is not blank and less than 254 chars.
     *
     * @param id the ID of the account (ie, posted_by from message table) 
     * @return a list of messages.
     */
    public List<Message> getMessagesByAccountID(int id){
        List<Message> msglst = messageDAO.getMessagesByAccountID(id);
        Boolean userexists = false;
        List<Account> ad = accountDAO.getAllAccounts();
        for(int i =0; i<ad.size();i++){
            if(ad.get(i).account_id == id){
                userexists = true;
            }else{
                continue;
            }
        }
        if(userexists==true){

            if(msglst==null){
                return null;
            }else if(msglst.isEmpty()){
                System.out.println("isEmpty() treu.");
                return msglst;
            }else{
                System.out.println("msglst:");
                System.out.println(msglst);
                return msglst;
            }
        }else{
            return msglst;
        }
    }


}
