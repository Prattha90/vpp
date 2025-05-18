package com.pratha.powerproject.service.impl;

import com.pratha.powerproject.entity.Battery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Pramosh Shrestha
 * @created 04/11/2023: 06:58
 */
@ExtendWith(MockitoExtension.class)
public class BatteryStatisticServiceImplTest {

    public static final int CAPACITY1 = 13500;
    public static final int CAPACITY2 = 50500;
    public static final int CAPACITY3 = 23500;
    private static final List<Battery> batteryEntities = List.of(
            new Battery()
                    .setId(1L)
                    .setName("Cannington")
                    .setPostcode("6107")
                    .setCapacity(CAPACITY1),
            new Battery()
                    .setId(2L)
                    .setName("Midland")
                    .setPostcode("6057")
                    .setCapacity(CAPACITY2),
            new Battery()
                    .setId(3L)
                    .setName("Hay Street")
                    .setPostcode("6000")
                    .setCapacity(CAPACITY3)
    );
    private static final int totalCapacity = CAPACITY1 + CAPACITY2 + CAPACITY3;
    private static final int averageCapacity = (totalCapacity) / batteryEntities.size();
    @Spy
    private BatteryStatisticServiceImpl batteryStatisticService;

    @Test
    void averageWattCapacity_whenInputIsNull_returnsZero_AndNotInvokeCalculateTotalWattCapacity() {
        // WHEN
        int calculatedAverage = batteryStatisticService.calculateAverageWattCapacity(null);

        // THEN
        // ASSERT
        assertEquals(0, calculatedAverage);

        // VERIFY
        verify(batteryStatisticService, times(0)).calculateTotalWattCapacity(batteryEntities);
    }

    @Test
    void averageWattCapacity_whenInputIsEmpty_returnsZero_AndNotInvokeCalculateTotalWattCapacity() {
        // WHEN
        int calculatedAverage = batteryStatisticService.calculateAverageWattCapacity(Collections.emptyList());

        // THEN
        // ASSERT
        assertEquals(0, calculatedAverage);

        // VERIFY
        verify(batteryStatisticService, times(0)).calculateTotalWattCapacity(batteryEntities);
    }

    @Test
    void averageWattCapacity_mustInvokeCalculateTotalWattCapacity() {
        // WHEN
        batteryStatisticService.calculateAverageWattCapacity(batteryEntities);

        // THEN
        // VERIFY
        verify(batteryStatisticService, times(1)).calculateTotalWattCapacity(batteryEntities);
    }

    @Test
    void averageWattCapacity_mustBePositive_andAccurate() {
        // WHEN
        int calculatedAverage = batteryStatisticService.calculateAverageWattCapacity(batteryEntities);

        // THEN
        // ASSERT
        Assertions.assertAll(
                "Check every conditions for average capacity calculation",
                () -> assertThat(calculatedAverage).isPositive(),
                () -> assertEquals(averageCapacity, calculatedAverage)
        );

        // VERIFY
        verify(batteryStatisticService, times(1)).calculateTotalWattCapacity(batteryEntities);
    }

    @Test
    void totalWattCapacity_whenInputIsNull_returnsZero() {
        // WHEN
        int calculatedAverage = batteryStatisticService.calculateTotalWattCapacity(null);

        // THEN
        // ASSERT
        assertEquals(0, calculatedAverage);
    }

    @Test
    void totalWattCapacity_whenInputIsEmpty_returnsZero() {
        // WHEN
        int calculatedAverage = batteryStatisticService.calculateTotalWattCapacity(Collections.emptyList());

        // THEN
        // ASSERT
        assertEquals(0, calculatedAverage);
    }

    @Test
    void totalWattCapacity_mustBePositive_andAccurate() {
        // WHEN
        int calculatedTotal = batteryStatisticService.calculateTotalWattCapacity(batteryEntities);

        // THEN
        // ASSERT
        Assertions.assertAll(
                "Check every conditions for average capacity calculation",
                () -> assertThat(calculatedTotal).isPositive(),
                () -> assertEquals(totalCapacity, calculatedTotal)
        );
    }
}
