package org.javaacademy.company;

import org.javaacademy.human.Gender;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.profession.Task;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверяем работоспособность Компании")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTest {
    private static Company company = new Company("Test", 2000);

    @Test
    @DisplayName("Проверяем создание Компании")
    @Order(1)
    void createNewCompanyTest() {
        assertEquals(2000, company.getGeneralRate());
        assertEquals("Test", company.getName());
        assertNotNull(company.getCompletedTasksProgrammers());
        assertNotNull(company.getTimesheet());
        assertEquals(BigDecimal.ZERO, company.getTotalCoast());
        assertNull(company.getManager());
        assertNotNull(company.getProgrammers());
    }

    @Test
    @DisplayName("Проверяем добавление программиста")
    @Order(2)
    void addNewProgrammerTest() {
        Programmer programmer = new Programmer("Test1", "Test1", "Test1", Gender.MAN);
        assertDoesNotThrow(() -> company.addProgrammer(programmer));
        assertThrows(NullPointerException.class, () -> company.addProgrammer(null));
    }

    @Test
    @DisplayName("Проверяем Изменяемость полей")
    @Order(3)
    void setsTest() {
        Manager manager = new Manager("Тест", "Test", "Test", Gender.MAN);
        company.setManager(manager);
        assertEquals(manager, company.getManager());
        company.setTotalCoast(BigDecimal.valueOf(10_000));
        assertEquals(BigDecimal.valueOf(10_000), company.getTotalCoast());
    }

    @Test
    @DisplayName("Проверяем добавление менеджера")
    @Order(4)
    void addNewManagerTest() {
        Manager manager = new Manager("Test", "Test", "Test", Gender.MAN);
        assertDoesNotThrow(() -> company.addManager(manager));
        assertThrows(NullPointerException.class, () -> company.addManager(null));
    }

    @Test
    @DisplayName("Проверяем еженедельные выплаты")
    @Order(5)
    void payForWeekTest() {
        Programmer programmer = new Programmer("Test2", "Test2", "Test2", Gender.MAN);
        company.addProgrammer(programmer);
        company.getTimesheet().put(programmer, 10.00);
        assertEquals(BigDecimal.valueOf(20_000.0), company.payMoney(programmer));
    }

    @Test
    @DisplayName("Проверяем выполнение еженедельной работы")
    @Order(7)
    void doWorkTaskTest() {
        assertThrows(NullPointerException.class, () -> company.doWeekWork(null));
        Queue<Task> tasks = new ArrayDeque<>();
        tasks.add(new Task("Test", 10));
        assertDoesNotThrow(() -> company.doWeekWork(tasks));
    }

}