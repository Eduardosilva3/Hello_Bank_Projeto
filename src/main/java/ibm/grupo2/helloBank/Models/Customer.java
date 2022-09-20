package ibm.grupo2.helloBank.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
  @Id
  @GeneratedValue
  Long id;

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
  String password;

  @Column(nullable = false)
  LocalDateTime created_at;

  LocalDateTime updated_at;

  @Column(nullable = false)
  boolean card;

  public Customer(){

  }

  public Customer(Long id, String name, String cpf, String email, int age, String phone,
                  String password, LocalDateTime created_at, LocalDateTime updated_at, Boolean card) {
    this.id=id;
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.age = age;
    this.phone = phone;
    this.password = password;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.card= card;
  }

}