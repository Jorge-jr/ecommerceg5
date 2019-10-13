package dao;

import java.sql.Connection;

public abstract class BasicDao {
    protected Connection con;
    
    public BasicDao() {
        this.con = new ConnectionFactory().getConnection();
    }
}
