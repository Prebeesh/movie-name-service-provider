package com.prebeesh1427.movienameserviceprovider.utils;

import com.prebeesh1427.movienameserviceprovider.data.Country;
import org.springframework.util.StringUtils;

public final class ValidationUtil {
    private ValidationUtil() {}

    public static boolean isValidSearchText(String searchText) {
        return StringUtils.hasText(searchText);
    }

    public static boolean isValidCountryCode(String counrtyCode) {
        return Country.fromCode(counrtyCode).isPresent();
    }
}
