
import org.example.Gender;
import org.example.exceptions.EqualsGenderParentsException;
import org.example.Citizen;
import org.example.CivilRegistry;
import org.example.FamilyStatus;


import java.time.LocalDate;


public class RunnerProm {
    public static void main(String[] args) {
        String nameCivilRegistry = null;
        if (args[0] != null && args[0].length() > 0) {
            nameCivilRegistry = args[0];
        } else {
            throw new RuntimeException("Отсутствует имя ЗАГС в args[0], требуется туда внести его");
        }

        CivilRegistry civilRegistry = new CivilRegistry(nameCivilRegistry);
        setReport(civilRegistry);
        civilRegistry.report();

    }

    public static void setReport(CivilRegistry civilRegistry) {
        Citizen ivan = new Citizen("Иван", "Иванов", "Иванович", Gender.MAN, FamilyStatus.NOT_WIFE);
        Citizen sveta = new Citizen("Светлана", "Павлова", "Николаевна", Gender.WOMAN, FamilyStatus.NOT_HUSBAND);
        Citizen kolya = new Citizen("Николай", "Кириллов", "Иосифович", Gender.MAN, FamilyStatus.NOT_WIFE);
        Citizen olga = new Citizen("Ольга", "Белова", "Александровна", Gender.WOMAN, FamilyStatus.NOT_HUSBAND);
        Citizen dmitry = new Citizen("Дмитрий", "Новиков", "Анатольевич", Gender.MAN, FamilyStatus.NOT_WIFE);
        Citizen nadya = new Citizen("Надежда", "Павлова", "Александровна", Gender.WOMAN, FamilyStatus.NOT_HUSBAND);

        civilRegistry.setRecordMarriageRegistry(dmitry, nadya, LocalDate.now());
        civilRegistry.setRecordMarriageRegistry(ivan, sveta, LocalDate.now());
        civilRegistry.setRecordMarriageRegistry(kolya, olga, LocalDate.now());
        try {
            Citizen stasChild = new Citizen(sveta.createChild("Стас", "Иванов", "Иванович", Gender.MAN, ivan));
            Citizen slavaChild = new Citizen(olga.createChild("Слава", "Кириллов", "Николаевич", Gender.MAN, kolya));
            Citizen elenaChild = new Citizen(sveta.createChild("Елена", "Иванова", "Ивановна", Gender.WOMAN, ivan));
            civilRegistry.setRecordBirthChildRegistry(stasChild, ivan, olga, LocalDate.now());
            civilRegistry.setRecordBirthChildRegistry(slavaChild, kolya, olga, LocalDate.now());
            civilRegistry.setRecordBirthChildRegistry(elenaChild, ivan, sveta, LocalDate.now());
        } catch (EqualsGenderParentsException e) {
            System.out.println(e.getMessage());
        }

        civilRegistry.setRecordDivorceRegistry(dmitry, nadya, LocalDate.now());
    }
}
