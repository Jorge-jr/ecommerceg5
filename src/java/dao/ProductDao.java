package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Product;

public class ProductDao extends BasicDao {
    
    private ArrayList<Product> list = new ArrayList<>();

    //em funcionamento apenas o save() e o deleteByProductId()
    
    
    
     //alterar os nos dos campos para o correspondente em sua tabela
    public Product save(Product product) {
        String sql = "INSERT INTO product (prod_name, prod_description, prod_price, prod_imageUrl) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement prepareStatement = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setString(2, product.getDescription());
            prepareStatement.setDouble(3, product.getPrice());
            prepareStatement.setString(4, product.getImageUrl());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                product.setId(id);
            }
            return product;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Product getByProductId(Long productId) {
        String sql = "select * from Product where id = ?;";
        Product product = null;
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setLong(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                String imageUrl = rs.getString("imageUrl");
                product = new Product(name, description, price, imageUrl);
                product.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public Product update(Product product) {
        String sql = "update Product set "
                + " name = ?, "
                + " description = ?, "
                + " price = ?, "
                + " imageUrl = ? "
                + " where id = ?; ";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getImageUrl());
            stmt.setLong(5, product.getId());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate == 1) {
                return product;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int deleteByProductId(int productId) {
        String sql = "DELETE FROM product WHERE prod_id = ?;";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setInt(1, productId);
            int rowsHitted = stmt.executeUpdate(); // executa a query e retorna o numero de linhas afetadas
            stmt.close();
            return rowsHitted;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    
    //alterar os nos dos campos para o correspondente em sua tabela
    public ArrayList<Product> listarTodos(){
        String sql = "SELECT * FROM product ";
        try {
            Statement st = this.con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int prod_id = rs.getInt("prod_id");
                String name = rs.getString("prod_name");
                String description = rs.getString("prod_description");
                double price = rs.getDouble("prod_price");
                String imageUrl = rs.getString("prod_imageUrl");
                Product prod = new Product(name, description, price, imageUrl);
                prod.setId(prod_id);
                list.add(prod);
            }
        } catch (Exception erro) {
             throw new RuntimeException("Erro para listar os produtos: "+ erro.getMessage());
        }
        return list;
    }
    
    public int amountOfProducts(){
        String sql = "SELECT COUNT(prod_id) FROM product";
        try {
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
           
            while(rs.next()){
                return rs.getInt(1);
                
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao contabilizar a quantidade de produtos: "+ e.getMessage());
        }
       return 0;
    }
}
