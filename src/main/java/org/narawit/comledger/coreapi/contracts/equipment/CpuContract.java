package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Cpu;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

public record CpuContract(
		Long id,
		Manufacture manufacture,
		String model,
		Short threadCount,
		Double clockSpeed,
		Unit clockSpeedUnit) {
	public CpuContract(Cpu cpu) {
		this(cpu.getId(), cpu.getManufacture(), cpu.getModel(), cpu.getThreadCount(), cpu.getClockSpeed(), cpu.getClockSpeedUnit());
	}
}
