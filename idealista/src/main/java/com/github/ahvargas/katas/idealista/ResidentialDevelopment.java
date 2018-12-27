package com.github.ahvargas.katas.idealista;


import lombok.Value;

@Value(staticConstructor = "of")
class ResidentialDevelopment {
    private final int x;
    private final int y;
    private final int id;
}



