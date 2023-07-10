package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

import java.sql.Connection;

public class DAOFactory {
    public static SellerDAO createSellerDAO() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
