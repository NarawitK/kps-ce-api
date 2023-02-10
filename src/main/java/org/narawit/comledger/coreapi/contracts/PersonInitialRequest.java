package org.narawit.comledger.coreapi.contracts;

import jakarta.validation.constraints.NotBlank;

public record PersonInitialRequest(@NotBlank String initial) {}