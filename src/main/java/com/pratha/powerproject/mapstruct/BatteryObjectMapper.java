package com.pratha.powerproject.mapstruct;

import com.pratha.powerproject.dto.BatteryDto;
import com.pratha.powerproject.entity.Battery;
import com.pratha.powerproject.mapstruct.config.MapstructGlobalConfig;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface Battery object mapper.
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 19:37
 */
@Mapper(config = MapstructGlobalConfig.class)
public interface BatteryObjectMapper {

    /**
     * To dto battery dto.
     *
     * @param battery the battery
     * @return the battery dto
     */
    BatteryDto toDto(Battery battery);

    /**
     * To dto list.
     *
     * @param battery the battery
     * @return the list
     */
    List<BatteryDto> toDtos(List<Battery> battery);

    /**
     * To entity battery.
     *
     * @param batteryDto the battery dto
     * @return the battery
     */
    Battery toEntity(BatteryDto batteryDto);

    /**
     * To entity list.
     *
     * @param batteryDto the battery dto
     * @return the list
     */
    List<Battery> toEntities(List<BatteryDto> batteryDto);

}
