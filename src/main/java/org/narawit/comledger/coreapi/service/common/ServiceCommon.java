package org.narawit.comledger.coreapi.service.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceCommon {
	public static <C, E> List<C> AddEntityToContractCollection(Iterable<E> iterableOfEntity, ServiceInterface<E, C> op) {
		List<C> contracts = new ArrayList<>();
		if(IsIterableHasElements(iterableOfEntity)) {
			for (E e : iterableOfEntity) {
				contracts.add(op.CreateContractInstance(e));
			}
		}
		return contracts;
	}
	
	public static <T> boolean IsIterableHasElements(Iterable<T> collection) {
		int resultSize = 0;
		if(collection instanceof Collection<T>) {
			resultSize = ((Collection<T>)collection).size();
		}
		if(resultSize > 0) { 
			return true;
		}
		else { 
			return false; 
		}
	}
}
