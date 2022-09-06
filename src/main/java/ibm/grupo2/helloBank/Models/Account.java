package ibm.grupo2.helloBank.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_account")
public class Account {
  @Id
  @GeneratedValue
  UUID id;

  @Column(nullable = false)
  String ag;

  @Column(nullable = false)
  String type;

  double balance = 0;

  @Column(nullable = false)
  boolean active;

  @ManyToOne
  @JoinColumn(nullable = false)
  Customer owner_customer;

  @Column(nullable = false)
  LocalDateTime created_at;

  LocalDateTime updated_at;
}
