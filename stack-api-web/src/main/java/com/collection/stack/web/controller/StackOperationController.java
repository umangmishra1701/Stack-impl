package com.collection.stack.web.controller;

import com.collection.stack.model.constant.StackApiPath;
import com.collection.stack.service.impl.StackOperationService;
import com.collection.stack.web.model.request.PushRequest;
import com.collection.stack.web.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by umang.mishra
 * on 02/05/21
 **/
@RestController
@RequestMapping(value = StackApiPath.BASE_PATH)
@CrossOrigin
@Api(value = "Stack operation Controller")
public class StackOperationController {

    @Autowired
    private StackOperationService stackOperationService;

    @PostMapping(value = StackApiPath.PUSH)
    @ApiOperation(value = "Push element to stack")
    @ResponseBody
    public BaseResponse pushElement(@RequestBody PushRequest pushRequest) {
        return stackOperationService.push(pushRequest);
    }

    @DeleteMapping(value = StackApiPath.POP)
    @ApiOperation(value = "Pop element from stack")
    @ResponseBody
    public BaseResponse<Integer> popElement() {
        return stackOperationService.pop();
    }

    @GetMapping(value = StackApiPath.DISPLAY)
    @ApiOperation(value = "Display element from stack")
    @ResponseBody
    public BaseResponse<Integer[]> displayElement() {
        return stackOperationService.display();
    }
}
