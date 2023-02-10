package org.narawit.comledger.coreapi.contracts.option;

import jakarta.validation.constraints.NotBlank;

public record NetworkConnectorTypeRequest(@NotBlank String connector) {}
