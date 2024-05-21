// src/main/java/repositories/RequestHistoryRepository.java
package repositories;

import models.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {
}
