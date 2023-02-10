package org.narawit.comledger.coreapi.repos;

import org.narawit.comledger.coreapi.domain.MaintenanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceHistoryRepo extends JpaRepository<MaintenanceHistory, Long> {

}
