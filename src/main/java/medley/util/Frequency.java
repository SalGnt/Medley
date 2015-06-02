package medley.util;

/**
 * {@code Frequency} class that contains methods that should be used to
 * manipulate frequencies.
 *
 * @author Salvatore Gentile
 */
public final class Frequency {

    private static final double MIDI_MIN_FREQUENCY = 8.176;
    private static final double MIDI_MAX_FREQUENCY = 12543.854;
    private static final double PIANO_MIN_FREQUENCY = 27.5000;
    private static final double PIANO_MAX_FREQUENCY = 4186.01;

    /**
     * Scientific pitch.
     */
    public static final double A432 = 432.00;

    /**
     * French pitch.
     */
    public static final double A435 = 435.00;

    /**
     * International standard pitch.
     */
    public static final double A440 = 440.00;

    /**
     * Switzerland pitch.
     */
    public static final double A442 = 442.00;

    /**
     * Pitch used by German and Austrian Symphony Orchestras.
     */
    public static final double A443 = 443.00;

    /**
     * Pitch used by Berlin Philharmonic Orchestra.
     */
    public static final double A444 = 444.00;

    /**
     * Returns the frequency of the note which is distant the specified number
     * of semitones from the international standard pitch A440 pitch.
     *
     * <p>The frequency is computed using
     * {@link #getFrequency(int semitonesDistance, double pitch) getFrequency}
     * method.
     *
     * @param semitonesDistance a specified number of half steps between a
     *        {@code Note} and the A4 note.
     * @return the frequency of the note which is distant the specified number
     *         of semitones from the international standard pitch A440 pitch.
     * @see #getFrequency(int semitonesDistance, double pitch)
     */
    public static double getFrequency(int semitonesDistance) {
        return getFrequency(semitonesDistance, A440);
    }

    /**
     * Returns the frequency of the note which is distant the specified number
     * of semitones from the specified pitch.
     *
     * <p>The frequency is computed using the following formula:
     * $$ f = 2^\frac{n}{12} \cdot p $$
     * where \( n \) is the distance in semitones, and \( p \) a pitch.
     *
     * @param semitonesDistance a specified number of half steps between a
     *        {@code Note} and the A4 note.
     * @param pitch a specified pitch.
     * @return the frequency of the note which is distant the specified number
     *         of semitones from the specified pitch.
     */
    public static double getFrequency(int semitonesDistance, double pitch) {

        return Math.pow(2, (double) semitonesDistance / 12.0) * pitch;
    }

    /**
     * Returns the number of
     * <a href="https://en.wikipedia.org/wiki/Cent_(music)">cents</a> between a
     * reference frequency and a specified frequency.
     *
     * <p>The number of cents are computed using the following formula:
     * $$ n = 1200 \cdot log_2 \left( \frac{b}{a} \right) $$
     * where \( a \) is the reference frequency, and \( b \) the specified
     * frequency.
     *
     * @param reference a reference frequency.
     * @param frequency a specified frequency.
     * @return the number of cents between a reference frequency and a specified
     *         frequency.
     */
    public static double getCents(double reference, double frequency) {

        return 1200.0 * lb(frequency / reference);
    }

    /**
     * Returns the number of semitones between a reference frequency and a
     * specified frequency.
     *
     * <p>The number of semitones are computed using
     * {@link #getSemitones(double cents) getSemitones} method, whose argument
     * is computed using the
     * {@link #getCents(double reference, double frequency) getCents} method.
     *
     * @param reference a reference frequency.
     * @param frequency a specified frequency.
     * @return the number of semitones between a reference frequency and a
     *         specified frequency.
     * @see #getSemitones(double cents)
     * @see #getCents(double reference, double frequency)
     */
    public static int getSemitones(double reference, double frequency) {

        return getSemitones(getCents(reference, frequency));
    }

    /**
     * Returns the number of semitones corresponding to the specified number of
     * cents.
     *
     * @param cents a specified number of cents.
     * @return the number of semitones corresponding to the specified number of
     *         cents.
     */
    public static int getSemitones(double cents) {
        return (int) (Math.round(cents) / 100.0);
    }

    /**
     * Returns the MIDI note number corresponding to the specified frequency.
     *
     * <p>The MIDI note number is computed using the following formula:
     * $$ d = 69 + 12 \cdot \log_{2} \left( \frac{f}{440} \right) $$
     * where \( f \) is a valid frequency.
     *
     * @param frequency a specified frequency.
     * @return the MIDI note number corresponding to the specified frequency.
     * @throws IllegalArgumentException
     *         if {@code frequency < 8.176} or {@code frequency > 12543.854}
     * @see medley.util.Validator#frequency(double frequency, double min, double
     *      max)
     */
    public static int getMIDINumber(double frequency)
            throws IllegalArgumentException {
        Validator.frequency(frequency, MIDI_MIN_FREQUENCY, MIDI_MAX_FREQUENCY);

        return (int) Math.round(69.0 + 12.0 * lb(frequency / A440));
    }

    /**
     * Returns the MIDI note number corresponding to the specified frequency.
     *
     * <p>The frequency is computed using the following formula:
     * $$ f = 2^\frac{d - 69}{12} \cdot 440 $$
     * where \( d \) is a valid MIDI note number.
     *
     * @param midiNumber a specified MIDI note number.
     * @return the frequency corresponding to the specified MIDI note number.
     * @throws IllegalArgumentException
     *         if {@code midiNumber < 0} or {@code midiNumber > 127}
     * @see medley.util.Validator#midiNumber(int midiNumber)
     */
    public static double getMIDINumberFrequency(int midiNumber)
            throws IllegalArgumentException {
        Validator.midiNumber(midiNumber);

        return A440 * Math.pow(2, (midiNumber - 69.0) / 12.0);
    }

    /**
     * Returns the piano key number corresponding to the specified frequency.
     *
     * <p>The piano key number is computed using the following formula:
     * $$ n = 12 \cdot log_2 \left( \frac{f}{440} \right) $$
     * where \( f \) is a valid frequency.
     *
     * @param frequency a specified frequency.
     * @return the piano key number corresponding to the specified frequency.
     * @throws IllegalArgumentException
     *         if {@code frequency < 27.500} or {@code frequency > 4186.009}
     * @see medley.util.Validator#frequency(double frequency, double min, double
     *      max)
     */
    public static int getPianoKeyNumber(double frequency)
            throws IllegalArgumentException {
        Validator.frequency(frequency,
                            PIANO_MIN_FREQUENCY,
                            PIANO_MAX_FREQUENCY);

        return (int) Math.round(12.0 * lb(frequency / A440) + 49.0);
    }

    /**
     * Returns the frequency corresponding to the specified piano key number.
     *
     * <p>The frequency is computed using the following formula:
     * $$ f(n) = \left( \sqrt[12]{2} \right)^{n - 49} \cdot 440 $$
     * or, alternatively:
     * $$ f(n) = 2^\frac{n - 49}{12} \cdot 440 $$
     * where \( n \) is a valid piano key number.
     *
     * @param pianoKey a specified piano key number.
     * @return the frequency corresponding to the specified piano key number.
     * @throws IllegalArgumentException
     *         if {@code pianoKey < 1} or {@code pianoKey > 88}
     * @see medley.util.Validator#pianoKey(int pianoKey)
     */
    public static double getPianoKeyFrequency(int pianoKey)
            throws IllegalArgumentException {
        Validator.pianoKey(pianoKey);

        return Math.pow(2, ((double) pianoKey - 49.0) / 12.0) * A440;
    }

    /**
     * Returns the <i>binary logarithm</i> of the specified value.
     *
     * <p>The name of the method was chosen to reflect the ISO notation of the
     * binary logarithm.
     *
     * @param value a specified double.
     * @return the binary logarithm of the specified value.
     */
    private static double lb(double value) {
        return Math.log(value) / Math.log(2.0);
    }

}
