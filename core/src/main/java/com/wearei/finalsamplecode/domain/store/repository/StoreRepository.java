package com.wearei.finalsamplecode.domain.store.repository;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT DISTINCT s FROM Store s " +
            "LEFT JOIN FETCH s.menus m " +
            "WHERE (:storeName IS NULL OR s.storeName LIKE CONCAT('%', :storeName, '%')) AND " +
            "(:menuName IS NULL OR m.menuName LIKE CONCAT('%', :menuName, '%'))")
    List<Store> searchStoresOrMenu(@Param("storeName") String storeName,
                                   @Param("menuName") String menuName);

    boolean existsByStoreNameAndGround(String storeName, Ground ground);
}
