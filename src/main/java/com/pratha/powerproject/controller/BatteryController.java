package com.pratha.powerproject.controller;

import com.pratha.powerproject.dto.BatteryDto;
import com.pratha.powerproject.dto.BatteryStatisticDto;
import com.pratha.powerproject.dto.PostCodeRangeDto;
import com.pratha.powerproject.entity.Battery;
import com.pratha.powerproject.mapstruct.BatteryObjectMapper;
import com.pratha.powerproject.service.BatteryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <b>The type Battery controller</b>
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 19:33
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/battery")
@Tag(
        name = "Battery APIs",
        description = "Encapsulates the business model for battery model. This API serves you with two endpoints")
public class BatteryController {

    private final BatteryService batteryService;
    private final BatteryObjectMapper batteryObjectMapper;

    /**
     * <b>Save batteries response entity</b>
     *
     * @param batteryDtos {@link BatteryDto} the battery dtos
     * @return the response entity: {@link ResponseEntity}
     */
    @PostMapping(value = "/all")
    @Operation(
            summary = "Saves a list of batteries",
            description = "Save the provided batteries information in bulk",
            operationId = "saveBatteries",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Prompt Request for key words for the job title",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    name = "kwRequestDTO",
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    implementation = BatteryDto.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "Example battery data",
                                            summary = "Bulk battery data",
                                            description = "Provided sample data to run the Save API. This is a bulk operation",
                                            value = """
                                                    [
                                                      {
                                                        "name": "Cannington",
                                                        "postcode": "6107",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Midland",
                                                        "postcode": "6057",
                                                        "capacity": 50500
                                                      },
                                                      {
                                                        "name": "Hay Street",
                                                        "postcode": "6000",
                                                        "capacity": 23500
                                                      },
                                                      {
                                                        "name": "Mount Adams",
                                                        "postcode": "6525",
                                                        "capacity": 12000
                                                      },
                                                      {
                                                        "name": "Koolan Island",
                                                        "postcode": "6733",
                                                        "capacity": 10000
                                                      },
                                                      {
                                                        "name": "Armadale",
                                                        "postcode": "6992",
                                                        "capacity": 25000
                                                      },
                                                      {
                                                        "name": "Lesmurdie",
                                                        "postcode": "6076",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Kalamunda",
                                                        "postcode": "6076",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Carmel",
                                                        "postcode": "6076",
                                                        "capacity": 36000
                                                      },
                                                      {
                                                        "name": "Bentley",
                                                        "postcode": "6102",
                                                        "capacity": 85000
                                                      },
                                                      {
                                                        "name": "Akunda Bay",
                                                        "postcode": "2084",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Werrington County",
                                                        "postcode": "2747",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Bagot",
                                                        "postcode": "0820",
                                                        "capacity": 27000
                                                      },
                                                      {
                                                        "name": "Yirrkala",
                                                        "postcode": "0880",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "University of Melbourne",
                                                        "postcode": "3010",
                                                        "capacity": 85000
                                                      },
                                                      {
                                                        "name": "Norfolk Island",
                                                        "postcode": "2899",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Ootha",
                                                        "postcode": "2875",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Kent Town",
                                                        "postcode": "5067",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Northgate Mc",
                                                        "postcode": "9464",
                                                        "capacity": 13500
                                                      },
                                                      {
                                                        "name": "Gold Coast Mc",
                                                        "postcode": "9729",
                                                        "capacity": 50000
                                                      }
                                                    ]
                                                    """
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<List<BatteryDto>> saveBatteries(@RequestBody @Valid @Size(min = 1) List<BatteryDto> batteryDtos) {
        List<Battery> savedBatteries = batteryService.saveBatteries(batteryObjectMapper.toEntities(batteryDtos));

        return new ResponseEntity<>(
                batteryObjectMapper.toDtos(savedBatteries),
                HttpStatus.CREATED
        );
    }

    /**
     * <b>Gets batteries by criteria</b>
     *
     * @param postCodeRangeDto {@link Optional} the optional post code range dto
     * @return the batteries by criteria: {@link ResponseEntity}
     */
    @GetMapping(value = "/postcode/range")
    @Operation(
            summary = "Gets a list of batteries with statistics",
            description = "Fetches list of batteries which satisfies the condition provided via the request parameter(s). Plus, the response body has the names of the batteries sorted alphabetically.",
            operationId = "getBatteriesByCriteria",
            parameters = {
                    @Parameter(
                            name = "postCodeRangeDto",
                            description = "Query parameters. The minimum and the maximum values of the postcode. The boundary values (the minimum and the maximum) are inclusive",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    name = "postCodeRangeDto",
                                    description = "The range value between which the batteries statistic will fall",
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    implementation = PostCodeRangeDto.class
                            )
                    )
            }
    )
    public ResponseEntity<BatteryStatisticDto> getBatteriesByCriteria(@Valid Optional<PostCodeRangeDto> postCodeRangeDto) {
        return postCodeRangeDto.map(rangeDto -> new ResponseEntity<>(
                batteryService.getSortedBatteryNamesWithStatisticByPostCodeRange(rangeDto),
                HttpStatus.OK
        )).orElse(
                new ResponseEntity<>(
                        new BatteryStatisticDto(),
                        HttpStatus.OK
                )
        );
    }

}
