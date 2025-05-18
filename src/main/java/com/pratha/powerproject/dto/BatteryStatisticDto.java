package com.pratha.powerproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * The type Battery statistic dto.
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 20:25
 */
@Getter
@Setter
public class BatteryStatisticDto implements Serializable {

    private List<String> names;

    private int totalCapacity;

    private int averageCapacity;
}
