package com.petromirdzhunev.vehicle.domain.repository;

import java.util.List;

import com.petromirdzhunev.vehicle.domain.VehicleEntity;

public interface VehicleRepository {

	VehicleEntity insertVehicle(VehicleEntity vehicle);

	VehicleEntity vehicle(Long vehicleId);

	List<VehicleEntity> vehicles();

	VehicleEntity updateVehicle(Long vehicleId, VehicleEntity vehicle);

	void deleteVehicle(Long vehicleId);

	void assertVehicleNotExists(String plate, String vin);
}
