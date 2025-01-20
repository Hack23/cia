package com.hack23.cia.systemintegrationtest.ui;

import java.util.Arrays;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

/**
 * The Class TestUtils.
 */
public final class TestUtils {

    /**
     * Instantiates a new test utils.
     */
    private TestUtils() {}

    /**
     * Generate password.
     *
     * @return the string
     */
    public static String generatePassword() {
        final List<CharacterRule> rules = Arrays.asList(
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1));
        return new PasswordGenerator().generatePassword(12, rules);
    }
}
