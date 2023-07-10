package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDAO {
    public void insert(Seller dep);
    public void update(Seller dep);
    public void deleteById(Integer id);
    public Seller findById(Integer id);
    public List<Seller> findAll();
}
