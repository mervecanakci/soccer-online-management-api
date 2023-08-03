package com.example.demo.service.player;


import com.example.demo.model.enums.Position;
import com.example.demo.model.enums.TransferState;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    private final Faker faker = new Faker();

    private int teamId;

    private double marketValue = 1000000.0;

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomCountry() {
        return faker.address().country();
    }

    public int getRandomAge() {
        return faker.number().numberBetween(18, 49);
    }

    public Position getRandomPosition() {
        return faker.options().option(Position.values());

    }
    public TransferState transferState;

}
