package com.castlabs.mp4info;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


@AutoConfigureMockMvc
@SpringBootTest
class Mp4InfoControllerTests {

  @MockBean
  private Mp4InfoController mp4InfoController;

  @BeforeEach
  void setUp() {
    
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void analyzeMp4File() throws Exception {   

  }
}
