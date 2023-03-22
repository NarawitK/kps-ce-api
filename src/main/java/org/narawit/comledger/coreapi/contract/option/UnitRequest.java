package org.narawit.comledger.coreapi.contract.option;

import jakarta.validation.constraints.NotBlank;

public record UnitRequest(@NotBlank Integer unitTypeId, @NotBlank String unit) {}
