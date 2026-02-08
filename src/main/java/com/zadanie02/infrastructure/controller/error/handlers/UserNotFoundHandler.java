package com.zadanie02.infrastructure.controller.error.handlers;

import com.zadanie02.domain.service.GithubService;
import com.zadanie02.infrastructure.controller.error.ErrorUserNotFound;
import com.zadanie02.infrastructure.controller.error.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubService.class)
public class UserNotFoundHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUserNotFound handleException(UserNotFoundException exception){
        return new ErrorUserNotFound(HttpStatus.NOT_FOUND, exception.getMessage());

    }
}
