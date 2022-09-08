package ibm.grupo2.helloBank.Models;

import ibm.grupo2.helloBank.Models.Customer;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_account")
public class Account {
  @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false)
  String ag;

  @Column(nullable = false)
  String number;


  @Column(nullable = false)
  String type;

  double balance = 0;

  @Column(nullable = false)
  boolean active = true;

  @ManyToOne
  @JoinColumn(name = "Customer", referencedColumnName = "id")
  Customer owner_customer;

  @Column(nullable = false)
  LocalDateTime created_at;

  LocalDateTime updated_at;

  public Account(){

  }

  public Account(Long id, String ag, String type, double balance, boolean active, Customer owner_customer, LocalDateTime created_at, LocalDateTime updated_at) {
    this.id = id;
    this.ag = ag;
    this.type = type;
    this.balance = balance;
    this.active = active;
    //this.owner_customer = owner_customer;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }
}