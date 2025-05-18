package com.pratha.powerproject.mapstruct;

import com.pratha.powerproject.dto.BatteryDto;
import com.pratha.powerproject.entity.Battery;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-18T15:28:46+0545",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class BatteryObjectMapperImpl implements BatteryObjectMapper {

    @Override
    public BatteryDto toDto(Battery battery) {
        if ( battery == null ) {
            return null;
        }

        BatteryDto batteryDto = new BatteryDto();

        batteryDto.setId( battery.getId() );
        batteryDto.setName( battery.getName() );
        batteryDto.setPostcode( battery.getPostcode() );
        batteryDto.setCapacity( battery.getCapacity() );

        return batteryDto;
    }

    @Override
    public List<BatteryDto> toDtos(List<Battery> battery) {
        if ( battery == null ) {
            return null;
        }

        List<BatteryDto> list = new ArrayList<BatteryDto>( battery.size() );
        for ( Battery battery1 : battery ) {
            list.add( toDto( battery1 ) );
        }

        return list;
    }

    @Override
    public Battery toEntity(BatteryDto batteryDto) {
        if ( batteryDto == null ) {
            return null;
        }

        Battery battery = new Battery();

        battery.setId( batteryDto.getId() );
        battery.setName( batteryDto.getName() );
        battery.setPostcode( batteryDto.getPostcode() );
        battery.setCapacity( batteryDto.getCapacity() );

        return battery;
    }

    @Override
    public List<Battery> toEntities(List<BatteryDto> batteryDto) {
        if ( batteryDto == null ) {
            return null;
        }

        List<Battery> list = new ArrayList<Battery>( batteryDto.size() );
        for ( BatteryDto batteryDto1 : batteryDto ) {
            list.add( toEntity( batteryDto1 ) );
        }

        return list;
    }
}
