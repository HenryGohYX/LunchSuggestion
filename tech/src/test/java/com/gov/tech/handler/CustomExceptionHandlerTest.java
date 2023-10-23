package com.gov.tech.handler;

import com.gov.tech.exception.ResourceNotFoundException;
import com.gov.tech.handler.CustomExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @Mock
    MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    BindingResult bindingResult;

    @Test
    void testHandleInvalidArgument() {
        FieldError fieldError = new FieldError("objectName", "fieldName", "Error message");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        Map<String, String> errorMap = customExceptionHandler.handleInvalidArgument(methodArgumentNotValidException);

        assertEquals("Error message", errorMap.get("fieldName"));
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");

        Map<String, String> errorMap = customExceptionHandler.handleResourceNotFoundException(ex);

        assertEquals("Resource not found", errorMap.get("message"));
    }
}
