package org.narawit.comledger.coreapi.contracts.option;

import jakarta.validation.constraints.NotBlank;

public record PrinterTypeRequest(@NotBlank String type) {}