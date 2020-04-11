package com.example.demo.AccessPoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccessPoint.class)
class AccessPointControllerTest {

@Autowired
private MockMvc mockMvc;
    @Test
    void registerAccessPoint() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/access-point");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("",result.getResponse().getContentAsString());
    }

    @Test
    void getAccessPoints() {
    }

    @Test
    void getAccessPoint() {
    }

}