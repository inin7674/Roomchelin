package Roomchelin.roomchelin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EscaperoomRepository extends JpaRepository<Escaperoom, Long> {

    Escaperoom findByEscapestoreContaining (String param);
}
