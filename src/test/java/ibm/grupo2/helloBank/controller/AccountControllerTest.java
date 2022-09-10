package ibm.grupo2.helloBank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;

import ibm.grupo2.helloBank.dto.AccountDto;
import ibm.grupo2.helloBank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
public class AccountControllerTest {

    private final Long ID = 1L;
    private final String AG = "AgencyTest";
    private final String NUMBER = "NumberTest";
    private final String TYPE = "TypeTest";
    private final boolean ACTIVE = Boolean.TRUE;
    private final double BALANCE = 100.00;
    private final LocalDateTime CREATED_AT = LocalDateTime.now().minusDays(2);

    private final Customer CUSTOMER = new Customer(1000L,"Ryan","00000000","test@email.com"
            ,99,"99999999", "a1a2a3", LocalDateTime.now(), LocalDateTime.now());



    @MockBean
    AccountService accountService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testSave() throws Exception {

        BDDMockito.given(accountService.save(Mockito.any(Account.class))).willReturn(getMockAccount());

        mvc.perform(MockMvcRequestBuilders.post("/account").content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.ag").value(AG))
                .andExpect(jsonPath("$.data.number").value(NUMBER))
                .andExpect(jsonPath("$.data.type").value(TYPE))
                .andExpect(jsonPath("$.data.balace").value(BALANCE))
                .andExpect(jsonPath("$.data.active").value(ACTIVE))
                .andExpect(jsonPath("$.data.owner_customer").value(CUSTOMER))
                .andExpect(jsonPath("$.data.created_at").value(CREATED_AT))
                .andExpect(jsonPath("$.data.updated_at").value(LocalDateTime.now()));
    }

    public Account getMockAccount(){
        Account a = new Account();
        a.setId(ID);
        a.setAg(AG);
        a.setNumber(NUMBER);
        a.setType(TYPE);
        a.setBalance(BALANCE);
        a.isActive();
        a.setOwner_customer(CUSTOMER);
        a.setCreated_at(CREATED_AT);

        return a;
    }

    public String getJsonPayload() throws JsonProcessingException {
        AccountDto accountDto = new AccountDto(ID, AG, NUMBER,TYPE, BALANCE, ACTIVE, CUSTOMER,
                LocalDateTime.now().minusDays(5), LocalDateTime.now());
        accountDto = modelMapper.map(getMockAccount(), accountDto.getClass());
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(accountDto);
    }


}
