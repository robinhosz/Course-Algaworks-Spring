package com.algaworks.course.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Size(max = 60)
    private String nome;

    @Column(name = "fone")
    @NotBlank(message = "O telefone não pode ser vazio")
    @Size(max = 20)
    private String telefone;

    @NotBlank(message = "O email não pode ser vazio")
    @Email
    @Size(max = 255)
    private String email;

}
