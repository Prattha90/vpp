package com.pratha.powerproject.service.impl;

import com.pratha.powerproject.dto.BatteryStatisticDto;
import com.pratha.powerproject.dto.PostCodeRangeDto;
import com.pratha.powerproject.entity.Battery;
import com.pratha.powerproject.repository.BatteryRepository;
import com.pratha.powerproject.service.BatteryService;
import com.pratha.powerproject.service.BatteryStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <b>The type Battery service</b>
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 19:33
 */
@Service
@RequiredArgsConstructor
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;
    private final BatteryStatisticService batteryStatisticService;

    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        return batteryRepository.saveAll(batteries);
    }

    @Override
    public BatteryStatisticDto getSortedBatteryNamesWithStatisticByPostCodeRange(PostCodeRangeDto postCodeRangeDto) {
        List<Battery> fetchedBatteriesByCriteria = batteryRepository.findBatteriesByPostcodeBetweenOrderByName(postCodeRangeDto.getMinPostCode(), postCodeRangeDto.getMaxPostCode());

        // GUARD CLAUSE: For a null and empty list
        if (Objects.isNull(fetchedBatteriesByCriteria) || fetchedBatteriesByCriteria.isEmpty()) {
            return new BatteryStatisticDto()
                    .setNames(Collections.emptyList())
                    .setTotalCapacity(0)
                    .setAverageCapacity(0);
        }

        return new BatteryStatisticDto()
                .setNames(
                        fetchedBatteriesByCriteria.stream()
                                .map(Battery::getName)
                                .toList()
                )
                .setTotalCapacity(batteryStatisticService.calculateTotalWattCapacity(fetchedBatteriesByCriteria))
                .setAverageCapacity(batteryStatisticService.calculateAverageWattCapacity(fetchedBatteriesByCriteria));
    }
}
