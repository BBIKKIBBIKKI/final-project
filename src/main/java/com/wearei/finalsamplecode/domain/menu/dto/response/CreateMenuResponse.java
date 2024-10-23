package com.wearei.finalsamplecode.domain.menu.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMenuResponse {
    private final String storeName;
    private final String menuName;
}
