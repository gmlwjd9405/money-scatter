package com.kkaopay.money.scatter.support;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TokenGenerator {

    public static final int TOKEN_LENGTH = 3;
    private static final String EMPTY_STRING = "";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String generate() {
        List<String> characters = generateCharacters();
        Collections.shuffle(characters);

        return characters.stream()
                .limit(TOKEN_LENGTH)
                .collect(Collectors.joining(EMPTY_STRING));
    }

    private static List<String> generateCharacters() {
        return CHARACTERS.chars()
                .mapToObj(i -> (char)i)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
