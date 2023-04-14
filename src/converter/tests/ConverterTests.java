package converter.tests;

import converter.KibenianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the KibenianArabicConverter class.
 */
public class ConverterTests {

    @Test
    public void ArabicToKibenianSampleTestOneSet() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("1");
        assertEquals(converter.toKibenian(), "I");
    }

    @Test
    public void ArabicToKibenianSampleTestTwoSet() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("61");
        assertEquals(converter.toKibenian(), "I_I");
    }

    @Test
    public void ArabicToKibenianSampleTestDifferentLetters() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("75316");
        assertEquals(converter.toKibenian(), "XX_LV_XVI");
    }
    @Test
    public void ArabicToKibenianSampleTestTwoFarSet() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("3601");
        assertEquals(converter.toKibenian(), "I__I");
    }

    @Test
    public void ArabicToKibenianSampleTestThreeSet() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("3661");
        assertEquals(converter.toKibenian(), "I_I_I");
    }
    @Test
    public void ArabicToKibenianSampleTestOneGap() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("60");
        assertEquals(converter.toKibenian(), "I_");
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    // TODO Add more test cases

    @Test
    public void KibenianToArabicSampleTestNo_() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
    }

    @Test
    public void KibenianToArabicSampleTestOne_() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("V_I");
        assertEquals(converter.toArabic(), 301);
    }
    @Test
    public void KibenianToArabicSampleTestTwo_() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("I_I_I");
        assertEquals(converter.toArabic(), 3661);
    }
    @Test
    public void KibenianToArabicSampleTestGapAfter_() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("I_V_");
        assertEquals(converter.toArabic(), 3900);
    }

   @Test
    public void MalformedNumberException2V() throws MalformedNumberException, ValueOutOfBoundsException {
        Exception exception = assertThrows(MalformedNumberException.class, () -> {
           new KibenianArabicConverter("VV_L_VI");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Incorrect Kibenian Format";
        assertTrue(actualMessage.contains(expectedMessage));
    }
   @Test
    public void MalformedNumberException2L() throws MalformedNumberException, ValueOutOfBoundsException {
        Exception exception = assertThrows(MalformedNumberException.class, () -> {
           new KibenianArabicConverter("XXXX_LL_VI");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Incorrect Kibenian Format";
        assertTrue(actualMessage.contains(expectedMessage));
    }
   @Test
    public void MalformedNumberException5I() throws MalformedNumberException, ValueOutOfBoundsException {
        Exception exception = assertThrows(MalformedNumberException.class, () -> {
           new KibenianArabicConverter("XXX_L_VIIIII");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Incorrect Kibenian Format";
        assertTrue(actualMessage.contains(expectedMessage));
    }
   @Test
    public void MalformedNumberExceptionOver59() throws MalformedNumberException, ValueOutOfBoundsException {
        Exception exception = assertThrows(MalformedNumberException.class, () -> {
           new KibenianArabicConverter("LX_L_VI");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Incorrect Kibenian Format";
        assertTrue(actualMessage.contains(expectedMessage));
    }
   @Test
    public void MalformedNumberExceptionfalseOrder1() throws MalformedNumberException, ValueOutOfBoundsException {
        Exception exception = assertThrows(MalformedNumberException.class, () -> {
           new KibenianArabicConverter("XXL_L_VI");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Incorrect Kibenian Format";
        assertTrue(actualMessage.contains(expectedMessage));
    }
    

    @Test
    public void MalformedNumberException5X() throws MalformedNumberException, ValueOutOfBoundsException {
        Exception exception = assertThrows(MalformedNumberException.class, () -> {
           new KibenianArabicConverter("XXXXX_L_VI");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Incorrect Kibenian Format";
        assertTrue(actualMessage.contains(expectedMessage));
    }

}