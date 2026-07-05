package com.petromirdzhunev.vehicle.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petromirdzhunev.vehicle.domain.VehicleEntity;
import com.petromirdzhunev.vehicle.domain.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleEntity createVehicle(final VehicleEntity vehicle) {
		return null;
	}

	public void deleteVehicle(final Long id) {
	}

	public VehicleEntity vehicle(final Long id) {
		return null;
	}

	public List<VehicleEntity> listVehicles() {
		return List.of();
	}

	public VehicleEntity updateVehicle(final Long id, final VehicleEntity vehicle) {
		return null;
	}
}
