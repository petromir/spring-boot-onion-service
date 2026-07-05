package com.petromirdzhunev.vehicle.infra.http.server.controller;

import java.util.List;

import com.petromirdzhunev.vehicle.app.service.VehicleService;
import com.petromirdzhunev.vehicle.domain.VehicleEntity;
import com.petromirdzhunev.vehicle.infra.http.server.controller.mapper.VehiclePayloadMapper;
import com.petromirdzhunev.vehicle.infra.http.server.controller.model.VehicleRequestPayload;
import com.petromirdzhunev.vehicle.infra.http.server.controller.model.VehicleResponsePayload;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {

	private final VehicleService vehicleService;
	private final VehiclePayloadMapper vehiclePayloadMapper;

	@Override
	public VehicleResponsePayload createVehicle(final VehicleRequestPayload vehicleRequestPayload) {
		final VehicleEntity createdVehicle = vehicleService.createVehicle(
				vehiclePayloadMapper.toVehicle(vehicleRequestPayload));
		return vehiclePayloadMapper.fromVehicle(createdVehicle);
	}

	@Override
	public void deleteVehicle(final Long id) {
		vehicleService.deleteVehicle(id);
	}

	@Override
	public VehicleResponsePayload getVehicle(final Long id) {
		return vehiclePayloadMapper.fromVehicle(vehicleService.vehicle(id));
	}

	@Override
	public List<VehicleResponsePayload> listVehicles() {
		return vehicleService.listVehicles()
		                     .stream()
		                     .map(vehiclePayloadMapper::fromVehicle)
		                     .toList();
	}

	@Override
	public VehicleResponsePayload updateVehicle(final Long id, final VehicleRequestPayload vehicleRequestPayload) {
		final VehicleEntity updatedVehicle = vehicleService.updateVehicle(id,
				vehiclePayloadMapper.toVehicle(vehicleRequestPayload));
		return vehiclePayloadMapper.fromVehicle(updatedVehicle);
	}
}
