package org.javaacademy.company;

import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.profession.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверяем работоспособность Компании")
@ExtendWith(MockitoExtension.class)
class CompanyTest {
    @Mock
    Programmer programmer;
    @Mock
    Manager manager;
    @Mock
    Task task;

    @Test
    @DisplayName("Проверяем добавление программиста")
    void addNewProgrammerTest() {
        Company company = new Company("Test", 2000);
        assertDoesNotThrow(() -> company.addProgrammer(programmer));
        assertThrows(Exception.class, () -> company.addProgrammer(null));
    }

    @Test
    @DisplayName("Проверяем добавление менеджера")
    void addNewManagerTest() {
        Company company = new Company("Test", 2000);
        assertDoesNotThrow(() -> company.addManager(manager));
        assertThrows(Exception.class, () -> company.addManager(null));
    }

/*    @Test
    @DisplayName("Проверяем выполнение недельной работы")
    void doWorkTaskTest() {
        Company company = new Company("Test", 2000);
        Queue<Task> tasks = new ArrayDeque<>();
        tasks.add(task);
        assertThrows(Exception.class, () -> company.doWeekWork(tasks));
    }*/


}