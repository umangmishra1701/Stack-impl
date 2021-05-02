package com.collection.stack.service.impl;

import com.collection.stack.web.model.request.PushRequest;
import com.collection.stack.web.model.response.BaseResponse;

/**
 * Created by umang.mishra on 02/05/21
 **/
public interface StackOperationService {

  BaseResponse push(PushRequest pushRequest);

  BaseResponse<Integer> pop();

  BaseResponse<Integer[]> display();

}
