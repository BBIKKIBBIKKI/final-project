package com.wearei.finalsamplecode.api.store;

import com.wearei.finalsamplecode.domain.menu.service.DomainMenuService;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.store.service.DomainStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultStoreService {
    private final StoreRepository storeRepository;
    private final DomainStoreService domainStoreService;
    private final DomainMenuService domainMenuService;

    // 가게 다건 조회
    public List<Store> getAllStores() {

        return storeRepository.findAll();
    }

    // 가게 단건 조회 ( 메뉴도 같이 조회 )
    public Store getStore(Long storeId) {

        return storeRepository.findStoreWithMenus(storeId);
    }

    // 통합검색 (가게 + 메뉴)
    public List<Store> searchStoresOrMenus(String storeName, String menuName) {

        return storeRepository.searchStoresOrMenu(storeName, menuName);
    }


}
