package medley.score;

import medley.Duration;
import medley.Duration.Value;
import medley.tone.Accidental;

/**
 * {@code Element} interface that models a musical score entity.
 *
 * @author Salvatore Gentile
 */
public interface Element {

    public static final Accidental DEFAULT_ACCIDENTAL = Accidental.Natural;
    public static final int DEFAULT_OCTAVE = 4;
    public static final Value DEFAULT_VALUE = Value.Minim;
    public static final int DEFAULT_DOTS = 0;
    public static final Duration DEFAULT_DURATION = new Duration(DEFAULT_VALUE);
    public static final int DEFAULT_VOLUME = 98;

    /**
     * Returns the {@code Value}.
     *
     * @return the {@code Value}.
     */
    Value getValue();

    /**
     * Replaces the {@code Duration} with the specified one.
     *
     * @param value a specified {@code Value}.
     */
    void setValue(Value value);

    /**
     * Returns the number of dots.
     *
     * @return the number of dots.
     */
    int getDots();

    /**
     * Replace the number of dots with the specified one.
     *
     * @param dots a specified number of dots.
     */
    void setDots(int dots);

    /**
     * Returns the duration value.
     *
     * @return the duration value.
     */
    double getDurationValue();

    /**
     * Returns the volume value.
     *
     * @return the volume value.
     */
    int getVolume();

    /**
     * Replace the volume value with the specified one.
     *
     * @param volume a specified volume value.
     */
    void setVolume(int volume);

}
