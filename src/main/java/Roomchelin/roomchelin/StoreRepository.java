package Roomchelin.roomchelin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStore_name(String storename);
}
