package com.wearei.finalsamplecode.core.domain.menu.repository;

import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu, Long> {
}
