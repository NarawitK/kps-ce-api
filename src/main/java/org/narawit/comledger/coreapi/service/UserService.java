package org.narawit.comledger.coreapi.service;

import org.narawit.comledger.coreapi.contract.UserContract;
import org.narawit.comledger.coreapi.contract.UserRequest;
import org.narawit.comledger.coreapi.domain.User;
import org.narawit.comledger.coreapi.service.common.BaseService;

public interface UserService extends BaseService<UserContract, UserRequest, Long> {
	public User findByUsername(String username);
	public User addThenReturnEntity(UserRequest req);
}
