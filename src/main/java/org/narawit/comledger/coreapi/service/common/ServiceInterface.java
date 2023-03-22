package org.narawit.comledger.coreapi.service.common;

public interface ServiceInterface<E, C> {
	public C CreateContractInstance(E entity);
}
