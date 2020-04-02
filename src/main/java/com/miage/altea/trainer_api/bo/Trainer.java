package com.miage.altea.trainer_api.bo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Data
public class Trainer {

    @Id
    private String name;

    @ElementCollection
    private List<Pokemon> team;

    @Column
    private String password;
}
