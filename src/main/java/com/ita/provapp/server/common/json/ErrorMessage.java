package com.ita.provapp.server.common.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

public class ErrorMessage {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp = new Date();
    private List<String> errors = new ArrayList<>();
    private int status = 404;

    public ErrorMessage(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        this.status = status.value();

        errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
    }

    public ErrorMessage(String message, int status) {
        this(message);
        this.status = status;
    }

    public ErrorMessage(String message) {
        errors.add(message);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonIgnore
    public String getMessage() {
        return errors.stream().collect(Collectors.joining());
    }

    public void setMessage(String message) {
        errors.add(message);
    }
}
