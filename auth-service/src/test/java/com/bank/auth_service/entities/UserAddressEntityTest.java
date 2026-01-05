package com.bank.auth_service.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAddressEntityTest {
    @Autowired
    private TestEntityManager entityManager;

    // ---------- helpers ----------

    private User buildValidUser() {
        User user = new User();
        user.setFirstName("Mamo");
        user.setLastName("Wolde");
        user.setEmail("mamo@test.com");
        user.setPhone("+12345678901");
        user.setBirthDate(LocalDate.of(1995, 5, 10));
        return user;
    }

    private UserAddress buildValidAddress(User user) {
        UserAddress address = new UserAddress();
        address.setStreetNumber("123");
        address.setStreetName("Main St");
        address.setUnit("A1");
        address.setCity("Dallas");
        address.setState("TX");
        address.setZipCode("75001");
        address.setCounty("Dallas");
        address.setCountry("USA");
        address.setUser(user);
        return address;
    }

    // ---------- tests ----------

    @Test
    void shouldPersistUserAddressWithGeneratedIdAndTimestamps() {
        // GIVEN
        User user = entityManager.persistAndFlush(buildValidUser());
        UserAddress address = buildValidAddress(user);

        // WHEN
        UserAddress saved = entityManager.persistFlushFind(address);

        // THEN
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreated_at()).isNotNull();
        assertThat(saved.getUpdated_at()).isNotNull();
    }

    @Test
    void shouldUpdateUpdatedAtWhenAddressIsModified() {
        // GIVEN
        User user = entityManager.persistAndFlush(buildValidUser());
        UserAddress address = entityManager.persistAndFlush(buildValidAddress(user));

        LocalDateTime originalUpdatedAt = address.getUpdated_at();

        // WHEN
        address.setCity("Austin");
        entityManager.persistAndFlush(address);

        // THEN
        assertThat(address.getUpdated_at()).isAfter(originalUpdatedAt);
    }

    @Test
    void shouldFailWhenUserIsMissing() {

        UserAddress address = new UserAddress();
        address.setStreetNumber("123");
        address.setStreetName("Main St");
        address.setUnit("A1");
        address.setCity("Dallas");
        address.setState("TX");
        address.setZipCode("75001");
        address.setCounty("Dallas");
        address.setCountry("USA");

        assertThatThrownBy(() ->
                entityManager.persistAndFlush(address)
        ).isInstanceOf(Exception.class);
    }

    @Test
    void shouldEnforceNotNullConstraints() {
        // GIVEN
        User user = entityManager.persistAndFlush(buildValidUser());
        UserAddress address = new UserAddress();
        address.setUser(user);


        assertThatThrownBy(() ->
                entityManager.persistAndFlush(address)
        ).isInstanceOf(Exception.class);
    }
}
