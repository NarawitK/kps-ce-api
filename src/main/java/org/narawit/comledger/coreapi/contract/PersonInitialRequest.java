package org.narawit.comledger.coreapi.contract;

import jakarta.validation.constraints.NotBlank;

public record PersonInitialRequest(@NotBlank String initial) {}