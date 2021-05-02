package com.collection.stack.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.collection.stack.model.constant.StackApiPath;
import com.collection.stack.service.impl.StackOperationService;
import com.collection.stack.web.model.request.PushRequest;
import com.collection.stack.web.model.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by umang.mishra on 02/05/21
 **/

public class StackOperationControllerTest {

  @InjectMocks
  private StackOperationController stackOperationController;

  @Mock
  private StackOperationService stackOperationService;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  private PushRequest pushRequest = new PushRequest();

  @Before
  public void setUp() {
    initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.stackOperationController).build();
  }

  @Test
  public void push() throws Exception {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setSuccess(Boolean.TRUE);
    when(this.stackOperationService.push(this.pushRequest)).thenReturn(baseResponse);
    this.mockMvc
        .perform(post(StackApiPath.BASE_PATH + StackApiPath.PUSH)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(this.pushRequest)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(true)));
    verify(this.stackOperationService).push(this.pushRequest);

  }

  @Test
  public void pop() throws Exception {
    BaseResponse<Integer> baseResponse = new BaseResponse();
    baseResponse.setSuccess(Boolean.TRUE);
    baseResponse.setContent(12);
    when(stackOperationService.pop()).thenReturn(baseResponse);
    this.mockMvc
        .perform(delete(StackApiPath.BASE_PATH + StackApiPath.POP)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(true)));
    verify(this.stackOperationService).pop();
  }

  @Test
  public void display() throws Exception {
    BaseResponse<Integer[]> baseResponse = new BaseResponse();
    baseResponse.setSuccess(Boolean.TRUE);
    when(stackOperationService.display()).thenReturn(baseResponse);
    this.mockMvc
        .perform(get(StackApiPath.BASE_PATH + StackApiPath.DISPLAY)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(true)));
    verify(this.stackOperationService).display();
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(this.stackOperationService);
  }
}
