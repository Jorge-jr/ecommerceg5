package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Comment;
import model.Product;
import model.User;

public class CommentDao extends BasicDao {
		
    public Comment save(Comment address) {
        String sql = "INSERT INTO Comment "
                + " (text, createdAt, ratingNumber, userId, productId) "
                + " values "
                + " (?, ?, ?, ?, ?);";
        try {
            PreparedStatement prepareStatement = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, address.getText());
            prepareStatement.setDate(2, new java.sql.Date(address.getCreatedAt().getTime()));
            prepareStatement.setInt(3, address.getRatingNumber());
            prepareStatement.setLong(4, address.getUser().getId());
            prepareStatement.setLong(5, address.getProduct().getId());
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

    public List<Comment> getByProduct(Product product) {
        List<Comment> list = new ArrayList<>();
        String sql = "select * from Comment where productId = ?;";
        UserDao userDao = new UserDao();
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setLong(1, product.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = userDao.getByUserId(rs.getLong("userId"));
                String text = rs.getString("text");
                Integer ratingNumber = rs.getInt("ratingNumber");
                java.sql.Date createdAt = rs.getDate("createdAt");
                Comment address = new Comment(user, product, text, ratingNumber);
                address.setId(rs.getLong("id"));
                address.setProduct(product);
                address.setCreatedAt(new java.util.Date(createdAt.getTime()));
                list.add(address);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int deleteByCommentId(Long commentId) {
        String sql = "delete from Comment where id = ?;";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setLong(1, commentId);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

}
