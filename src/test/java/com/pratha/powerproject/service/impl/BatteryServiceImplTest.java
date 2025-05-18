package com.pratha.powerproject.service.impl;

import com.pratha.powerproject.dto.BatteryStatisticDto;
import com.pratha.powerproject.dto.PostCodeRangeDto;
import com.pratha.powerproject.entity.Battery;
import com.pratha.powerproject.repository.BatteryRepository;
import com.pratha.powerproject.service.BatteryStatisticService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Pramosh Shrestha
 * @created 02/11/2023: 17:11
 */
@ExtendWith(MockitoExtension.class)
class BatteryServiceImplTest {

    public static final String CANNINGTON = "Cannington";
    public static final String MIDLAND = "Midland";
    public static final String HAY_STREET = "Hay Street";
    public static final String MIN_POSTCODE = "6000";
    public static final String POSTCODE = "6057";
    public static final String MAX_POSTCODE = "6107";
    public static final int CAPACITY1 = 13500;
    public static final int CAPACITY2 = 50500;
    public static final int CAPACITY3 = 23500;
    private static final long AUTO_GENERATED_ID1 = 1L;
    private static final long AUTO_GENERATED_ID2 = 2L;
    private static final long AUTO_GENERATED_ID3 = 3L;
    // Set the field values for the list of persistable (Without primary key) Battery entities
    private static final List<Battery> persistableBatteryEntities = List.of(
            new Battery()
                    .setName(CANNINGTON)
                    .setPostcode(MAX_POSTCODE)
                    .setCapacity(CAPACITY1),
            new Battery()
                    .setName(MIDLAND)
                    .setPostcode(POSTCODE)
                    .setCapacity(CAPACITY2),
            new Battery()
                    .setName(HAY_STREET)
                    .setPostcode(MIN_POSTCODE)
                    .setCapacity(CAPACITY3)
    );
    // Set the auto generated primary key in saved battery entities.
    private static final List<Battery> savedBatteryEntities = List.of(
            new Battery()
                    .setId(AUTO_GENERATED_ID1)
                    .setName(CANNINGTON)
                    .setPostcode(MAX_POSTCODE)
                    .setCapacity(CAPACITY1),
            new Battery()
                    .setId(AUTO_GENERATED_ID2)
                    .setName(MIDLAND)
                    .setPostcode(POSTCODE)
                    .setCapacity(CAPACITY2),
            new Battery()
                    .setId(AUTO_GENERATED_ID3)
                    .setName(HAY_STREET)
                    .setPostcode(MIN_POSTCODE)
                    .setCapacity(CAPACITY3)
    );
    private static final List<Battery> fetchedBatteriesByCriteriaSortedByName = savedBatteryEntities
            .stream()
            .sorted(Comparator.comparing(Battery::getName))
            .toList();
    private static final PostCodeRangeDto postCodeRangeDto = new PostCodeRangeDto()
            .setMinPostCode(MIN_POSTCODE)
            .setMaxPostCode(MAX_POSTCODE);
    @Mock
    private BatteryRepository batteryRepository;
    @Mock
    private BatteryStatisticService batteryStatisticService;
    @InjectMocks
    private BatteryServiceImpl batteryServiceImpl;

    @Test
    void saveBatteries_shouldReturn_listOfBatteryObjectWithPrimaryKeys() {
        // GIVEN
        given(batteryRepository.saveAll(persistableBatteryEntities))
                .willReturn(savedBatteryEntities);

        // THEN
        // Verify the business logic(s)
        then(batteryServiceImpl.saveBatteries(persistableBatteryEntities))
                .isInstanceOf(List.class)
                .hasOnlyElementsOfType(Battery.class)
                .satisfies(batteries -> assertAll(
                        "Check for the auto generated primary keys in the response",
                        () -> assertEquals(AUTO_GENERATED_ID1, savedBatteryEntities.get(0).getId()),
                        () -> assertEquals(AUTO_GENERATED_ID2, savedBatteryEntities.get(1).getId()),
                        () -> assertEquals(AUTO_GENERATED_ID3, savedBatteryEntities.get(2).getId())
                ));

        // VERIFY
        verify(batteryRepository, times(1)).saveAll(persistableBatteryEntities);
    }

    @Test
    void getBatteriesByCriteria_shouldReturnEmptyBatteryStatisticsObject_whenTheFetchOperation_isNull() {
        // GIVEN
        given(batteryRepository.findBatteriesByPostcodeBetweenOrderByName(postCodeRangeDto.getMinPostCode(), postCodeRangeDto.getMaxPostCode()))
                .willReturn(null);

        // THEN
        then(batteryServiceImpl.getSortedBatteryNamesWithStatisticByPostCodeRange(postCodeRangeDto))
                .isInstanceOf(BatteryStatisticDto.class)
                .satisfies(batteryStatisticDto -> assertAll(
                        "Check for the empty object in the response",
                        () -> assertTrue(batteryStatisticDto.getNames().isEmpty()),
                        () -> assertEquals(0, batteryStatisticDto.getTotalCapacity()),
                        () -> assertEquals(0, batteryStatisticDto.getAverageCapacity())
                ));

        // VERIFY
        verify(batteryRepository, times(1)).findBatteriesByPostcodeBetweenOrderByName(postCodeRangeDto.getMinPostCode(), postCodeRangeDto.getMaxPostCode());
        verifyNoInteractions(batteryStatisticService);
    }

    @Test
    void getBatteriesByCriteria_shouldReturnEmptyBatteryStatisticsObject_whenTheFetchOperation_isEmptyList() {
        // GIVEN
        given(batteryRepository.findBatteriesByPostcodeBetweenOrderByName(postCodeRangeDto.getMinPostCode(), postCodeRangeDto.getMaxPostCode()))
                .willReturn(Collections.emptyList());

        // THEN
        then(batteryServiceImpl.getSortedBatteryNamesWithStatisticByPostCodeRange(postCodeRangeDto))
                .isInstanceOf(BatteryStatisticDto.class)
                .satisfies(batteryStatisticDto -> assertAll(
                        "Check for the empty object in the response",
                        () -> assertTrue(batteryStatisticDto.getNames().isEmpty()),
                        () -> assertEquals(0, batteryStatisticDto.getTotalCapacity()),
                        () -> assertEquals(0, batteryStatisticDto.getAverageCapacity())
                ));

        // VERIFY
        verify(batteryRepository, times(1)).findBatteriesByPostcodeBetweenOrderByName(postCodeRangeDto.getMinPostCode(), postCodeRangeDto.getMaxPostCode());
        verifyNoInteractions(batteryStatisticService);
    }

    @Test
    void getBatteriesByCriteria_shouldReturn_batteryStatisticObject_withAlphabeticallyOrderedByName() {
        // GIVEN
        given(batteryStatisticService.calculateTotalWattCapacity(Mockito.any()))
                .willReturn(1);
        given(batteryStatisticService.calculateAverageWattCapacity(Mockito.any()))
                .willReturn(1);
        given(batteryRepository.findBatteriesByPostcodeBetweenOrderByName(postCodeRangeDto.getMinPostCode(), postCodeRangeDto.getMaxPostCode()))
                .willReturn(fetchedBatteriesByCriteriaSortedByName);

        // THEN
        then(batteryServiceImpl.getSortedBatteryNamesWithStatisticByPostCodeRange(postCodeRangeDto))
                .isInstanceOf(BatteryStatisticDto.class);

        // ASSERT: Alphabetical order for the name field
        final List<String> sortedNames = batteryServiceImpl.getSortedBatteryNamesWithStatisticByPostCodeRange(postCodeRangeDto)
                .getNames();

        assertThatList(sortedNames).isSorted();
    }
}
