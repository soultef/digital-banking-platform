package com.bank.auth_service.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private User buildValidUser() {
        User user = new User();
        user.setFirstName("Mamo");
        user.setLastName("Wolde");
        user.setEmail("mamo@xyz.com");
        user.setPhone("+12345678901");
        user.setBirthDate(LocalDate.of(1995, 5, 10));
        return user;
    }

    private UserAddress buildValidUserAddress() {
        UserAddress address = new UserAddress();
        address.setStreetName("Main St");
        address.setStreetNumber("123");
        address.setUnit("12A");
        address.setCity("Dallas");
        address.setState("TX");
        address.setZipCode("75001");
        address.setCounty("Dallas");
        address.setCountry("USA");
        return address;
    }

    @Test
    void shouldPersistUserWithGeneratedIdAndTimestamps() {
        User user = buildValidUser();

        User savedUser = entityManager.persistFlushFind(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getCreatedAt()).isNotNull();
        assertThat(savedUser.getUpdatedAt()).isNotNull();
    }

    @Test
    void shouldUpdateUpdatedAtWhenUserIsModified() {
        User user = buildValidUser();
        User savedUser = entityManager.persistAndFlush(user);

        var originalUpdatedAt = savedUser.getUpdatedAt();

        savedUser.setFirstName("Updated");
        entityManager.persistAndFlush(savedUser);

        assertThat(savedUser.getUpdatedAt()).isAfterOrEqualTo(originalUpdatedAt);
    }

    @Test
    void shouldCascadePersistAddresses() {
        User user = buildValidUser();
        UserAddress address = buildValidUserAddress();

        user.addAddress(address);

        User savedUser = entityManager.persistFlushFind(user);

        assertThat(savedUser.getAddresses()).hasSize(1);
    }

    @Test
    void shouldRemoveOrphanAddressWhenDetachedFromUser() {
        User user = buildValidUser();
        UserAddress address = buildValidUserAddress();

        user.addAddress(address);
        User savedUser = entityManager.persistAndFlush(user);

        UUID addressId = address.getId();

        savedUser.removeAddress(address);
        entityManager.persistAndFlush(savedUser);

        UserAddress removed =
                entityManager.find(UserAddress.class, addressId);

        assertThat(removed).isNull();
    }
}
