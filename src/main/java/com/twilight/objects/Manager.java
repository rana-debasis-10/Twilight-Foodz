package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
   @Id
   @MobileNumber
   private String mobNo;

   @NotNull
   @Column(unique = true)
   private Integer outletId;
}
