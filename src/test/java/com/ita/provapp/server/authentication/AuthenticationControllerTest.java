package com.ita.provapp.server.authentication;

import com.ita.provapp.server.ProvAppServerMain;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.exceptions.PasswordIncorrectException;
import com.ita.provapp.server.common.json.Credential;
import com.ita.provapp.server.common.json.LoginUser;
import com.ita.provapp.server.common.json.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountsService accountsService;

    @Test
    public void testAuthentication() throws EntityNotFoundException, PasswordIncorrectException, Exception {
        Credential credential = new Credential("adam123","password");
        LoginUser user = new LoginUser("1234", new User(1, "adam123", "Adam","Kowalski","adamkow@gmail.com"));

        when(this.accountsService.authenticate(credential)).thenReturn(user);

        mockMvc.perform(
                post("/users/authtokens")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                    .content(new Gson().toJson(credential)))
                .andExpect(content().json(new Gson().toJson(user)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated()).andReturn();
    }
}
