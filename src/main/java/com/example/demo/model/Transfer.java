package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private double teamValue;
    private String playerName;
    private String playerCountry;
    private double playerMarketValue = 1000000;
    private double  price;
    private LocalDateTime dateOfTransfer;
    private boolean isCompleted;
    private LocalDateTime endDate;

    //    @ManyToMany
//    @JoinTable(
//            name = "player_transfer",
//            joinColumns = @JoinColumn(name = "transfer_id"),
//            inverseJoinColumns = @JoinColumn(name = "player_id"))
//    private List<Player> players = new ArrayList<>();
//
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;



}

// todo:bonservis bedeli yeni kulüp tarafından ödenir