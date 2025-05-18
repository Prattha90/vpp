package com.pratha.powerproject.service.impl;

import com.pratha.powerproject.entity.Battery;
import com.pratha.powerproject.service.BatteryStatisticService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Pramosh Shrestha
 * @created 03/11/2023: 23:33
 */
@Service
public class BatteryStatisticServiceImpl implements BatteryStatisticService {

    @Override
    public int calculateAverageWattCapacity(List<Battery> batteries) {
        // GUARD CLAUSE: For null and empty list
        if (Objects.isNull(batteries) || batteries.isEmpty()) {
            return 0;
        }

        int totalWattCapacity = calculateTotalWattCapacity(batteries);
        return totalWattCapacity / batteries.size();
    }

    @Override
    public int calculateTotalWattCapacity(List<Battery> batteries) {
        // GUARD CLAUSE: For null and empty list
        if (Objects.isNull(batteries) || batteries.isEmpty()) {
            return 0;
        }

        return batteries.stream()
                .mapToInt(Battery::getCapacity)
                .sum();
    }
}
