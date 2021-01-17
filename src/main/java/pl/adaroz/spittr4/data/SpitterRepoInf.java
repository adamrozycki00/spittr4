package pl.adaroz.spittr4.data;

import pl.adaroz.spittr4.Spitter;

public interface SpitterRepoInf {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

}
