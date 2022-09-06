package ibm.grupo2.helloBank.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_historic")
public class Log {
  @Id
  @GeneratedValue
  UUID id;

  @Column(nullable = false)
  double value;
  @Column(nullable = false)
  Account src_acc;
  @Column(nullable = false)
  Account dest_acc;

  String description;

  @Column(nullable = false)
  String type; // Saque - Depósito - Transferência;

  @Column(nullable = false)
  LocalDateTime created_at;
}
