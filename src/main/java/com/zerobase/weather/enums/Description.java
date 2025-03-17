package com.zerobase.weather.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;

@RequiredArgsConstructor
@Getter
public enum Description {
    SUCCESS("success"), FAIL("fail");

    private final String description;
}
