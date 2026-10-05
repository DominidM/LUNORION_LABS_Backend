package com.lunorion.labs.shared.domain;

import java.util.List;

public final class PageSizes {

    public static final List<Integer> ALLOWED = List.of(5, 10, 25, 50);
    public static final int DEFAULT = 10;

    private PageSizes() {
    }

    public static int normalize(Integer size) {
        if (size == null || !ALLOWED.contains(size)) {
            return DEFAULT;
        }
        return size;
    }
}
