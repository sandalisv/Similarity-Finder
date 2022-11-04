package com.find.similarity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
public class Matches {
    String frequency;
    HashSet<String> similarWords;
}
