package org.narawit.comledger.coreapi.contract.option;

import jakarta.validation.constraints.NotBlank;

public record NetworkConnectionTypeRequest(@NotBlank String connectionType) {}
