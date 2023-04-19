package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    MessageDAO messageDAO;
    ////TODO: Retrieve a specific  Messages using its message ID
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<Message>();

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
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_id"), rs.getString("message_text"),
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
            String sql = "Delete * from Message where messageId = ?";
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
            String sql = "UPDATE Message SET message_id=?,posted_id=?" +
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

    public List<Message> getAllTypeOfMessages(int messageId, int postedId, String messageText, long timePostedEpoch) {
        return null;
    }
}