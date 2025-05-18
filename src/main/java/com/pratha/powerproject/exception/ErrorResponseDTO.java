package com.pratha.powerproject.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pramosh Shrestha
 * @created 18/05/2025: 14:13
 */
@Setter
@Getter
public class ErrorResponseDTO {

    private String message;
    private String details;
}
