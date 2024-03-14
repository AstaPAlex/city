package org.javaacademy.civil.zags;

import org.javaacademy.human.Gender;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Проверяем работоспособность Гражданин")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CitizenTest {
    private static Citizen citizen = new Citizen("Test1", "Test2", "Test3", Gender.MAN);

    @Test
    @DisplayName("Проверяем создание гражданина")
    @Order(1)
    void createNewCitizenTest() {
        assertEquals(FamilyStatus.NOT_MARRIED, citizen.getFamilyStatus());
        assertNull(citizen.getMarriagePartner());
        assertThrows(NullPointerException.class,() ->
                new Citizen("Test", null, "Test", Gender.MAN));
        assertThrows(NullPointerException.class,() ->
                new Citizen(null, "Test", "Test", Gender.MAN));
        assertThrows(NullPointerException.class,() ->
                new Citizen("Test", "Test", null, Gender.MAN));
        assertThrows(NullPointerException.class,() ->
                new Citizen("Test", "Test", "Test", null));
        assertNotNull(citizen.getName());
        assertNotNull(citizen.getSurname());
        assertNotNull(citizen.getGender());
        assertNotNull(citizen.getPatronymic());
    }

    @Test
    @DisplayName("Проверяем изменяемость полей")
    @Order(2)
    void setsCitizenTest() {
        Citizen citizen2 = new Citizen("Test2", "Test2", "Test2", Gender.WOMAN);
        citizen.setMarriagePartner(citizen2);
        assertEquals(citizen2, citizen.getMarriagePartner());
        citizen.setFamilyStatus(null);
        assertNull(citizen.getFamilyStatus());
    }

    @Test
    @DisplayName("Проверяем получение полей")
    @Order(3)
    void getsCitizenTest() {
        assertEquals("Test2 Test1 Test3", citizen.getFullName());
        assertNotNull(citizen.getChildren());
        assertEquals(Gender.MAN, citizen.getGender());
        assertNull(citizen.getFather());
        assertNull(citizen.getMother());
        assertEquals("Test1", citizen.getName());
        assertEquals("Test2", citizen.getSurname());
        assertEquals("Test3", citizen.getPatronymic());
    }
  
}