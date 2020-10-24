package com.lcn.order.exception;

import com.lcn.order.model.ResponseData;
import com.lcn.order.utils.ServerUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    public ResponseData runTimeErrorHandler(HttpServletRequest request, Exception e) throws Exception
    {
        log.error("内部错误：", e);

        ResponseData responseData = new ResponseData();
        responseData.setStatusCode(ServerUtil.getStatusCode(request));
        responseData.setMessage("服务器正忙，请稍后重试！");
        return responseData;
    }

    @ExceptionHandler(value = MyException.class)
    public ResponseData requestErrorHandler(MyException e) throws Exception
    {
        ResponseData responseData = new ResponseData();
        responseData.setStatusCode(e.getStatusCode());
        responseData.setMessage(e.getMessage());

        return responseData;
    }

}
