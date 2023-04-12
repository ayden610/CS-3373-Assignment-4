package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

public class Main {


    public Main() {

    }

    public static void main(String[] args) throws MalformedNumberException, ValueOutOfBoundsException {
        String number = "I_X";
        KibenianArabicConverter KAC = new KibenianArabicConverter(number);
        KAC.toArabic();
    }

}
