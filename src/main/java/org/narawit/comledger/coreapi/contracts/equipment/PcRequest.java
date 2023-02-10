package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record PcRequest(
		@NotBlank
		Long equipmentId,
		@NotBlank
		Integer mainboardManufactureid,
		@NotBlank
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
		) {

}
