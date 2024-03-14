package org.javaacademy.civil.zags;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Проверяем работоспособность Компании")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WriterTypeRegistryTest {
    @Mock
    Citizen father;
    @Mock
    Citizen mother;
    @Mock
    LocalDate date;
    private WriterTypeRegistry writerTypeRegistry = new WriterTypeRegistry(LocalDate.now(),
            TypeRegistry.BIRTH_REGISTRATION, mother, father);

    @Test
    @DisplayName("Проверяем создание класса")
    void createNewWriterTypeRegistryTest() {
        assertThrows(NullPointerException.class, () -> new WriterTypeRegistry(
                null, TypeRegistry.BIRTH_REGISTRATION, mother, father));
        assertThrows(NullPointerException.class, () -> new WriterTypeRegistry(
                date, null, mother, father));
        assertThrows(NullPointerException.class, () -> new WriterTypeRegistry(
                date, TypeRegistry.BIRTH_REGISTRATION, null));
    }

    @Test
    @DisplayName("Проверяем гетеры")
    void getsTest() {
        assertNotNull(writerTypeRegistry.getTypeRegistry());
        assertNotNull(writerTypeRegistry.getDate());
        assertNotNull(writerTypeRegistry.getListCitizen());
    }

    @Test
    @DisplayName("Проверяем сравнение")
    void equalsTest() {
        WriterTypeRegistry writerTypeRegistry2 = new WriterTypeRegistry(LocalDate.now(),
                TypeRegistry.DIVORCE_REGISTRATION, mother, father);
        assertFalse(writerTypeRegistry.equals(writerTypeRegistry2));
    }

    @Test
    @DisplayName("Проверяем хешкод]")
    void hashcodeTest() {
        assertEquals(writerTypeRegistry.hashCode(), writerTypeRegistry.hashCode());
    }

}