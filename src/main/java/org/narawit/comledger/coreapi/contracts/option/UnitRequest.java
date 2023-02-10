package org.narawit.comledger.coreapi.contracts.option;

import jakarta.validation.constraints.NotBlank;

public record UnitRequest(@NotBlank String unit) {}
