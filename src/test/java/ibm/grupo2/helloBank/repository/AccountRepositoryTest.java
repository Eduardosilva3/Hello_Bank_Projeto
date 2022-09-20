package ibm.grupo2.helloBank.repository;


import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.Repositories.AccountRepository;

import ibm.grupo2.helloBank.Repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){

        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now().plusMonths(5);
        var id = 1l;
        Customer c1 = new Customer(id,"Billy","123456789", "Billy@test.com",
                50,"90909090","Hadouken", dt1,dt2,Boolean.TRUE);
        customerRepository.save(c1);

        System.out.println("Before Each");

        Account account = new Account();

        account.setId(id);
        account.setAg("test Agency");
        account.setNumber("test Number");
        account.setType("test Type");
        account.isActive();
        account.setBalance(0);
        account.setOwner_customer(c1);
        account.setCreated_at(dt1);

        Account cTest = accountRepository.save(account);



    }

    @AfterEach
    public void tearDown(){
        accountRepository.deleteAll();
    }

    @Test
    void testFindByAccountNumber(){
        String numberTest = "test Number";

        Optional<Account> response =
                accountRepository.findByNumber(numberTest);
        assertTrue(response.isPresent());
        assertEquals(response.get().getNumber(), numberTest);
    }


   @Test
    public void testSave(){

        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now().plusMonths(5);
        var id = 3l;
        Customer c2 =
        customerRepository.save(new Customer(id,"Arnold","99999999", "ArnoldTest@test.com",
                35,"9999999","Hadouken", dt1,dt2,Boolean.TRUE));

        Account acc = new Account();
        acc.setId(id);
        acc.setAg("test Agency 2");
        acc.setNumber("test Number 2");
        acc.setType("test Type 2 ");
        acc.setBalance(100);
        acc.setCreated_at(dt1);

        acc.setOwner_customer(c2);

        Account response = accountRepository.save(acc);

        assertNotNull(response);
    }
}
