package Roomchelin.roomchelin;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface escaperoomRepository extends JpaRepository<escaperoom, Long> {
}
