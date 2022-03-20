import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateAndTime {
    public static void main(String[] args) {
        Date startTime = new Date();
        long time = startTime.getTime();
        DateAndTime user = new DateAndTime();
        String birthday = user.scanner();
        int birthdayDate = user.setBirthdayDate(birthday);
        int birthdayMonth = user.setBirthdayMonth(birthday);
        long timeToBirthday = (user.getTime(birthdayDate, birthdayMonth, time) - startTime.getTime());
        user.dateCalculator(timeToBirthday);
    }

    public String scanner() {
        System.out.println("To find out how much is left to wait for your birthday, you only need to write the date and month of birth in this way - \"25.05.2005\" ");
        Scanner scanner = new Scanner(System.in);
        String dateOfBirthday = scanner.nextLine();
        return dateOfBirthday;
    }

    public int setBirthdayDate(String dateOfBirthday) {
        String[] arrayOfStrings = dateOfBirthday.split("\\.");
        int date = Integer.parseInt(arrayOfStrings[0]);
        return date;
    }

    public int setBirthdayMonth(String dateOfBirthday) {
        String[] arrayOfStrings = dateOfBirthday.split("\\.");
        int month = Integer.parseInt(arrayOfStrings[1]);
        return month;
    }

    public long getTime(int birthdayDate, int birthdayMonth, long time) {
        Date endTime = new Date();
        endTime.setYear(endTime.getYear());
        endTime.setMonth(birthdayMonth - 1);
        endTime.setDate(birthdayDate);
        endTime.setHours(0);
        endTime.setMinutes(0);
        endTime.setSeconds(0);
        if (endTime.getTime() <= time)
            endTime.setYear(endTime.getYear() + 1);
        return endTime.getTime();
    }

    public long dateCalculator(long timeToBirthday) {
        Calendar calendar = Calendar.getInstance();
        int dayOfEachMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        long milliSeconds[] = {0,86400000L, 3600000L, 60000, 1000};
        int[] typesOfTime = {0, 0, 1, 0, 0};
        int i = 0;
        while (true) {
            if (i == 0) {
                if (timeToBirthday >= (dayOfEachMonth * milliSeconds[1])) {
                    timeToBirthday -= (dayOfEachMonth * milliSeconds[1]);
                    typesOfTime[i] += 1;
                    calendar.add(Calendar.MONTH, 1);
                    dayOfEachMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                } else {
                    i++;
                }
            } else {
                if (timeToBirthday >= milliSeconds[i]) {
                    timeToBirthday -= milliSeconds[i];
                    typesOfTime[i] += 1;
                }else{
                    i++;
                    if(i==5){
                        break;
                    }
                }
            }
        }
        System.out.println("To your birthday remains:\n " + typesOfTime[0] + " - month(s); \n " + typesOfTime[1] + " - day(s);\n " + typesOfTime[2] + " - hour(s);\n " + typesOfTime[3] + " - minute(s);\n " + typesOfTime[4] + " - second(s);");
        return 0;
    }
}
