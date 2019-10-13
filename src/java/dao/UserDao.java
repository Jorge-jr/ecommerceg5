package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDao extends BasicDao {

    @SuppressWarnings("CallToPrintStackTrace")
    public User save(User user) {
        String sql = "INSERT INTO User (cpf, email, fullName, username, password) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement prepareStatement = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, user.getCpf());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setString(3, user.getFullName());
            prepareStatement.setString(4, user.getUsername());
            prepareStatement.setString(5, user.getPassword());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if(resultSet.next()){
                Long id = resultSet.getLong(1);
                user.setId(id);
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
       
    }

}
