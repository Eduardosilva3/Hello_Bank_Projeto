create database Hellobank;
create database HellobankQa;
create database HellobankQa1;
create database HellobankTest;

use HellobankQa1;

select * from customer;

 INSERT INTO `HellobankQa1`.`customer` (`id`,`age`, `card`, `cpf`, `created_at`, `email`, `name`, `password`, `phone`, `updated_at`) VALUES
 ('28','26', b'1', '111.222.333-98', '2022-09-22 15:11:56.000000', 'teste@teste.com', 'Eduardo', '123456', '+558199999999', '2022-09-22 15:12:39.000000');
 
 INSERT INTO `HellobankQa1`.`customer` (`id`,`age`, `card`, `cpf`, `created_at`, `email`, `name`, `password`, `phone`, `updated_at`) VALUES
 ('29','18', b'1', '111.222.444-98', '2022-09-23 15:11:56.000000', 'teste@teste.com', 'Leopoldo', '123456', '+55819998888', '2022-09-23 15:15:39.000000');
 
 INSERT INTO `HellobankQa1`.`customer` (`id`,`age`, `card`, `cpf`, `created_at`, `email`, `name`, `password`, `phone`, `updated_at`) VALUES
 ('30','45', b'1', '333.222.333-98', '2022-09-24 15:11:56.000000', 'teste@teste.com', 'Fabio', '123456', '+558188889999', '2022-09-24 15:12:39.000000');
 
 INSERT INTO `HellobankQa1`.`account` (`active`, `ag`, `balance`, `created_at`, `number`, `type`, `customer`) 
 VALUES (b'1', '001', '100', '2022-09-22 15:20:42.000000', '225', 'Current', '28');
 
  INSERT INTO `HellobankQa1`.`account` (`active`, `ag`, `balance`, `created_at`, `number`, `type`, `customer`) 
 VALUES (b'1', '001', '100', '2022-09-22 15:30:42.000000', '50', 'Current', '29');
 
 INSERT INTO `HellobankQa1`.`account` (`active`, `ag`, `balance`, `created_at`, `number`, `type`, `customer`) 
 VALUES (b'1', '001', '100', '2022-09-22 15:14:42.000000', '15', 'Current', '30');
 
 INSERT INTO `HellobankQa1`.`log` (`date`,`destiny`, `log_type`, `origin`, `value`) 
 VALUES ('2022-09-21 19:56:13.229000','225', 'deposit', '225', '50');
  
  INSERT INTO `HellobankQa1`.`log` (`date`,`destiny`, `log_type`, `origin`, `value`) 
 VALUES ('2022-09-21 19:40:13.229000','225', 'withdraw', '225', '25');
 
 INSERT INTO `HellobankQa1`.`log` (`date`,`destiny`, `log_type`, `origin`, `value`) 
 VALUES ('2022-09-21 19:55:13.229000','225', 'transfer', '50','50');
 
 
 