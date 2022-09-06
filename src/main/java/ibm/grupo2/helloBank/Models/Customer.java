package ibm.grupo2.helloBank.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_customer")
public class Customer {
  @Id
  @GeneratedValue
  UUID id;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  String cpf;

  @Column(nullable = false)
  String email;

  @Column(nullable = false)
  int age;

  @Column(nullable = false)
  String phone;

  @Column(nullable = false)
  LocalDateTime created_at;

  LocalDateTime updated_at;
}
