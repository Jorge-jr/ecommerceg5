package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Phone;
import model.User;

public class PhoneDao extends BasicDao {

    public Phone save(Phone phone) {
        String sql = "INSERT INTO Phone "
                + " (ddd, number, name, userId) "
                + " values "
                + " (?, ?, ?, ?);";
        try {
            PreparedStatement prepareStatement = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, phone.getDdd());
            prepareStatement.setString(2, phone.getNumber());
            prepareStatement.setString(3, phone.getName());
            prepareStatement.setLong(4, phone.getUser().getId());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong(1);
                phone.setId(id);
            }
            return phone;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Phone> getByUser(User user) {
        List<Phone> list = new ArrayList<>();
        String sql = "select * from Phone where userId = ?;";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setLong(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String ddd = rs.getString("ddd");
                String number = rs.getString("number");
                String name = rs.getString("name");
                Phone phone = new Phone(user, ddd, number);
                phone.setName(name);
                phone.setId(id);
                list.add(phone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Phone update(Phone phone) {
        String sql = "update Phone set "
                + " ddd = ?, "
                + " number = ?, "
                + " name = ? "
                + " where id = ?; ";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, phone.getDdd());
            stmt.setString(2, phone.getNumber());
            stmt.setString(3, phone.getName());
            stmt.setLong(4, phone.getId());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate == 1) {
                return phone;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int deleteByAddressId(Long addressId) {
        String sql = "delete from Phone where id = ?;";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setLong(1, addressId);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

}
