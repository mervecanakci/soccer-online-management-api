package com.example.demo.repository;


import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // uniq olsun istediğimiz için:
    //bu sorguyu transfer de kullanıcaz caRDnUMBER UNİQ DİYE ONLA YAPTIK
    Payment findByTeamValue(double teamValue);


    //todo hata veriyordu yorumda *** düzeldi test etmedin ama
    boolean existsByUserIdAndTeamIdAndPlayerId(
            Integer userId, Integer teamId, Integer playerId);
//todo
}
