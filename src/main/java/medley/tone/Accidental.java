package medley.tone;

/**
 * {@code Name} class that represents musical notes accidental, that alter the
 * pitch of a note.
 */
public enum Accidental {

    /**
     * Flat, or Bemolle, means <i>lower in pitch</i>. It lowers a note by a half
     * step. It's associated symbol is \u266d.
     */
    Flat("Flat", '\u266d'),

    /**
     * Natural, or Bequadro, represents the <i>unaltered pitch</i> of a note. It
     * cancels previous accidentals. A note is natural whet it is neither flat
     * or sharp. It's associated symbol is \u266e.
     */
    Natural("Natural", '\u266e'),

    /**
     * Sharp, or Diesis, means <i>higher in pitch</i>. It raises a note by a
     * half step. It's associated symbol is \u266f.
     */
    Sharp("Sharp", '\u266f');

    private final String name;
    private final char symbol;

    Accidental(final String name, final char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Returns the name of this {@code Accidental}.
     *
     * @return the name of this {@code Accidental}.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the symbol of this {@code Accidental}.
     *
     * @return the symbol of this {@code Accidental}.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns a string representation of this {@code Accidental}. The string
     * consists of the symbol of the accidental. The symbol is converted to
     * string as by {@code String.valueOf(Object)}.
     *
     * @return a string representation of this {@code Accidental}.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

};
