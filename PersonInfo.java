package Lab3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class PersonInfo {
    public static void main(String [] args) throws IndexOutOfBoundsException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your surname, name, patronymic" +
        " and the date of birth in dd.mm.yyyy format");
        String [] info = scanner.nextLine().split(" ");

        String surname = info[0], name = info[1], patronymic = info[2];
        String birthday = info[3];

        int age = calculateAge(birthday);
        String yearEnding = getYearEnding(age);
        char sex = defineSex(patronymic);

        System.out.printf("%s %c.%c. %c %d %s%n", surname, name.charAt(0),
                patronymic.charAt(0), sex, age, yearEnding);
    }

    private static int calculateAge(String birthday){
        String [] parts = birthday.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        int age = 0;

        try{
            LocalDate date = LocalDate.of(year, month, day);
            age = Period.between(date, LocalDate.now()).getYears();
        }
        catch (DateTimeException e){
            System.out.println("Something went wrong. Try again");
        }
        return age;
    }

    private static String getYearEnding(int age){
        if (age % 10 == 1 && age != 11) return "год";
        else if ((age % 10 == 2 || age % 10 == 3 || age % 10 == 4) && (age < 10 || age > 20)) return "года";
        else return "лет";
    }

    private static char defineSex(String patronymic){
        String end = patronymic.substring(Math.max(0, patronymic.length() - 2));
        if (end.equalsIgnoreCase("на")) return 'Ж';
        return 'М';
    }
}
