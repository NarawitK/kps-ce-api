package org.narawit.comledger.coreapi.contracts.option;

import jakarta.validation.constraints.NotBlank;

public record ManufactureCatRequest(@NotBlank String category) {}
