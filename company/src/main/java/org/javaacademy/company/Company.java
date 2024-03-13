package org.javaacademy.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.javaacademy.profession.*;

@RequiredArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    @NonNull
    final String name;
    @NonNull
    final int generalRate;
    final Set<Programmer> programmers = new HashSet<>();
    final Map<Employee, Double> timesheet = new HashMap<>();
    final MultiMap<Programmer, Task> completedTasksProgrammers = new MultiValueMap<>();
    BigDecimal totalCoast = new BigDecimal(BigInteger.ZERO);
    Manager manager;

    public void addProgrammer(@NonNull Programmer programmer) {
        programmers.add(programmer);
        timesheet.put(programmer, hourZero());
        programmer.setRate(generalRate);
    }

    public void addManager(@NonNull Manager manager) {
        this.manager = manager;
        timesheet.put(manager, hourZero());
    }

    public void doWeekWork(@NonNull Queue<Task> tasks) {
        while (tasks.peek() != null) {
            doWorkTask(tasks);
        }
    }

    private void doWorkTask(@NonNull Queue<Task> tasks) {
        for (Programmer programmer : programmers) {
            Task task = tasks.poll();
            if (task == null) {
                return;
            }
            programmer.setDoneTask(task);
            completedTasksProgrammers.put(programmer, task);
            timesheet.put(
                    programmer,
                    timesheet.get(programmer) + task.getHourCount());
            timesheet.put(
                    manager,
                    timesheet.get(manager) + task.getHourCount() * 0.1);
        }
    }

    public void payForWeek() {
        timesheet.forEach((employee, money) -> totalCoast = totalCoast.add(payMoney(employee)));
    }

    private BigDecimal payMoney(Employee employee) {
        BigDecimal weeklyPayment = BigDecimal.valueOf(timesheet.get(employee) * employee.getRate());
        employee.setEarnedMoney(employee.getEarnedMoney().add(weeklyPayment));
        timesheet.put(employee, hourZero());
        return weeklyPayment;
    }

    public void showInfo() {
        System.out.printf("%s\nЗатраты: %.2f\n", name, totalCoast);
        completedTasksProgrammers
                .forEach((key, value) -> System.out.printf("%s - %s\n", key.getFullName(), value));
    }

    private Double hourZero() {
        return 0.00;
    }

}
