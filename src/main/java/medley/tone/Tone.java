package medley.tone;

import medley.util.Frequency;
import medley.util.Validator;

import static medley.tone.Accidental.*;
import static medley.tone.Name.*;

/**
 * {@code Tone} class that contains all the informations about a musical tone.
 *
 * @author Salvatore Gentile
 */
public final class Tone {

    private static final Tone[][] tones = {
        { new Tone(C, Natural,  0), new Tone(B, Sharp,    0) },  //  0. C (B#)
        { new Tone(C, Sharp,    1), new Tone(D, Flat,     1) },  //  1. C#/Db
        { new Tone(D, Natural,  2), new Tone(D, Natural,  2) },  //  2. D
        { new Tone(D, Sharp,    3), new Tone(E, Flat,     3) },  //  3. D#/Eb
        { new Tone(E, Natural,  4), new Tone(F, Flat,     4) },  //  4. E (Fb)
        { new Tone(F, Natural,  5), new Tone(E, Sharp,    5) },  //  5. F (E#)
        { new Tone(F, Sharp,    6), new Tone(G, Flat,     6) },  //  6. F#/Gb
        { new Tone(G, Natural,  7), new Tone(G, Natural,  7) },  //  7. G
        { new Tone(G, Sharp,    8), new Tone(A, Flat,     8) },  //  8. G#/Ab
        { new Tone(A, Natural,  9), new Tone(A, Natural,  9) },  //  9. A
        { new Tone(A, Sharp,   10), new Tone(B, Flat,    10) },  // 10. A#/Bb
        { new Tone(B, Natural, 11), new Tone(C, Flat,    11) }   // 11. B (Cb)
    };

    private Name name;
    private Accidental accidental;
    private int pitchClass;
    private int accidentalID;

    private Tone(Name name, Accidental accidental, int pitchClass) {
        this.name = name;
        this.accidental = accidental;
        this.pitchClass = pitchClass;

        if (accidental == Flat) {
            this.accidentalID = 1;
        } else {
            this.accidentalID = 0;
        }
    }

    /**
     * Initializes a newly created {@code Tone} object with the specified
     * frequency value.
     *
     * @param frequency a specified frequency value.
     * @throws IllegalArgumentException
     *         if the specified frequency is not a positive double between 8.176
     *         and 12543.854.
     * @see medley.util.Frequency#getMIDINumber(double frequency)
     */
    public Tone(double frequency) {
        this(Frequency.getMIDINumber(frequency));
    }

    /**
     * Initializes a newly created {@code Tone} object with the specified MIDI
     * number.
     *
     * @param midiNumber a specified MIDI number.
     * @throws IllegalArgumentException
     *         if the specified MIDI number is not a positive integer between 0
     *         and 127.
     * @see medley.util.Validator#midiNumber(int midiNumber)
     */
    public Tone(int midiNumber) {
        Validator.midiNumber(midiNumber);

        this.pitchClass = midiNumber % 12;
        this.accidentalID = 0;
        this.name = tones[pitchClass][accidentalID].getName();
        this.accidental = tones[pitchClass][accidentalID].getAccidental();
    }

    public Tone(Tone tone) {
        this(tone.getName(), tone.getAccidental(), tone.getPitchClass());
    }

    /**
     * Returns the {@code Name} of this {@code Tone}.
     *
     * @return the {@code Name} of this {@code Tone}.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the {@code Accidental} of this {@code Tone}.
     *
     * @return the {@code Accidental} of this {@code Tone}.
     */
    public Accidental getAccidental() {
        return accidental;
    }

    /**
     * Returns the pitch class of this {@code Tone}.
     *
     * @return the pitch class of this {@code Tone}.
     */
    public int getPitchClass() {
        return pitchClass;
    }

    /**
     * Switches the {@code Accidental} of this {@code Tone} with an equivalent
     * one.
     */
    public void switchAccidental() {
        this.accidentalID = (this.accidentalID == 0 ? 1 : 0);
        this.name = tones[pitchClass][accidentalID].getName();
        this.accidental = tones[pitchClass][accidentalID].getAccidental();
    }

    /**
     * Returns the {@code Tone} corresponding to the specified {@code Name} and
     * {@code Accidental}.
     *
     * @param name a specified {@code Name}.
     * @param accidental a specified {@code Accidental}.
     * @return the {@code Tone} corresponding to the specified {@code Name} and
     *         {@code Accidental}.
     */
    public static Tone getTone(Name name, Accidental accidental) {
        for (int pitchClass = 0; pitchClass < tones.length; pitchClass++) {
            if (tones[pitchClass][0].getName() == name
                    && tones[pitchClass][0].getAccidental() == accidental) {
                return new Tone(tones[pitchClass][0]);
            }

            if (tones[pitchClass][1].getName() == name
                    && tones[pitchClass][1].getAccidental() == accidental) {
                return new Tone(tones[pitchClass][1]);
            }
        }

        return null; // Unreachable
    }

    /**
     * Returns the pitch class of the specified {@code Name} and
     * {@code Accidental}.
     *
     * @param name a specified {@code Name}.
     * @param accidental a specified {@code Accidental}.
     * @return the pitch class of the specified {@code Name} and
     *         {@code Accidental}.
     */
    public static int getPitchClass(Name name, Accidental accidental) {
        for (int pitchClass = 0; pitchClass < tones.length; pitchClass++) {
            if (tones[pitchClass][0].getName() == name
                    && tones[pitchClass][0].getAccidental() == accidental
                    || tones[pitchClass][1].getName() == name
                    && tones[pitchClass][1].getAccidental() == accidental) {
                return pitchClass;
            }
        }

        return -1; // Unreachable
    }

    /**
     * Returns a string representation of this {@code Tone}. The string consists
     * of a concatenation of the name and an eventual accidental's symbol.
     *
     * @return a string representation of this {@code Tone}.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(10);

        sb.append(name);

        if (accidental != Natural) {
            sb.append(accidental);
        }

        return sb.toString();
    }

    /**
     * Compares the specified object with this {@code Tone} for equality.
     *
     * @param o object to be compared for equality with this {@code Tone}.
     * @return {@code true} if the specified object is equals to this
     *         {@code Tone}.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Tone)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Tone t = (Tone) o;

        if (t.getPitchClass() == this.pitchClass) {
            return true;
        }

        return false;
    }

    /**
     * Returns the hash code value for this {@code Tone}.
     *
     * @return the hash code value for this {@code Tone}.
     */
    public int hashCode() {
        final int prime = 911;

        int hash = 1;
        hash = prime * hash + name.hashCode();
        hash = prime * hash + accidental.hashCode();
        hash = prime * hash + pitchClass;
        hash = prime * hash + accidentalID;

        return hash;
    }

}
