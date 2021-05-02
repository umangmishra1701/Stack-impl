package com.collection.stack.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

  private Integer code;

  private boolean success;

  private String errorMessage;

  private String errorCode;

  private T content;

}
