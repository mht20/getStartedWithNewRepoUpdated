package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    MessageDAO messageDAO;
    ////TODO: Retrieve a specific  Messages using its message ID
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "select * from Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"), rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                message.getMessage_text();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
    public Message getMessageById(int message_id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "select * from Message where message_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_id"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return null;
    }
    public Message deleteMessageById(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "Delete  from Message where messageId =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, messageId);
            preparedStatement.setInt(2, messageId);
            preparedStatement.executeUpdate();

            ResultSet pkeResultSet = preparedStatement.getGeneratedKeys();
            if (pkeResultSet.next()) {
                int generateMessageId = (int) pkeResultSet.getInt(1);
                return new Message();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void updateMessage(int message_id, int posted_id, String message_text,
                              long time_posted_epoch) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "UPDATE Message SET message_id=?,posted_id=?;" +
                    ",message_text=?," +
                    "time_posted_epoch=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,message_id);
            preparedStatement.setInt(2,posted_id);
            preparedStatement.setString(3,message_text);
            preparedStatement.setLong(4,time_posted_epoch);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }
    public Message insertNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //sql logic
            String sql ="INSERT INTO Message (posted_by,message_text,time_posted_epoch) VALUES (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,message.getPosted_by());
            preparedStatement.setString(2,message.getMessage_text());
            preparedStatement.setLong(3,message.getTime_posted_epoch());
            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getInt(1);
                return new Message(generated_message_id, message.getPosted_by(),
                        message.getMessage_text(),message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
    public List<Message> getAllTypeOfMessages()
    {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "select * from Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"), rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                message.getMessage_text();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }


    public List<Message> getAllTypeOfMessages(int messageId,
                                              int postedId,
                                              String messageText,
                                              long timePostedEpoch) {
        return null;
    }
}