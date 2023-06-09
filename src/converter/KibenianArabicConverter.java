package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Kibenian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/31/2023
 */
public class KibenianArabicConverter {

    // A string that holds the number (Kibenian or Arabic) you would like to convert
    private final String number;

    /**
     * Constructor for the KibenianArabic class that takes a string. The string should contain a valid
     * Kibenian or Arabic numeral. See the assignment instructions for what constitutes a correct input
     * and what exceptions should be raised.
     *
     * @param number A string that represents either a Kibenian or Arabic number.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic integer that cannot be represented
     * in the Kibenian number system.
     * @throws MalformedNumberException Thrown if the value is an Kibenian number that does not conform
     * to the rules of the Kibenian number system or any other error in Arabic number input.
     */
    public KibenianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {
        // TODO check to see if the number is valid, then set it equal to the string
        int num = 0;

        if (number.contains(" ")) {
            throw new MalformedNumberException("Contained an Invalid Character");
        }

        boolean isArabic = true;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            isArabic = false;
        }

        if (isArabic) {
            if (num > 215999 || num <= 0) {
                throw new ValueOutOfBoundsException("Integer Value is out of Bounds");
            }
            if (number.contains(".") || number.charAt(0) == '0') {
                throw new MalformedNumberException("Integer Value incorrect format");
            }

        } else {

            int lCount = 0;
            int xCount = 0;
            int vCount = 0;
            int iCount = 0;
            int _Count = 0;
            int valCount = 0;
            int totalCount = 0;
            char lastChar = ' ';
            boolean isValid = true;

            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == '_') {
                    lCount = 0;
                    xCount = 0;
                    vCount = 0;
                    iCount = 0;
                    valCount = 0;
                    lastChar = '_';
                    _Count++;
                } else if (number.charAt(i) == 'L') {
                    lCount++;
                    valCount += 50;
                    totalCount += 50;

                    if (lastChar == ' ' || lastChar == '_') {
                        lastChar = 'L';
                    } else {
                        isValid = false;
                    }

                } else if (number.charAt(i) == 'X') {
                    xCount++;
                    valCount += 10;
                    totalCount += 10;

                    if (lastChar == ' ' || lastChar == 'L' || lastChar == 'X' || lastChar == '_') {
                        lastChar = 'X';
                    } else {
                        isValid = false;
                    }

                } else if (number.charAt(i) == 'V') {
                    vCount++;
                    valCount += 5;
                    totalCount += 5;
                    if (lastChar == ' ' || lastChar == 'L' || lastChar == 'X' || lastChar == '_') {
                        lastChar = 'V';
                    } else {
                        isValid = false;
                    }

                } else if (number.charAt(i) == 'I') {
                    iCount++;
                    valCount += 1;
                    totalCount += 1;

                    if (lastChar == ' ' || lastChar == 'L' || lastChar == 'X' || lastChar == 'V' || lastChar == 'I' || lastChar == '_') {
                        lastChar = 'I';
                    } else {
                        isValid = false;
                    }

                } else {
                    isValid = false;
                }

                if (lCount > 1 || xCount > 4 || vCount > 1 || iCount > 4 || _Count > 2 || valCount > 59) {
                    isValid = false;
                }
            }
            if(totalCount == 0){
                isValid = false;
            }

            if (!isValid) {
                throw new MalformedNumberException("Incorrect Kibenian Format");
            }
        }

        if (number.contains(" ")) {
            throw new MalformedNumberException("Number cannot contain space");
        }
        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        int firstSet = -1;
        int firstSetMultiplicity = 0;
        int secondSet = -1;
        int secondSetMultiplicity = 0;
        int thirdSetMultiplicity = 0;
        int returnNumber = 0;
        if(number.contains("_")) {
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == '_') {
                    firstSet = i;
                    i = number.length();
                }
            }
            for (int i = 0; i < firstSet; i++) { //sort through numbers before the first _
                if (number.charAt(i) == 'L') {        //add 50 if number is an L to first set
                    firstSetMultiplicity += 50;
                } else if (number.charAt(i) == 'X') { //add 10 if number is an X to first set
                    firstSetMultiplicity += 10;
                } else if (number.charAt(i) == 'V') { //add 5 if number is a V to first set
                    firstSetMultiplicity += 5;
                } else if (number.charAt(i) == 'I') { //add 1 if number is an I to first set
                    firstSetMultiplicity += 1;
                }
            }
                for (int i = firstSet + 1; i < number.length(); i++) {
                    if (number.charAt(i) == '_') {
                        secondSet = i;
                        i = number.length();
                    } else secondSet = number.length();
                }
                for (int i = firstSet + 1; i < secondSet; i++) {
                    if (number.charAt(i) == 'L') {        //add 50 if number is an L to second set
                        secondSetMultiplicity += 50;
                    } else if (number.charAt(i) == 'X') { //add 10 if number is an X to second set
                        secondSetMultiplicity += 10;
                    } else if (number.charAt(i) == 'V') { //add 5 if number is a V to second set
                        secondSetMultiplicity += 5;
                    } else if (number.charAt(i) == 'I') { //add 1 if number is an I to second set
                        secondSetMultiplicity += 1;
                    }
                }
                if (secondSet != number.length()) {
                    for (int i = secondSet + 1; i < number.length(); i++) {
                        if (number.charAt(i) == 'L') {        //add 50 if number is an L to third set
                            thirdSetMultiplicity += 50;
                        } else if (number.charAt(i) == 'X') { //add 10 if number is an X to third set
                            thirdSetMultiplicity += 10;
                        } else if (number.charAt(i) == 'V') { //add 5 if number is a V to third set
                            thirdSetMultiplicity += 5;
                        } else if (number.charAt(i) == 'I') { //add 1 if number is an I to third set
                            thirdSetMultiplicity += 1;
                        }
                    }
                    returnNumber = (firstSetMultiplicity * 3600) + (secondSetMultiplicity * 60) + thirdSetMultiplicity;
                    return returnNumber;
                } else returnNumber = (firstSetMultiplicity * 60) + secondSetMultiplicity;
                return returnNumber;
        }
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == 'L') {        //add 50 if number is an L to third set
                thirdSetMultiplicity += 50;
            } else if (number.charAt(i) == 'X') { //add 10 if number is an X to third set
                thirdSetMultiplicity += 10;
            } else if (number.charAt(i) == 'V') { //add 5 if number is a V to third set
                thirdSetMultiplicity += 5;
            } else if (number.charAt(i) == 'I') { //add 1 if number is an I to third set
                thirdSetMultiplicity += 1;
            }
        }
        return thirdSetMultiplicity;
    }

    /**
     * Converts the number to an Kibenian numeral or returns the current value if it is already in the Kibenian form.
     *
     * @return A Kibenian value
     */
    public String toKibenian() {

        //Gets the number as an integer
        int num = Integer.parseInt(number);

        //Represents the different sections seperated by underscores in decimal
        int[] nums = new int[3];
        nums[0] = 0;
        nums[1] = 0;
        nums[2] = 0;

        //Represents the different sections seperated by underscores in Kibenian
        String[] stringNums = new String[3];
        stringNums[0] = "";
        stringNums[1] = "";
        stringNums[2] = "";

        //Gets the first section
        if (num / 3600 > 0) {
            nums[0] = num / 3600;
        }
        num = num % 3600;

        //Gets the second section
        if (num / 60 > 0) {
            nums[1] = num/60;
        }

        num = num % 60;

        //Gets the third section
        nums[2] = num;

        System.out.println("1: " + nums[0] + "2: " + nums[1] + "3: " + nums[2]);

        for (int i = 0; i < 3; i++) {
            int tempNum = nums[i];
            if (tempNum / 50 > 0) {
                stringNums[i] += "L";
            }
            tempNum %= 50;

            for (int j = 0; j < tempNum / 10; j++) {
                stringNums[i] += "X";
            }
            tempNum %= 10;

            if (tempNum / 5 > 0) {
                stringNums[i] += "V";
            }
            tempNum %= 5;

            for (int j = 0; j < tempNum; j++) {
                stringNums[i] += "I";
            }
        }
        String answer = "";
        if (nums[0] != 0) {
            answer += stringNums[0] + "_";
            if (nums[1] == 0) {
                answer += "_";
            }
        }
        if (nums[1] != 0) {
            answer += stringNums[1] + "_";
        }
        if (nums[2] != 0) {
            answer += stringNums[2];
        }
        return answer;
    }

}
