package course2;

public class L3_EnhancedSwitch {
    public static void main(String[] args) {

        L3_EnhancedSwitch obj1 = new L3_EnhancedSwitch();

        System.out.println("Enhanced switch case - multiline return: "+obj1.multiLineExample());

        EnumExample example2 = new EnumExample(Day.SATURDAY);

        System.out.println("Enhanced switch case - enum: "+example2.getActivity());

    }

    public String multiLineExample() {
        int score = 70;

        return switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> {
                yield "Grade C" +
                        "-Borderline" +
                        "Pass"; // returns value from the block
            }
            default -> "F";
        };
    }
}

enum Day { MONDAY, TUESDAY, WEDNESSDAY, THRUSDAY, FRIDAY, SATURDAY }

class EnumExample {
    Day today;

    EnumExample(Day today) {
        this.today = today;
    }

    public String getActivity(){

        return switch (today) {
            case MONDAY, TUESDAY, WEDNESSDAY, THRUSDAY -> "Work";
            case FRIDAY -> "Get ready to relax";
            case SATURDAY -> "Relax";
        };
    }

}