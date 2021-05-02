package com.collection.stack.service.impl;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import com.collection.stack.model.constant.ErrorCode;
import com.collection.stack.service.impl.impl.StackOperationServiceImpl;
import com.collection.stack.web.model.request.PushRequest;
import com.collection.stack.web.model.response.BaseResponse;

/**
 * Created by umang.mishra on 02/05/21
 **/
public class StackOperationServiceImplTest {

  @InjectMocks
  private StackOperationServiceImpl stackOperationService;

  private PushRequest pushRequest;

  @Before
  public void setUp() {
    initMocks(this);
    this.pushRequest = new PushRequest();
    this.pushRequest.setPushElement(12);
    ReflectionTestUtils.setField(stackOperationService, "stackSize", 10);
    this.stackOperationService.initializeStackSize();
  }

  @Test
  public void pushSuccess() {
    BaseResponse baseResponse = this.stackOperationService.push(this.pushRequest);
    Assert.assertTrue(baseResponse.isSuccess());
  }

  @Test
  public void pushStackFull() {
    ReflectionTestUtils.setField(stackOperationService, "stackSize", 0);
    this.stackOperationService.initializeStackSize();
    BaseResponse baseResponse = this.stackOperationService.push(this.pushRequest);
    Assert.assertFalse(baseResponse.isSuccess());
    Assert.assertEquals(ErrorCode.STACK_FULL.getMessage(), baseResponse.getErrorMessage());
  }

  @Test
  public void popSuccess() {
    this.stackOperationService.push(this.pushRequest);
    BaseResponse baseResponse = this.stackOperationService.pop();
    Assert.assertTrue(baseResponse.isSuccess());
    Assert.assertEquals(baseResponse.getContent(), this.pushRequest.getPushElement());
  }

  @Test
  public void popStackEmpty() {
    ReflectionTestUtils.setField(stackOperationService, "stackSize", 0);
    this.stackOperationService.initializeStackSize();
    BaseResponse baseResponse = this.stackOperationService.pop();
    Assert.assertFalse(baseResponse.isSuccess());
    Assert.assertEquals(ErrorCode.STACK_EMPTY.getMessage(), baseResponse.getErrorMessage());
  }

  @Test
  public void displaySuccess() {
    this.stackOperationService.push(this.pushRequest);
    BaseResponse baseResponse = this.stackOperationService.display();
    Assert.assertTrue(baseResponse.isSuccess());
    Assert.assertNotNull(baseResponse.getContent());
  }

  @Test
  public void displayStackEmpty() {
    ReflectionTestUtils.setField(stackOperationService, "stackSize", 0);
    this.stackOperationService.initializeStackSize();
    BaseResponse baseResponse = this.stackOperationService.display();
    Assert.assertFalse(baseResponse.isSuccess());
    Assert.assertEquals(ErrorCode.STACK_EMPTY.getMessage(), baseResponse.getErrorMessage());
  }
}
