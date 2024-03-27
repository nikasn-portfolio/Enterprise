package com.knits.enterprise.dto.security;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@SuperBuilder(toBuilder=true)
public class UserDto {

    @EqualsAndHashCode.Include
    private Long id;

    private String login;

    @ToString.Exclude
    private String password;

    @EqualsAndHashCode.Include
    private String firstName;

    @EqualsAndHashCode.Include
    private String lastName;

    private String email;

    @Builder.Default
    private Boolean active = true;
}