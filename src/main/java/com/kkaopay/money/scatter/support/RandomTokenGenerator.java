package com.kkaopay.money.scatter.support;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomTokenGenerator implements TokenGenerationStrategy {

    private static final String EMPTY_STRING = "";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    private final int length;

    private RandomTokenGenerator(final int length) {
        this.length = length;
    }

    public static RandomTokenGenerator of(final int length) {
        return new RandomTokenGenerator(length);
    }

    @Override
    public String generate() {
        List<String> characters = generateCharacters();
        Collections.shuffle(characters);

        return characters.stream()
                .limit(length)
                .collect(Collectors.joining(EMPTY_STRING));
    }

    private List<String> generateCharacters() {
        return CHARACTERS.chars()
                .mapToObj(i -> (char)i)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
