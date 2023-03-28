package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PcRequest(
		@NotNull(message = "Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotNull(message = "Mainboard Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer mainboardManufactureid,
		@NotBlank(message = "Mainboard Model" + NOT_BLANK_VALIDATION_MSG)
		String mainboardModel,
		String pointerDevice,
		String keyboard,
		Long cpuId,
		Long ramId,
		Integer cdromTypeId,
		Integer psuManufactureId,
		Double psuPower,
		Integer psuUnitId,
		Long nicId,
		Long upsId,
		Long vgaId
		) {}