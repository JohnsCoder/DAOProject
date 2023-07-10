package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Seller implements Serializable {
    private Integer Id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Double baseSalary;

    private Department department;

    public Seller(Integer id, String name, String email, LocalDate birthDate, Double baseSalary, Department department) {
        Id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(getId(), seller.getId()) && Objects.equals(getName(), seller.getName()) && Objects.equals(getEmail(), seller.getEmail()) && Objects.equals(getBirthDate(), seller.getBirthDate()) && Objects.equals(getBaseSalary(), seller.getBaseSalary()) && Objects.equals(getDepartment(), seller.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getBirthDate(), getBaseSalary(), getDepartment());
    }

    @Override
    public String toString() {
        return "Seller{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", baseSalary=" + baseSalary +
                ", department=" + department +
                '}';
    }
}
