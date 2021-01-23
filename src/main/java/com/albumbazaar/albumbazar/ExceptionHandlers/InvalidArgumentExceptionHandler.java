package com.albumbazaar.albumbazar.ExceptionHandlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class InvalidArgumentExceptionHandler {

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class, NumberFormatException.class })
    public ModelAndView ExceptionHandlerForInvalidNumberFormatType(final HttpServletRequest request, Exception e) {

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", "Invalid Input");
        mav.addObject("exception", e);
        return mav;
    }

}
