package org.javaacademy.civil.zags;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверяем работоспособность Компании")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CivilRegistryTest {
    @Mock
    Citizen mother;
    @Mock
    Citizen father;
    @Mock
    LocalDate date;
    private static CivilRegistry civilRegistry = new CivilRegistry("TEST");

    @Test
    @DisplayName("Проверяем создание Загса")
    @Order(1)
    void createNewCivil() {
        assertThrows(NullPointerException.class, () -> new CivilRegistry(null));
    }

    @Test
    @DisplayName("Проверяем запись рождения")
    @Order(2)
    void birthChildTest() {
        assertDoesNotThrow( () ->civilRegistry.birthChild(father, mother, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.birthChild(null, mother, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.birthChild(father, null, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.birthChild(father, mother, null));
    }

    @Test
    @DisplayName("Проверяем запись брака")
    @Order(3)
    void registrationMarriageTest() {
        assertThrows(NullPointerException.class, () -> civilRegistry.registrationMarriage(null, mother, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.registrationMarriage(father, null, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.registrationMarriage(father, mother, null));
        assertDoesNotThrow( () ->civilRegistry.registrationMarriage(father, mother, date));
    }

    @Test
    @DisplayName("Проверяем регистрацию развода")
    @Order(4)
    void registrationDivorceTest() {
        assertThrows(NullPointerException.class, () -> civilRegistry.registrationDivorce(null, mother, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.registrationDivorce(father, null, date));
        assertThrows(NullPointerException.class, () -> civilRegistry.registrationDivorce(father, mother, null));
        assertDoesNotThrow( () ->civilRegistry.registrationDivorce(father, mother, date));
    }

    @Test
    @DisplayName("Проверяем получение отчета")
    @Order(5)
    void getReportTest() {
        assertDoesNotThrow( () ->civilRegistry.getReport());
    }
}