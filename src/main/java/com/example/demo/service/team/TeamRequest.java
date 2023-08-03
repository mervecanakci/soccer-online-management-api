package com.example.demo.service.team;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
    @JsonIgnore
    private final Faker faker = new Faker();

    private int userId;

    @JsonIgnore
    public String getRandomTeamName() {
        return faker.team().name();
//        return faker.team().name();
    }

    @JsonIgnore
    private double teamValue = 5000000.0;

    @JsonIgnore
    public String getRandomCountry() {
        return faker.address().country();
    }


    //@Value("${team.value:5000000}")
    //  private double teamValue = 5000000.0; //todo: Takım değeri (oyuncu değerlerinin toplamı)

        /*
         @Override
    public CreateOrderResponse add(CreateOrderRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setPrice(request.getPrice());
        order.setQuantity(request.getQuantity());
        order.setCurrency(request.getCurrency());
        order.setId(0);
        order.setCustomer(mapper.map(customerService.getById(request.getCustomerId()), Customer.class));

        repository.save(order);
        productService.changeStatus(order.getProductId(), Status.UNAVAILABLE);

        CreateOrderResponse response = new CreateOrderResponse();
        response.setProductId(order.getProductId());
        response.setPrice(order.getPrice());
        response.setTotalPrice(getTotalPrice(order));
        response.setQuantity(order.getQuantity());
        response.setCurrency(order.getCurrency());
        response.setCustomerId(order.getCustomer().getId());
        response.setId(order.getId());
        return response;

         */
}
