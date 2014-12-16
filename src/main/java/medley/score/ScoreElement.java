package medley.score;

import medley.Duration;
import medley.Duration.Value;
import medley.util.Validator;

/**
 * {@code ScoreElement} class that models a musical score entity.
 *
 * @author Salvatore Gentile
 * @see medley.score.Element
 */
public abstract class ScoreElement implements Element {

    protected Duration duration;
    protected int volume;

    public ScoreElement() {
        this(DEFAULT_DURATION, DEFAULT_VOLUME);
    }

    public ScoreElement(Duration duration) {
        this(duration, DEFAULT_VOLUME);
    }

    public ScoreElement(Duration duration, int volume) {
        this.duration = duration;
        this.volume = volume;
    }

    /**
     * Returns the {@code Value} of this {@code ScoreElement}.
     *
     * @return the {@code Value} of this {@code ScoreElement}.
     * @see medley.Duration#getValue()
     */
    public Value getValue() {
        return duration.getValue();
    }

    /**
     * Replaces the {@code Duration} of this {@code ScoreElement} with the
     * specified one.
     *
     * @param value a specified {@code Value}.
     * @see medley.Duration#setValue(Value value)
     */
    public void setValue(Value value) {
        duration.setValue(value);
    }

    /**
     * Returns the number of dots of this {@code ScoreElement}.
     *
     * @return the number of dots of this {@code ScoreElement}.
     * @see medley.Duration#getDots()
     */
    public int getDots() {
        return duration.getDots();
    }

    /**
     * Replace the number of dots of this {@code ScoreElement} with the
     * specified one.
     *
     * @param dots a specified number of dots.
     * @throws IllegalArgumentException
     *         if {@code dots < 0} or {@code dots > 3}
     * @see medley.Duration#setDots(int dots)
     */
    public void setDots(int dots) throws IllegalArgumentException {
        duration.setDots(dots);
    }

    /**
     * Returns the duration value of this {@code ScoreElement}.
     *
     * @return the duration value of this {@code ScoreElement}.
     * @see medley.Duration#getDurationValue()
     */
    public double getDurationValue() {
        return duration.getDurationValue();
    }

    /**
     * Returns the volume value of this {@code ScoreElement}.
     *
     * @return the volume value of this {@code ScoreElement}.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Replace the volume value of this {@code ScoreElement} with the
     * specified one.
     *
     * @param volume a specified volume value.
     * @throws IllegalArgumentException
     *         if {@code volume < 0} or {@code volume > 127}
     * @see medley.util.Validator#volume(int volume)
     */
    public void setVolume(int volume) throws IllegalArgumentException {
        Validator.volume(volume);

        this.volume = volume;
    }

    /**
     * Compares the specified object with this {@code ScoreElement} for
     * equality.
     *
     * <p>This implementation first checks if the specified object is a
     * {@code ScoreElement}. If not, it returns {@code false}; if so, it checks
     * if the specified object is this {@code ScoreElement}. If not, it returns
     * {@code false}; if so, it compares the duration value of both the score
     * elements.
     *
     * @param o object to be compared for equality with this
     *          {@code ScoreElement}.
     * @return {@code true} if the specified object is equals to this
     *         {@code ScoreElement}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        if (!(o instanceof ScoreElement)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        ScoreElement se = (ScoreElement) o;

        if (se.getDurationValue() == this.getDurationValue()) {
            return true;
        }

        return false;
    }

    /**
     * Returns the hash code value for this {@code ScoreElement}.
     *
     * @return the hash code value for this {@code ScoreElement}.
     */
    @Override
    public int hashCode() {
        final int prime = 911;

        int hash = 1;
        hash = prime * hash + duration.hashCode();
        hash = prime * hash + volume;

        return hash;
    }

}
