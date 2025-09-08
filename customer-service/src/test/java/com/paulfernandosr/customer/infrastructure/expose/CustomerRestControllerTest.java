package com.paulfernandosr.customer.infrastructure.expose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulfernandosr.customer.application.model.CreateCustomerCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCustomerWhenCustomerCreateDataAreValid() throws Exception {
        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(
                "123456781234", "Paul Fernando", "MALE", 27,
                "Jr. el Hierro", "992857645", "mypa$$", "ACTIVE"
        );

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCustomerCommand)))
                .andExpect(status().isCreated());
    }
}