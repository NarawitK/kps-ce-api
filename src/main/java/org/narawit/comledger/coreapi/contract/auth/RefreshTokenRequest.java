package org.narawit.comledger.coreapi.contract.auth;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
		@NotBlank(message = "Refresh Token" + NOT_BLANK_VALIDATION_MSG) 
		String refreshToken) {}