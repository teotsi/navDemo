package com.example.demo.AccessPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccessPoint.class)
class AccessPointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccessPointRepository accessPointRepository;

    @Autowired
    ObjectMapper mapper;

     @SneakyThrows
     @Test
     void setupTest() throws JsonProcessingException {
        AccessPoint accessPoint = new AccessPoint("p1", "pi1b", 5, 3.2, 5.3, 5.3);
        when(accessPointRepository.findById("p1")).thenReturn(Optional.of(accessPoint));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/access-point")
                .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(accessPoint));
         mockMvc.perform(builder).andExpect(status().isAccepted());

         mockMvc.perform(MockMvcRequestBuilders.get("/access-point").contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk()).andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                 .andExpect((ResultMatcher) jsonPath("$[0].ssid", is("p1")));
     }

    @Test
    void registerAccessPoint() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/access-point");
        mockMvc.perform(request).andExpect(content().string(""));
    }

    @Test
    void getAccessPoints() {
    }

    @Test
    void getAccessPoint() {
    }

}