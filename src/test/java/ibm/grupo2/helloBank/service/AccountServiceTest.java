package ibm.grupo2.helloBank.service;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Repositories.AccountRepository;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;


    @BeforeAll
    public void setUp(){
        BDDMockito.given(accountRepository.findByNumber(Mockito.anyString())).willReturn(Optional.of(new Account()));
    }

    public void testFindByNumber(){
        Optional <Account> account = accountService.findByNumber("number test");

        assertTrue(account.isPresent());

    }

}
