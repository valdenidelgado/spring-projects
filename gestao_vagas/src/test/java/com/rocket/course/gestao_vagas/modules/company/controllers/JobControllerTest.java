package com.rocket.course.gestao_vagas.modules.company.controllers;

import com.rocket.course.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.rocket.course.gestao_vagas.modules.company.entities.CompanyEntity;
import com.rocket.course.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.rocket.course.gestao_vagas.modules.utils.TestUtils.generateToken;
import static com.rocket.course.gestao_vagas.modules.utils.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JobControllerTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void shouldBeAbleToCreateAJob() throws Exception {
        var company = CompanyEntity.builder()
                .name("Rocket")
                .username("rocket")
                .email("r@gmail.com")
                .password("123456")
                .description("Rocket description")
                .build();
        company = companyRepository.saveAndFlush(company);

        var createJobDto = CreateJobDTO.builder()
                .description("Java Developer")
                .benefits("GYMPASS")
                .level("SENIOR")
                .build();
        var result = mvc.perform(post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createJobDto))
                        .header("Authorization", generateToken(company.getId(), "secret")))
                .andExpect(status().isOk());
    }
}