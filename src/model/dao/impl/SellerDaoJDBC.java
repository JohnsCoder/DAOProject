package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDAO {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller dep) {

    }

    @Override
    public void update(Seller dep) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                return instantiateSeller(rs, instantiateDepartment(rs));
            }
            return null;

        } catch (SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeStatment(pst);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) {
        try {
            return new Seller(rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getDate("BirthDate").toLocalDate(),
                    rs.getDouble("BaseSalary"),
                    dep);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    private Department instantiateDepartment(ResultSet rs) {
        try {
            return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
