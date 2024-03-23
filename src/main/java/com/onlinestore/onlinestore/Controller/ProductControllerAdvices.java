package com.onlinestore.onlinestore.Controller;

import com.onlinestore.onlinestore.DTO.ExceptionDTO;
import com.onlinestore.onlinestore.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ProductControllerAdvices {

   
    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDTO> handlesProductNotFoundException( ProductNotFoundException productNotFoundException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDTO.setMsg(productNotFoundException.getMessage());
        return new ResponseEntity(exceptionDTO , HttpStatus.NOT_FOUND);
    }

     
    
    /*

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    private ExceptionDTO handlesProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMsg(productNotFoundException.getMessage());
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        return exceptionDTO;
    }
    */
}
