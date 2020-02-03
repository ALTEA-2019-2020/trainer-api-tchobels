package com.miage.altea.trainer_api.bo;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Embeddable
@Data
public class Pokemon {

    private int pokemonTypeId;
    private int level;
}
