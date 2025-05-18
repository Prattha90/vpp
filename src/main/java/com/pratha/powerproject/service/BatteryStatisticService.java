package com.pratha.powerproject.service;

import com.pratha.powerproject.entity.Battery;

import java.util.List;

/**
 * <b>The interface Battery statistic service.</b>
 * <p>This service provides mission critical statistics on batteries.</p>
 *
 * @author Pramosh Shrestha
 * @created 03 /11/2023: 23:31
 */
public interface BatteryStatisticService {

    /**
     * <b>Calculates average watt capacity</b>
     *
     * @param batteries {@link List} of {@link Battery}
     * @return int
     */
    int calculateAverageWattCapacity(List<Battery> batteries);

    /**
     * <b>Calculates total watt capacity</b>
     *
     * @param batteries {@link List} of {@link Battery}
     * @return int
     */
    int calculateTotalWattCapacity(List<Battery> batteries);
}
