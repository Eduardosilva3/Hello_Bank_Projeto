package ibm.grupo2.helloBank.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "log")
public class Log {
  @Id
  @GeneratedValue
  Long id;

  String logType;

  String origin;

  String originCpf;

  String destiny;

  @Column(nullable = false)
  double value;

  @Column(nullable = false)
  private Date date = new Date();

}