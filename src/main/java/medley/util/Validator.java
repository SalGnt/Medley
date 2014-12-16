package medley.util;

import java.util.Locale;

/**
 * {@code Validator} class that contains methods used to check values accuracy.
 *
 * @author Salvatore Gentile
 */
public final class Validator {

    private static final int MIDI_MIN_NUMBER = 0;
    private static final int MIDI_MAX_NUMBER = 127;

    private static final int PIANO_MIN_KEY = 1;
    private static final int PIANO_MAX_KEY = 88;

    private static final int VOLUME_MIN_VALUE = 0;
    private static final int VOLUME_MAX_VALUE = 127;

    private static final int DOTS_MIN = 0;
    private static final int DOTS_MAX = 3;

    /**
     * Checks the specified frequency and throws an exception if it is not
     * between the specified minimum frequency value and the specified maximum
     * frequency value.
     *
     * @param frequency a specified frequency.
     * @param min a specified minimum frequency value.
     * @param max a specified maximum frequency value.
     */
    public static void frequency(double frequency, double min, double max)
            throws IllegalArgumentException {
        if (frequency < min || frequency > max) {
            StringBuilder e = new StringBuilder(80);

            e.append("Invalid frequency value! ");
            e.append("It must be a positive double between ");
            e.append(String.format(Locale.ENGLISH, "%1.2f", min));
            e.append(" and ");
            e.append(String.format(Locale.ENGLISH, "%1.2f", max));
            e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * Checks the specified MIDI note number and throws an exception if it is
     * not valid.
     *
     * @param midiNumber a specified MIDI note number.
     * @throws IllegalArgumentException
     *         if {@code midiNumber < 0} or {@code midiNumber > 127}
     */
    public static void midiNumber(int midiNumber)
            throws IllegalArgumentException {
        if (midiNumber < MIDI_MIN_NUMBER || midiNumber > MIDI_MAX_NUMBER) {
            StringBuilder e = new StringBuilder(80);

            e.append("Invalid MIDI note number value! ");
            e.append("It must be a positive integer between ");
            e.append(MIDI_MIN_NUMBER);
            e.append(" and ");
            e.append(MIDI_MAX_NUMBER);
            e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * Checks the specified piano key number and throws an exception if it is
     * not valid.
     *
     * @param pianoKey a specified piano key number.
     * @throws IllegalArgumentException
     *         if {@code pianoKey < 1} or {@code pianoKey > 88}
     */
    public static void pianoKey(int pianoKey) throws IllegalArgumentException {
        if (pianoKey < PIANO_MIN_KEY || pianoKey > PIANO_MAX_KEY) {
            StringBuilder e = new StringBuilder(80);

            e.append("Invalid piano key number value! ");
            e.append("It must be a positive integer between ");
            e.append(PIANO_MIN_KEY);
            e.append(" and ");
            e.append(PIANO_MAX_KEY);
            e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * Checks if the specified MIDI note number can be incremented by the
     * specified number of half steps; If not, it throws an exception.
     *
     * @param midiNumber a specified MIDI note number.
     * @param semitones a specified number of half steps.
     * @throws IllegalArgumentException
     *         if {@code midiNumber + semitones < 1} or
     *         {@code midiNumber + semitones > 88}
     */
    public static void transpose(int midiNumber, int semitones)
            throws IllegalArgumentException {
        int transposed = midiNumber + semitones;

        if (transposed < MIDI_MIN_NUMBER || transposed > MIDI_MAX_NUMBER) {
            StringBuilder e = new StringBuilder(80);

            e.append("Invalid semitones value! ");
            e.append("For this note it must be a value between -");
            e.append(midiNumber); e.append(" and +");
            e.append(127 - midiNumber); e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * Checks the specified volume value and throws an exception if it is not
     * valid.
     *
     * @param volume a specified volume value.
     */
    public static void volume(int volume) throws IllegalArgumentException {
        if (volume < VOLUME_MIN_VALUE || volume > VOLUME_MAX_VALUE) {
            StringBuilder e = new StringBuilder(80);

            e.append("Invalid volume value! ");
            e.append("It must be a positive integer between ");
            e.append(VOLUME_MIN_VALUE);
            e.append(" and ");
            e.append(VOLUME_MAX_VALUE);
            e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * Checks the specified number of dots and throws an exception if it is not
     * valid.
     *
     * @param dots a specified number of dots.
     */
    public static void dots(int dots) throws IllegalArgumentException {
        if (dots < DOTS_MIN || dots > DOTS_MAX) {
            StringBuilder e = new StringBuilder(70);

            e.append("Invalid dots number! ");
            e.append("It must be a positive integer between ");
            e.append(DOTS_MIN);
            e.append(" and ");
            e.append(DOTS_MAX);
            e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * Checks the specified semitones value and throws an exception if it is not
     * between the specified minimum semitones value and the specified maximum
     * semitones value.
     *
     * @param semitones a specified number of half steps.
     * @param min a specified minimum semitones value.
     * @param max a specified maximum semitones value.
     */
    public static void semitones(int semitones, int min, int max)
            throws IllegalArgumentException {
        if (semitones < min || semitones > max) {
            StringBuilder e = new StringBuilder(80);

            e.append("Invalid semitones value! ");
            e.append("It must be a positive integer between ");
            e.append(min);
            e.append(" and ");
            e.append(max);
            e.append(".");

            throw new IllegalArgumentException(e.toString());
        }
    }

}
