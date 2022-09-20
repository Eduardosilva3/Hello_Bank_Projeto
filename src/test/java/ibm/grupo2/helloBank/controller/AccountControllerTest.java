package ibm.grupo2.helloBank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;

import ibm.grupo2.helloBank.dto.AccountDto;
import ibm.grupo2.helloBank.service.AccountService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class AccountControllerTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final Long ID = 1L;
    private final String AG = "AgencyTest";
    private final String NUMBER = "NumberTest";
    private final String TYPE = "TypeTest";
    private final boolean ACTIVE = Boolean.TRUE;
    private final double BALANCE = 100.00;
    private final LocalDateTime CREATED_AT = LocalDateTime.parse("2000-01-01 12:30", formatter);
    private final LocalDateTime UPDATED_AT = LocalDateTime.parse("2000-01-05 12:00", formatter);
    private final Boolean CARD = true;


    private final Customer CUSTOMER = new Customer(1000L,"Ryan","00000000","test@email.com"
            ,99,"99999999", "a1a2a3", CREATED_AT, UPDATED_AT, CARD );



    @MockBean
    AccountService accountService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testSave() throws Exception {

        BDDMockito.given(accountService.save(Mockito.any(Account.class))).willReturn(getMockAccount());

        System.out.println( jsonPath("$.data").toString() );

        mvc.perform(MockMvcRequestBuilders.post("/account").
                        content(getJsonPayload(ID,AG,NUMBER,TYPE,BALANCE,ACTIVE,CREATED_AT,UPDATED_AT,CARD))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.ag").value(AG))
                .andExpect(jsonPath("$.data.number").value(NUMBER))
                .andExpect(jsonPath("$.data.type").value(TYPE))
                .andExpect(jsonPath("$.data.balance").value(BALANCE))
                .andExpect(jsonPath("$.data.active").value(ACTIVE));
//              .andExpect(jsonPath("$.data.created_at").value(CREATED_AT.format(formatter)))
//              .andExpect(jsonPath("$.data.updated_at").value(UPDATED_AT.format(formatter)));
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
        a.setUpdated_at(UPDATED_AT);

        return a;
    }

    public String getJsonPayload(Long id,
                                 String ag, String number, String type,
                                 double balance, boolean active, LocalDateTime created_at,
                                 LocalDateTime updated_at, Boolean card) throws JsonProcessingException {

        AccountDto accountDto = new AccountDto(id, ag, number,type, balance, active, CUSTOMER,
                created_at, updated_at,CARD);
         ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        System.out.println(mapper.writeValueAsString(accountDto));

        return mapper.writeValueAsString(accountDto);
    }

}
