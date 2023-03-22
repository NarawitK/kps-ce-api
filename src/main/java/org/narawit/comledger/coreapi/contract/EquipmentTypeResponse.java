package org.narawit.comledger.coreapi.contract;

import jakarta.validation.constraints.NotBlank;

public record EquipmentTypeResponse(@NotBlank String name) {}