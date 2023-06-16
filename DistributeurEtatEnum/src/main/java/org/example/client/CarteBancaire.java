package org.example.client;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString // for demo ;)
@RequiredArgsConstructor(staticName = "of")
public class CarteBancaire {
    @NonNull
    private String numero;

    @NonNull
    private int code;
}
