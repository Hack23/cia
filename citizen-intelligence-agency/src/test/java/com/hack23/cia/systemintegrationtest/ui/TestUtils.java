package com.hack23.cia.systemintegrationtest.ui;

import org.passay.data.EnglishCharacterData;
import org.passay.generate.PasswordGenerator;
import org.passay.rule.CharacterRule;

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
        final CharacterRule[] rules = new CharacterRule[]{
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1)};
        return new PasswordGenerator(12, rules).generate().toString();
    }
}
