package com.prebeesh1427.movienameserviceprovider.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {

    @Test
    void testPositiveIsValidSearchText() {
        assertTrue(ValidationUtil.isValidSearchText("titanic"));
    }

    @Test
    void testNegativeIsValidSearchText() {
        assertFalse(ValidationUtil.isValidSearchText(""));
    }

    @Test
    void testNullIsValidSearchText() {
        assertFalse(ValidationUtil.isValidSearchText(null));
    }

    @Test
    void testPositiveisValidCountryCode() {
        assertTrue(ValidationUtil.isValidCountryCode("ca"));
    }

    @Test
    void testNegativeisValidCountryCode() {
        assertFalse(ValidationUtil.isValidCountryCode("test"));
    }

    @Test
    void testNullIsValidCountryCode() {
        assertFalse(ValidationUtil.isValidCountryCode(null));
    }
}