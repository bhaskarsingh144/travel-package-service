package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;
import com.tech.nymble.travelpackageservice.repository.TravelPackageQueryRepository;
import com.tech.nymble.travelpackageservice.repository.TravelPackageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelPackageServiceImplTest {


    @InjectMocks
    private TravelPackageServiceImpl travelPackageService;
    @Mock
    private TravelPackageRepository travelPackageRepository;
    @Mock
    private TravelPackageQueryRepository travelPackageQueryRepository;

    @Test
    void testGetAllTravelPackages() {
        List<TravelPackage> expectedTravelPackages = Arrays.asList(
                new TravelPackage("1", "Package 1", 10, 50, new ArrayList<>(), new ArrayList<>()),
                new TravelPackage("2", "Package 2", 15, 50, new ArrayList<>(), new ArrayList<>())
        );

        when(travelPackageRepository.findAll()).thenReturn(expectedTravelPackages);

        List<TravelPackage> actualTravelPackages = travelPackageService.getAllTravelPackages();

        verify(travelPackageRepository, times(1)).findAll();
        assertEquals(expectedTravelPackages, actualTravelPackages);
    }

    @Test
    void testGetTravelPackageById() {
        String packageId = "1";
        TravelPackage expectedTravelPackage = new TravelPackage(packageId, "Package 1", 10, 50, new ArrayList<>(), new ArrayList<>());

        when(travelPackageRepository.findById(packageId)).thenReturn(Optional.of(expectedTravelPackage));

        TravelPackage actualTravelPackage = travelPackageService.getTravelPackageById(packageId);

        verify(travelPackageRepository, times(1)).findById(packageId);
        assertEquals(expectedTravelPackage, actualTravelPackage);
    }

    @Test
    void testSaveTravelPackage() {
        TravelPackage travelPackageToSave = new TravelPackage("1", "Package 1", 10, 50, new ArrayList<>(), new ArrayList<>());

        when(travelPackageRepository.save(travelPackageToSave)).thenReturn(travelPackageToSave);

        TravelPackage savedTravelPackage = travelPackageService.saveTravelPackage(travelPackageToSave);

        verify(travelPackageRepository, times(1)).save(travelPackageToSave);
        assertEquals(travelPackageToSave, savedTravelPackage);
    }

    @Test
    void testDeleteTravelPackageById() {
        String packageIdToDelete = "1";

        travelPackageService.deleteTravelPackageById(packageIdToDelete);

        verify(travelPackageRepository, times(1)).deleteById(packageIdToDelete);
    }

    @Test
    void testGetTravelPackagesByIds() {
        List<String> packageIds = Arrays.asList("1", "2");
        List<TravelPackage> expectedTravelPackages = Arrays.asList(
                new TravelPackage("1", "Package 1", 10, 50, new ArrayList<>(), new ArrayList<>()),
                new TravelPackage("2", "Package 2", 20, 50, new ArrayList<>(), new ArrayList<>())
        );

        when(travelPackageQueryRepository.getTravelPackagesByIds(packageIds)).thenReturn(expectedTravelPackages);

        List<TravelPackage> actualTravelPackages = travelPackageService.getTravelPackagesByIds(packageIds);

        verify(travelPackageQueryRepository, times(1)).getTravelPackagesByIds(packageIds);
        assertEquals(expectedTravelPackages, actualTravelPackages);
    }
}
