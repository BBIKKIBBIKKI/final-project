package com.wearei.finalsamplecode.domain.menu.repository;

import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
