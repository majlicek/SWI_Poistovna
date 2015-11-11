package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Verifier {

    /**
     *
     * Checks whether the input string is a valid number e.g. -6.87. Character
     * '.' passes as decimal mark but ',' does not.
     *
     * @param input {@code String} to check
     *
     * @return {@code true} if input text consists of numbers only. else returns
     * {@code false}.
     */
    static boolean inputIsValidNumber(String input) {
        return input.matches("(-?\\d+(\\.\\d+)?)");
    }

    /**
     *
     * Checks whether the string input contains number characters 0-9 only.
     *
     * @param input {@code String} to check
     *
     * @return {@code true} if input text consists of 0-9 chars only. else
     * returns {@code false}.
     */
    static boolean inputContainsNumbersOnly(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Checks whether the input number is between values from-to.
     *
     *
     * @param input {@code BigDecimal} to check
     * @param from {@code double} the limit number
     * @param to {@code double} the limit number
     *
     * @return {@code true} if input number is between from-to numbers else
     * returns {@code false}.
     */
    static boolean numberIsBetween(BigDecimal input, double from, double to) {

        if (from > to) {
            double temp = from;
            from = to;
            to = temp;
        }

        BigDecimal a = new BigDecimal(from);
        BigDecimal b = new BigDecimal(to);

        if (input.compareTo(a) == -1) {
            return false;
        }
        if (input.compareTo(b) == 1) {
            return false;
        }
        return true;
    }

    /**
     *
     * Checks whether the input number is between values from-to.
     *
     *
     * @param input {@code BigDecimal} to check
     * @param from {@code BigDecimal} the limit number
     * @param to {@code BigDecimal} the limit number
     *
     * @return {@code true} if input number is between from-to numbers else
     * returns {@code false}.
     */
    static boolean numberIsBetween(BigDecimal input, BigDecimal from, BigDecimal to) {

        if (from.compareTo(to) == 1) {
            BigDecimal temp = from;

            from = to;
            to = temp;
        }

        if (input.compareTo(from) == -1) {
            return false;
        }
        if (input.compareTo(to) == 1) {
            return false;
        }
        return true;
    }

    /**
     *
     * Checks whether the input number is between values from-to.
     *
     *
     * @param input {@code double} to check
     * @param from {@code double} the limit number
     * @param to {@code double} the limit number
     *
     * @return {@code true} if input number is between from-to numbers else
     * returns {@code false}.
     */
    static boolean numberIsBetween(double input, double from, double to) {
        if (from > to) {
            double temp = from;
            from = to;
            to = temp;
        }

        if (input < from) {
            return false;
        }
        if (input > to) {
            return false;
        }

        return true;
    }

    /**
     *
     * Checks whether the input number is between values from-to.
     *
     *
     * @param input {@code int} to check
     * @param from {@code int} the limit number
     * @param to {@code int} the limit number
     *
     * @return {@code true} if input number is between from-to numbers else
     * returns {@code false}.
     */
    static boolean numberIsBetween(int input, int from, int to) {
        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }

        if (input < from) {
            return false;
        }
        if (input > to) {
            return false;
        }

        return true;
    }

    /**
     *
     * Checks whether the input number is between values from-to.
     *
     *
     * @param input {@code double} to check
     * @param from {@code int} the limit number
     * @param to {@code int} the limit number
     *
     * @return {@code true} if input number is between from-to numbers else
     * returns {@code false}.
     */
    static boolean numberIsBetween(double input, int from, int to) {
        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }

        if (input < from) {
            return false;
        }
        if (input > to) {
            return false;
        }

        return true;
    }

    /**
     *
     * Checks whether the input number is 0 or higher
     *
     *
     * @param input {@code double} to check
     *
     * @return {@code true} if input number is 0 or higher else returns
     * {@code false}.
     */
    static boolean numberIsPositive(double input) {
        return ((input >= 0));
    }

    public static boolean skontrolujVek(String text) {
        // skontrolovat
        return false;
    }

    public static boolean skontrolujDobuPoistenia(String text) {
        // skontrolovat
        return false;
    }

    public static boolean skontrolujPlat(String text) {
        // skontrolovat
        return false;
    }

    public static boolean pridatSmrtUrazom() {
        // nie su ziadne obmedzenia => true
        return true;
    }

    public static boolean pridatTrvaleNasledky() {
        // nie su ziadne obmedzenia => true
        return true;
    }

    public static boolean pridatTrvaleNasledkyProg() {
        // nie su ziadne obmedzenia => true
        return true;
    }

    public static boolean pridatNevyhnutnaLiecba() {
        // zistit z udajov z Managera
        return true;
    }

    public static boolean pridatPraceneschopnost() {
        // zistit z udajov z Managera
        return true;
    }

    public static boolean pridatHospitalizacia() {
        // zistit z udajov z Managera
        return true;
    }

    public static boolean pridatKritickeChoroby() {
        // zistit z udajov z Managera
        return true;
    }

}
