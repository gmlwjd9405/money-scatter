package com.kkaopay.money.scatter.support;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class RandomTokenGenerator implements TokenGenerationStrategy {

    private final int length;

    private RandomTokenGenerator(final int length) {
        this.length = length;
    }

    public static RandomTokenGenerator of(final int length) {
        return new RandomTokenGenerator(length);
    }

    @Override
    public String generate() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                .build();

        return generator.generate(this.length);
    }
}
