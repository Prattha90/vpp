package com.pratha.powerproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Post code range dto.
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 20:31
 */
@Getter
@Setter
public class PostCodeRangeDto {

    @NotNull
    @NotBlank
    @Schema(name = "minPostCode", example = "6000", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1)
    private String minPostCode;

    @NotNull
    @NotBlank
    @Schema(name = "maxPostCode", example = "6102", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1)
    private String maxPostCode;
}
