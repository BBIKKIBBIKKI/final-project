package com.wearei.finalsamplecode.core.domain.menu.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DomainMenuServiceTest {
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DomainStoreService domainStoreService;
    @InjectMocks
    private DomainMenuService domainMenuService;

    @Test
    public void 메뉴_정상_생성() {
        // given
        Long userId = 1L;
        User user = new User(userId, UserRole.ROLE_OWNER);
        Long storeId = 1L;
        Store store = new Store(storeId);

        given(userRepository.findByIdOrThrow(user.getId())).willReturn(user);
        given(domainStoreService.checkStore(anyLong())).willReturn(store);

        Menu menu = new Menu(store,"메뉴",1000L, user, 100L);

        given(menuRepository.save(any())).willReturn(menu);
        // when
        Menu createdMenu = domainMenuService.createMenu(user.getId(), store.getId(),menu.getMenuName(),menu.getPrice(), menu.getInventory());

        assertNotNull(createdMenu);
        assertEquals(menu.getMenuName(), createdMenu.getMenuName());
        assertEquals(menu.getPrice(), createdMenu.getPrice());
        assertEquals(store, createdMenu.getStore());
        assertEquals(user, createdMenu.getUser());
    }

    @Test
    public void 존재하지_않는_유저(){
        Long invalidId = 1L;
        Long storeId = 1L;
        String menuName = "menu";
        Long price = 1000L;
        Long inventory = 100L;

        given(userRepository.findByIdOrThrow(invalidId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_USER));

        ApiException exception = assertThrows(ApiException.class, () -> {
            domainMenuService.createMenu(invalidId,storeId,menuName,price,inventory);
        });

        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 존재하지_않는_스토어(){
        Long userId = 1L;
        User user = new User(userId, UserRole.ROLE_OWNER);
        Long invalidStoreId = 999L;
        String menuName = "menu";
        Long price = 1000L;
        Long inventory = 100L;

        given(userRepository.findByIdOrThrow(user.getId())).willReturn(user);
        given(domainStoreService.checkStore(invalidStoreId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_STORE));

        ApiException exception = assertThrows(ApiException.class, () -> {
            domainMenuService.createMenu(user.getId(),invalidStoreId,menuName,price, inventory);
        });

        assertEquals(ErrorStatus._NOT_FOUND_STORE.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 메뉴_수정() {
        Long userId = 1L;
        User user = new User(userId, UserRole.ROLE_OWNER);
        Long storeId = 1L;
        Store store = new Store(storeId);
        Menu menu = new Menu(store,"메뉴",1000L, user,100L);

        given(userRepository.findByIdOrThrow(user.getId())).willReturn(user);
        given(domainStoreService.checkStore(anyLong())).willReturn(store);
        given(menuRepository.findById(menu.getId())).willReturn(Optional.of(menu));

        Menu updateMenu = domainMenuService.updateMenu(user.getId(),menu.getId(),store.getId(),"updateMenu",200L);

        assertNotNull(updateMenu);
    }
}
