package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller sell) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, sell.getName());
            pst.setString(2, sell.getEmail());
            pst.setDate(3, Date.valueOf(sell.getBirthDate()));
            pst.setDouble(4, sell.getBaseSalary());
            pst.setInt(5, sell.getDepartment().getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();

                if (rs.next()) {
    sell.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("unexpected error, no rows affected!");
            }
        } catch (SQLException err) {
            throw new DbException(err.getMessage());
        } finally {
            DB.closeStatment(pst);
        }

    }

    @Override
    public void update(Seller sell) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? WHERE Id = ?");
            pst.setString(1, sell.getName());
            pst.setString(2, sell.getEmail());
            pst.setDate(3, Date.valueOf(sell.getBirthDate()));
            pst.setDouble(4, sell.getBaseSalary());
            pst.setInt(5, sell.getDepartment().getId());
            pst.setInt(6, sell.getId());

            pst.executeUpdate();


        } catch (SQLException err) {
            throw new DbException(err.getMessage());
        } finally {
            DB.closeStatment(pst);
        }

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
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            List<Seller> sellers = new ArrayList<>();
                pst = conn.prepareStatement("SELECT seller.*, department.Name DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id ORDER BY Name");
                rs = pst.executeQuery();
            Map<Integer, Department> departmentMap = new HashMap<>();
                while (rs.next()) {
                    if (!departmentMap.containsKey(rs.getInt("DepartmentId"))) {
                        Department dep = instantiateDepartment(rs);
                        departmentMap.put(dep.getId(), dep);
                    }
                        sellers.add(instantiateSeller(rs, departmentMap.get(rs.getInt("DepartmentId"))));
                }
                return sellers;

        } catch (SQLException err) {
            throw new DbException(err.getMessage());
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();
        try {
            pst = conn.prepareStatement("SELECT seller.*, department.Name DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");

            pst.setInt(1, department.getId());

            rs = pst.executeQuery();
            boolean hasRow = rs.next();
            if (hasRow) {
                Department dep = instantiateDepartment(rs);
                while (hasRow) {
                    sellers.add(instantiateSeller(rs, dep));
                    hasRow = rs.next();
                }
                return sellers;
            }
            return null;
        } catch (SQLException err) {
            throw new DbException(err.getMessage());
        } finally {
            DB.closeStatment(pst);
            DB.closeResultSet(rs);
        }
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
