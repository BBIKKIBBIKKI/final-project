package com.wearei.finalsamplecode.domain.menu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuRequest {
    private Long storeId;
    private String menuName;
}
