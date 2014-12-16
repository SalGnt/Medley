package medley;

import medley.score.ScoreElement;

/**
 * {@code Rest} class that contains all the informations about a musical rest.
 *
 * @author Salvatore Gentile
 * @see medley.score.Element
 * @see medley.score.ScoreElement
 */
public class Rest extends ScoreElement {

    /**
     * Initializes a newly created {@code Rest} object with the specified
     * {@code Duration}.
     *
     * @param duration a specified {@code Duration}.
     */
    public Rest(Duration duration) {
        super(duration, 0);
    }

    /**
     * Replaces the volume value of this {@code Rest} with the specified one.
     *
     * @param volume a specified volume value.
     * @throws UnsupportedOperationException
     *         the volume of a rest cannot be edited.
     */
    @Override
    public void setVolume(int volume) throws UnsupportedOperationException {
        StringBuilder e = new StringBuilder(60);

        e.append("Invalid operation! ");
        e.append("The volume of a rest cannot be edited.");

        throw new UnsupportedOperationException(e.toString());
    }

    /**
     * Returns a string representation of this {@code Rest}.
     *
     * @return a string representation of this {@code Rest}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Rest (");
        sb.append(duration);
        sb.append(")");

        return sb.toString();
    }

    /**
     * Compares the specified object with this {@code Rest} for equality.
     *
     * @param o object to be compared for equality with this {@code Rest}.
     * @return {@code true} if the specified object is equals to this
     *         {@code Rest}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rest)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Rest r = (Rest) o;

        if (r.duration.equals(this.duration)) {
            return true;
        }

        return false;
    }

    /**
     * Returns the hash code value for this {@code Rest}.
     *
     * @return the hash code value for this {@code Rest}.
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
