package com.petromirdzhunev.vehicles.infra.http.server.controller;

import java.util.List;

import com.petromirdzhunev.vehicles.app.service.VehiclesService;
import com.petromirdzhunev.vehicles.domain.VehicleEntity;
import com.petromirdzhunev.vehicles.infra.http.server.controller.mapper.VehiclePayloadMapper;
import com.petromirdzhunev.vehicles.infra.http.server.controller.model.VehicleRequestPayload;
import com.petromirdzhunev.vehicles.infra.http.server.controller.model.VehicleResponsePayload;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehiclesController implements VehiclesApi {

	private final VehiclesService vehiclesService;
	private final VehiclePayloadMapper vehiclePayloadMapper;

	@Override
	public VehicleResponsePayload createVehicle(final VehicleRequestPayload vehicleRequestPayload) {
		final VehicleEntity createdVehicle = vehiclesService.createVehicle(
				vehiclePayloadMapper.toVehicle(vehicleRequestPayload));
		return vehiclePayloadMapper.fromVehicle(createdVehicle);
	}

	@Override
	public void deleteVehicle(final Long id) {
		vehiclesService.deleteVehicle(id);
	}

	@Override
	public VehicleResponsePayload getVehicle(final Long id) {
		return vehiclePayloadMapper.fromVehicle(vehiclesService.vehicle(id));
	}

	@Override
	public List<VehicleResponsePayload> listVehicles() {
		return vehiclesService.listVehicles()
		                      .stream()
		                      .map(vehiclePayloadMapper::fromVehicle)
		                      .toList();
	}

	@Override
	public VehicleResponsePayload updateVehicle(final Long id, final VehicleRequestPayload vehicleRequestPayload) {
		final VehicleEntity updatedVehicle = vehiclesService.updateVehicle(id,
				vehiclePayloadMapper.toVehicle(vehicleRequestPayload));
		return vehiclePayloadMapper.fromVehicle(updatedVehicle);
	}
}
