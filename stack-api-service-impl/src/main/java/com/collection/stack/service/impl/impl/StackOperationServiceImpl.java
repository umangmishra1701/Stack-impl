package com.collection.stack.service.impl.impl;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.collection.stack.model.constant.ErrorCode;
import com.collection.stack.service.impl.StackOperationService;
import com.collection.stack.web.model.request.PushRequest;
import com.collection.stack.web.model.response.BaseResponse;

/**
 * Created by umang.mishra on 02/05/21
 **/
@Service
public class StackOperationServiceImpl implements StackOperationService {

  private int[] stack;

  private int topIndex = -1;

  @Value("${stack.max.size}")
  private Integer stackSize;


  @PostConstruct
  public void initializeStackSize() {
    this.stack = new int[stackSize];
  }

  @Override
  public BaseResponse push(PushRequest pushRequest) {
    if (this.topIndex == this.stackSize - 1) {
      return new BaseResponse<>(HttpStatus.OK.value(), false, ErrorCode.STACK_FULL.getMessage(),
          ErrorCode.STACK_FULL.getCode(), null);
    }
    this.stack[++this.topIndex] = pushRequest.getPushElement();
    return new BaseResponse<>(HttpStatus.OK.value(), true, null, null, null);
  }

  @Override
  public BaseResponse<Integer> pop() {
    if (this.topIndex == -1) {
      return new BaseResponse<>(HttpStatus.OK.value(), false, ErrorCode.STACK_EMPTY.getMessage(),
          ErrorCode.STACK_EMPTY.getCode(), null);
    }
    Integer popElement = this.stack[this.topIndex--];
    return new BaseResponse<>(HttpStatus.OK.value(), true, null, null, popElement);
  }

  @Override
  public BaseResponse<Integer[]> display() {
    if (this.topIndex == -1) {
      return new BaseResponse<>(HttpStatus.OK.value(), false, ErrorCode.STACK_EMPTY.getMessage(),
          ErrorCode.STACK_EMPTY.getCode(), null);
    }
    int[] stackElements = Arrays.copyOfRange(this.stack, 0, this.topIndex + 1);
    return new BaseResponse<Integer[]>(HttpStatus.OK.value(), true, null, null,
        Arrays.stream(stackElements).boxed().toArray(Integer[]::new));
  }
}
