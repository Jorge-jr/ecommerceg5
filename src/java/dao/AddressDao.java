package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.User;

public class AddressDao extends BasicDao {

    public Address save(Address address) {
        String sql = "INSERT INTO Address "
                + " (street, number, complement, neighborhood, postalCode, city, state, userId) "
                + " values "
                + " (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement prepareStatement = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, address.getStreet());
            prepareStatement.setString(2, address.getNumber());
            prepareStatement.setString(3, address.getComplement());
            prepareStatement.setString(4, address.getNeighborhood());
            prepareStatement.setString(5, address.getPostalCode());
            prepareStatement.setString(6, address.getCity());
            prepareStatement.setString(7, address.getState());
            prepareStatement.setLong(8, address.getUser().getId());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong(1);
                address.setId(id);
            }
            return address;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Address> getByUser(User user) {
        List<Address> list = new ArrayList<>();
        String sql = "select * from Address where userId = ?;";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setLong(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String street = rs.getString("street");
                String number = rs.getString("number");
                String complement = rs.getString("complement");
                String neighborhood = rs.getString("neighborhood");
                String postalCode = rs.getString("postalCode");
                String city = rs.getString("city");
                String state = rs.getString("state");
                Address address = new Address(user, street, number, neighborhood, postalCode, city, state);
                address.setId(id);
                list.add(address);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Address update(Address address) {
        String sql = "update Address set "
                + " street = ?, "
                + " number = ?, "
                + " complement = ?, "
                + " neighborhood = ?, "
                + " postalCode = ?, "
                + " city = ?, "
                + " state = ? "
                + " where id = ?; ";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getNumber());
            stmt.setString(3, address.getComplement());
            stmt.setString(4, address.getNeighborhood());
            stmt.setString(5, address.getPostalCode());
            stmt.setString(6, address.getCity());
            stmt.setString(7, address.getState());
            stmt.setLong(8, address.getId());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate == 1) {
                return address;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int deleteByAddressId(Long addressId) {
        String sql = "delete from Address where id = ?;";
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
