package sk.upjs.ics.SwiPoistovna;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public class Verifier {

    public static final int SUCASNY_ROK = Calendar.getInstance().get(Calendar.YEAR);

    public static final int VEK_MAXIMALNY = 90;
    public static final int VEK_MINIMALNY = 15;

    public static final int DOBA_POISTENIA_MINIMALNA = 1;
    public static final int DOBA_POISTENIA_MAXIMALNA = 75;

    public static final int MINIMALNY_PLAT = 380;

    public static final int VEKOVY_LIMIT_KRITICKE_CHOROBY = 65;
    public static final int VEKOVY_LIMIT_HOSPITALIZACIA = 65;
    public static final int VEKOVY_LIMIT_DENNA_DAVKA_POCAS_PN = 65;
    public static final int VEKOVY_LIMIT_NEVYHNUTNA_LIECBA_URAZU = 65;

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

    /**
     *
     * Skontroluje, ci dany vek splna pozadovane minimum a neprekracuje limit.
     *
     *
     * @param rokNarodenia {@code String} rok narodenia, ktory bude
     * skontrolovany
     *
     * @return {@code true} , ak splna minimum a zaroven neprekracuje maximalny
     * povoleny vek, inac vrati {@code false}.
     */
    public static boolean skontrolujVek(String rokNarodenia) {
        // skontrolovat

        if (rokNarodenia.equals("") || Verifier.inputContainsNumbersOnly(rokNarodenia) == false) {
            return false;
        }

        int vek = -1;
        try {
            vek = SUCASNY_ROK - Integer.parseInt(rokNarodenia);
        } catch (NumberFormatException e) {
            return false;
        }

        if (Verifier.numberIsBetween(vek, VEK_MINIMALNY, VEK_MAXIMALNY)) {
            return true;
        }

        return false;
    }

    /**
     *
     * Skontroluje, ci dany vek splna pozadovane minimum a neprekracuje limit.
     *
     *
     * @param rokNarodenia {@code int} rok narodenia, ktory bude skontrolovany
     *
     * @return {@code true} , ak splna minimum a zaroven neprekracuje maximalny
     * povoleny vek, inac vrati {@code false}.
     */
    public static boolean skontrolujVek(int rokNarodenia) {

        if (Verifier.numberIsBetween(SUCASNY_ROK - rokNarodenia, VEK_MINIMALNY, VEK_MAXIMALNY)) {
            return true;
        }

        return false;
    }

    /**
     *
     * Skontroluje, ci dany vek splna pozadovane minimum a neprekracuje vlastny
     * stanoveny limit.
     *
     * @param rokNarodenia {@code int} rok narodenia, ktory bude skontrolovany
     * @param customMax {@code customMax} vlastny limit, ktory nema vek
     * prekrocit
     *
     * @return {@code true} , ak splna minimum a zaroven neprekracuje vlastny
     * stanoveny limit, inac vrati {@code false}.
     */
    public static boolean skontrolujVek(int rokNarodenia, int customMax) {

        if (Verifier.numberIsBetween(SUCASNY_ROK - rokNarodenia, VEK_MINIMALNY, customMax)) {
            return true;
        }

        return false;
    }

    /**
     *
     * Skontroluje, ci dana doba poistenia splna pozadovane minimum a
     * neprekracuje maximalny limit.
     *
     * @param dobaPoistenia {@code String} doba poistenia, ktora bude
     * skontrolovana
     *
     * @return {@code true} , ak splna minimum a zaroven neprekracuje maximalny
     * stanoveny limit, inac vrati {@code false}.
     */
    public static boolean skontrolujDobuPoistenia(String dobaPoistenia) {

        if (dobaPoistenia.equals("") || Verifier.inputContainsNumbersOnly(dobaPoistenia) == false) {
            return false;
        }

        try {
            if (Verifier.numberIsBetween(Integer.parseInt(dobaPoistenia), DOBA_POISTENIA_MINIMALNA, DOBA_POISTENIA_MAXIMALNA)) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return false;
    }

    /**
     *
     * Skontroluje, ci plat je validny a otestuje, ci uzivatel zadal sumu rovnu
     * aspon minimalnej mzde.
     *
     * @param plat {@code String} plat na skontrolovanie
     *
     * @return {@code true} , ak plat nie je zaporny, neobsahuje znaky, a je
     * celociselny. Inak vrati {@code false}.
     */
    public static boolean skontrolujPlat(String plat) {
        if (plat.equals("") || Verifier.inputContainsNumbersOnly(plat) == false) {
            return false;
        }

        try {
            if (Integer.parseInt(plat) < MINIMALNY_PLAT) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean skontrolujPoistnuSumu(String suma) {
        return true;
    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre smrt urazom podla udajov
     * od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatSmrtUrazom() {
        // nie su ziadne obmedzenia => true
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia()));

    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre trvale nasledky podla
     * udajov od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatTrvaleNasledky() {
        // nie su ziadne obmedzenia => true
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia()) && !Manager.INSTANCE.getPripoistenia(2));

    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre trvale nasledky s
     * progresivnym zhrosovanim podla udajov od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatTrvaleNasledkyProg() {
        // nie su ziadne obmedzenia => true
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia()) && !Manager.INSTANCE.getPripoistenia(1));
    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre nevyhnutnu liecbu podla
     * udajov od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatNevyhnutnaLiecba() {

        // zistit z udajov z Managera
        //TODO
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia(), VEKOVY_LIMIT_NEVYHNUTNA_LIECBA_URAZU));
    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre praceneschopnost podla
     * udajov od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatPraceneschopnost() {
        // zistit z udajov z Managera
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia(), VEKOVY_LIMIT_DENNA_DAVKA_POCAS_PN) && !(Manager.INSTANCE.getRizikovaSkupina() == Manager.RizikovaSkupina.TRETIA));
    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre hospitalizaciu podla
     * udajov od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatHospitalizacia() {
        // zistit z udajov z Managera
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia(), VEKOVY_LIMIT_HOSPITALIZACIA));
    }

    /**
     *
     * Skontroluje, ci je mozne pridat pripoistenie pre kriticke choroby podla
     * udajov od triedy {@link Manager}.
     *
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatKritickeChoroby() {
        // zistit z udajov z Managera
        return (Verifier.skontrolujVek(Manager.INSTANCE.getRokNarodenia(), VEKOVY_LIMIT_KRITICKE_CHOROBY));
    }


    /*
     * [smrtUrazom, trvaleNasledky, trvaleNasledkyProg, nevyhnutnaLiecba, 
     *   praceneschopnost, hospitalizacia, kritickeChoroby]
     */
    /**
     *
     * Skontroluje, ci je mozne pridat dane pripoistenie podla udajov od triedy
     * {@link Manager}.
     *
     * @param i {@code int} vid nizsie:<br>
     * i = 1 skontroluje narok na pridanie pripoistenia smrt urazom <br>
     * i = 2 skontroluje narok na pridanie pripoistenia pre trvale nasledky<br>
     * i = 3 skontroluje narok na pridanie pripoistenia pre trvale nasledky s
     * progresivnym zhorsovanim<br>
     * i = 4 skontroluje narok na pridanie pripoistenia pre nevyhnutnu
     * liecbu<br>
     * i = 5 skontroluje narok na pridanie pripoistenia pre praceneschopnost<br>
     * i = 6 skontroluje narok na pridanie pripoistenia pre hospitalizaciu<br>
     * i = 7 skontroluje narok na pridanie pripoistenia pre kriticke choroby
     *
     * @return {@code true} ak ma klient narok na toto pripoistenie. Inak vrati
     * {@code false}.
     */
    public static boolean pridatPripoistenie(int i) {
        switch (i) {
            case 0:
                return Verifier.pridatSmrtUrazom();
            case 1:
                return Verifier.pridatTrvaleNasledky();
            case 2:
                return Verifier.pridatTrvaleNasledkyProg();
            case 3:
                return Verifier.pridatNevyhnutnaLiecba();
            case 4:
                return Verifier.pridatPraceneschopnost();
            case 5:
                return Verifier.pridatHospitalizacia();
            case 6:
                return Verifier.pridatKritickeChoroby();

            default:
                return false;
        }
    }
}
