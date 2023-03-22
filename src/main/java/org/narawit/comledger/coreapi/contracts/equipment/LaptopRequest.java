package org.narawit.comledger.coreapi.contracts.equipment;

public record LaptopRequest(
		Long equipmentId,
		Integer manufactureId,
		String model,
		Long cpuId,
		Long ramId,
		String pointerDevice,
		String keyboard,
		Integer cdRomTypeId) {
}
