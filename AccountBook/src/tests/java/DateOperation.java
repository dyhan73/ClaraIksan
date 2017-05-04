package tests.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Locale;

/**
 * Created by Daeyoung Han on 2017. 5. 4..
 */
public class DateOperation {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString());
        System.out.println(LocalDate.now().plusYears(-5).getYear());
        System.out.println(LocalDate.now().plusYears(-5).getMonthValue());
        System.out.println(LocalDate.now().plusYears(-5).getDayOfMonth());

        System.out.println(LocalDateTime.now().toString());

        // 5년전 1월 1일 구하기
        LocalDate now = LocalDate.now();
        LocalDate startDate = LocalDate.of(now.plusYears(-5).getYear(), 1, 1);
        System.out.println(startDate.toString());
    }
}
