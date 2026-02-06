package com.twilight.components.database;

import com.twilight.types.OnBoarder;
import com.twilight.types.OnBoardingState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "onBoardings")
@NoArgsConstructor
@AllArgsConstructor
public class OnBoardingDetails {
    @Id
    private String id;


    @Enumerated(value = EnumType.STRING)
    OnBoardingState onBoardingState;

    @Enumerated(value = EnumType.STRING)
    OnBoarder type;

    public OnBoardingDetails(String id, OnBoarder type, OnBoardingState state) {
        this.id = id;
        this.onBoardingState = state;
        this.type = type;
    }

}
