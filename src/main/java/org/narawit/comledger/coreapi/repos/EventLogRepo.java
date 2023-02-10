package org.narawit.comledger.coreapi.repos;

import org.narawit.comledger.coreapi.domain.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepo extends JpaRepository<EventLog, Long> {

}
