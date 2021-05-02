package com.collection.stack.web.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ErrorResponse implements Serializable {
  private String message;
  private List<String> details;

  public ErrorResponse(String message, List<String> details) {
    super();
    this.message = message;
    this.details = details;
  }

}
