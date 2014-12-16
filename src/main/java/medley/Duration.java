package medley;

import medley.score.Element;
import medley.util.Validator;

/**
 * {@code Duration} class that contains all the informations about musical
 * duration and time span.
 *
 * @author Salvatore Gentile
 */
public class Duration {

    /**
     * Musical values, that represent the notes durations.
     */
    public enum Value {
        /**
         * Breve has the time value of two semibreves.
         */
        Breve(2),

        /**
         * Semibreve has the time value of half a {@code Breve} or the time
         * value of two minims or the time value of four crotchets.
         */
        Semibreve(1),

        /**
         * Minim has the time value of half a {@code Semibreve} or the time
         * value of two crotchets.
         */
        Minim(1 / 2.0),

        /**
         * Crotchet has the time value of half a {@code Minim} or the time value
         * of a quarter of a {@code Semibreve}.
         */
        Crotchet(1 / 4.0),

        /**
         * Quaver has the time value of half a {@code Crotchet} or the time
         * value of a quarter of a {@code Minim} or the time value of an eighth
         * of a {@code Semibreve}.
         */
        Quaver(1 / 8.0),

        /**
         * Semiquaver has the time value of half a {@code Quaver}.
         */
        Semiquaver(1 / 16.0),

        /**
         * Demisemiquaver has the time value of half a {@code Semiquaver}.
         */
        Demisemiquaver(1 / 32.0),

        /**
         * Hemidemisemiquaver has the time value of half a
         * {@code Demisemiquaver}.
         */
        Hemidemisemiquaver(1 / 64.0);

        private final double value;

        Value(double value) {
            this.value = value;
        }

        /**
         * Returns the duration value of this {@code Value}.
         *
         * @return the duration value of this {@code Value}.
         */
        public double getDurationValue() {
            return value;
        }
    }

    private Value value;
    private int dots;

    /**
     * Initializes a newly created {@code Duration} object with the specified
     * {@code Value}.
     *
     * @param value a specified {@code Duration} value.
     */
    public Duration(Value value) {
        this.value = value;
        this.dots = Element.DEFAULT_DOTS;
    }

    /**
     * Initializes a newly created {@code Duration} object with the
     * specified {@code Value} and dots number.
     *
     * @param value a specified {@code Value} duration.
     * @param dots a specified number of dots.
     * @throws IllegalArgumentException
     *         if the specified dots number is not a positive integer between 0
     *         and 3.
     */
    public Duration(Value value, int dots) throws IllegalArgumentException {
        Validator.dots(dots);

        this.value = value;
        this.dots = dots;
    }

    /**
     * Initializes a newly created {@code Duration} object with the same
     * {@code Value} and dots number of the specified note. In other words, the
     * newly created duration is a copy of the argument duration.
     *
     * @param duration a {@code Duration}.
     */
    public Duration(Duration duration) {
        this.value = duration.value;
        this.dots = duration.dots;
    }

    /**
     * Returns the {@code Value} of this {@code Duration}.
     *
     * @return the {@code Value} of this {@code Duration}.
     */
    public Value getValue() {
        return value;
    }

    /**
     * Replaces the {@code Value} of this {@code Duration} with the specified
     * one.
     *
     * @param value a specified {@code Value}.
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Returns the number of dots of this {@code Duration}.
     *
     * @return the number of dots of this {@code Duration}.
     */
    public int getDots() {
        return dots;
    }

    /**
     * Replace the number of dots of this {@code Duration} with the
     * specified one.
     *
     * @param dots a specified number of dots.
     * @throws IllegalArgumentException
     *         if the specified dots number is not a positive integer between 0
     *         and 3.
     * @see medley.util.Validator#dots(int dots)
     */
    public void setDots(int dots) throws IllegalArgumentException {
        Validator.dots(dots);

        this.dots = dots;
    }

    /**
     * Returns the duration value of this {@code Duration}.
     *
     * @return the duration value of this {@code Duration}.
     */
    public double getDurationValue() {
        if (dots != 0) {
            switch (dots) {
                case 1:
                    return increaseDurationValue(1 / 2.0);

                case 2:
                    return increaseDurationValue(1 / 2.0 + 1 / 4.0);

                case 3:
                    return increaseDurationValue(1 / 2.0 + 1 / 4.0 + 1 / 8.0);
            }
        }

        return value.getDurationValue();
    }

    private double increaseDurationValue(double increment) {
        return value.getDurationValue() + value.getDurationValue() * increment;
    }

    /**
     * Returns a verbose string representation of this {@code Duration}. The
     * string contains all the informations about the duration.
     *
     * @return a verbose string representation of this {@code Duration}.
     */
    public String verboseString() {
        return null;
    }

    /**
     * Returns a string representation of this {@code Duration}. The string
     * consists of a concatenation of the value and eventual dots.
     *
     * @return a string representation of this {@code Duration}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(value);

        if (dots > 0) {
            sb.append(" with ");
            sb.append(dots);
            sb.append((dots == 1 ? " dot" : " dots"));
        }

        return sb.toString();
    }

    /**
     * Compares the specified object with this {@code Duration} for equality.
     *
     * @param o object to be compared for equality with this {@code Duration}.
     * @return {@code true} if the specified object is equals to this
     *         {@code Duration}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Duration)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Duration d = (Duration) o;

        if (d.getDurationValue() != this.getDurationValue()) {
            return false;
        }

        if (d.getValue() == this.value && d.getDots() == this.dots){
            return true;
        }

        return false;
    }

    /**
     * Returns the hash code value for this {@code Duration}.
     *
     * @return the hash code value for this {@code Duration}.
     */
    @Override
    public int hashCode() {
        final int prime = 911;

        int hash = 1;
        hash = prime * hash + value.hashCode();
        hash = prime * hash + dots;

        return hash;
    }

}
