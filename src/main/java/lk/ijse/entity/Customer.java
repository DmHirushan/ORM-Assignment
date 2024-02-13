package lk.ijse.entity;

import lk.ijse.embeded.NameIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lk.ijse.embeded.MobileNumber;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "customer")
public class Customer {
    @Column(name = "cus_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @Column
    private NameIdentifier name;
    @Column
    private int age;
    @Column
    private String address;
    @Column
    private double salary;
    @Column
    @ElementCollection
    @CollectionTable(name = "customer_contact_numbers", joinColumns = @JoinColumn(name = "cus_id"))
    private List<MobileNumber> mobileNos = new ArrayList<>();

    public Customer(NameIdentifier name, int age, String address, double salary, List<MobileNumber> mobileNos) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.mobileNos = mobileNos;
    }
}
