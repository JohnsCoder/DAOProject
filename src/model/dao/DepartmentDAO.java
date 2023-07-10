package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDAO {
    public void insert(Department dep);
    public void update(Department dep);
    public void deleteById(Integer id);
    public Department findById(Integer id);
    public List<Department> findAll();

}
