package pl.adaroz.spittr4.data;

import org.springframework.stereotype.Repository;
import pl.adaroz.spittr4.Spitter;

import java.util.Random;

@Repository
public class SpitterRepository implements SpitterRepoInf {

    public Spitter save(Spitter spitter) {
        return new Spitter(spitter.getId() + 1, spitter.getUsername(), spitter.getPassword(), spitter.getFirstName(), spitter.getLastName());
    }

    public Spitter findByUsername(String username) {
        return new Spitter(new Random().nextLong() % 1000, username, "password", "John", "Smith");
    }

}
