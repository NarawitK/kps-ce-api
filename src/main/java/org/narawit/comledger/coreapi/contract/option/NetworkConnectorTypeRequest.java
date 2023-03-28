package org.narawit.comledger.coreapi.contract.option;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;

public record NetworkConnectorTypeRequest(
		@NotBlank(message = "Connector Name" + NOT_BLANK_VALIDATION_MSG) 
		String connector) {}