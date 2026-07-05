package com.petromirdzhunev.vehicle.infra.db.dao;

import org.springframework.stereotype.Component;

import com.petromirdzhunev.vehicle.domain.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehicleDao implements VehicleRepository {}
