/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;
import java.math.BigInteger;

public class InputVerifier {

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

    static boolean inputContainsNumbersOnly(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    //checks whether the numeral input is between values a(from) and b(to)
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

    static boolean numberIsPositive(double in) {
        return ((in >= 0));
    }

}
