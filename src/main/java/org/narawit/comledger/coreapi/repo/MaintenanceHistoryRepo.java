package org.narawit.comledger.coreapi.repo;

import org.narawit.comledger.coreapi.domain.MaintenanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceHistoryRepo extends JpaRepository<MaintenanceHistory, Long> {

}
