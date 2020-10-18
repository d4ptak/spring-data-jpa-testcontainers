package com.d4ptak.springdatajpatestcontainers.user;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "public__user")
@SequenceGenerator(name = "seq_user", sequenceName = "public__user_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(generator = "seq_user", strategy = SEQUENCE)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    public static User create(final String username) {
        final User user = new User();
        user.username = username;
        return user;
    }

}


