package com.petromirdzhunev.vehicles.infra.http.server.controller;

import java.util.List;

import com.petromirdzhunev.vehicles.infra.http.server.controller.model.VehicleCreationRequestPayload;
import com.petromirdzhunev.vehicles.infra.http.server.controller.model.VehicleCreationResponsePayload;
import com.petromirdzhunev.vehicles.infra.http.server.controller.model.VehicleResponsePayload;
import com.petromirdzhunev.vehicles.infra.http.server.controller.model.VehicleUpdateRequestPayload;

public class VehiclesController implements VehiclesApi {
	@Override
	public VehicleCreationResponsePayload createVehicle(final VehicleCreationRequestPayload vehicleCreationRequestPayload) {
		return null;
	}

	@Override
	public void deleteVehicle(final Long id) {

	}

	@Override
	public VehicleResponsePayload getVehicle(final Long id) {
		return null;
	}

	@Override
	public List<VehicleResponsePayload> listVehicles() {
		return List.of();
	}

	@Override
	public VehicleResponsePayload updateVehicle(final Long id,
			final VehicleUpdateRequestPayload vehicleUpdateRequestPayload) {
		return null;
	}
}
