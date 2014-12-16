package medley;

import java.util.Locale;

import medley.score.ScoreElement;
import medley.tone.Accidental;
import medley.tone.Name;
import medley.tone.Tone;
import medley.util.Frequency;
import medley.util.StringParser;
import medley.util.Validator;

/**
 * {@code Note} class that contains all the informations about a musical note.
 * This class represents a single note. A note is identified by a frequency and
 * by a MIDI note number.
 *
 * <p>A note has also a {@code Name}, represented using English literal notation
 * an {@code Accidental}, that represent the alteration of a note, and an
 * octave.
 *
 * <p>A {@code Note} can be easily transposed using the methods
 * {@link #semitoneUp() semitoneUp}, {@link #semitoneDown() semitoneDown} and
 * {@link #transpose(int semitones) transpose}.
 *
 * @author Salvatore Gentile
 * @see medley.score.Element
 * @see medley.score.ScoreElement
 */
public class Note extends ScoreElement {

    private double frequency;
    private int midiNumber;
    private Tone tone;
    private int octave;

    /**
     * Initializes a newly created {@code Note} object with the specified
     * frequency value.
     *
     * <p>The new {@code Note} will be created using the value of the nearest
     * valid note frequency.
     *
     * @param frequency a specified frequency value.
     * @throws IllegalArgumentException
     *         if the specified frequency is not a positive double between 8.176
     *         and 12543.854.
     * @see #Note(double frequency, Duration duration, int volume)
     */
    public Note(double frequency) throws IllegalArgumentException {
        this(frequency, DEFAULT_DURATION, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object with the specified
     * frequency value and {@code Duration}.
     *
     * <p> The new {@code Note} will be created using the value of the nearest
     * valid note frequency.
     *
     * @param frequency a specified frequency value.
     * @param duration a specified {@code Duration}.
     * @throws IllegalArgumentException
     *         if the specified frequency is not a positive double between 8.176
     *         and 12543.854.
     * @see #Note(double frequency, Duration duration, int volume)
     */
    public Note(double frequency, Duration duration)
            throws IllegalArgumentException {
        this(frequency, duration, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object with the specified
     * frequency value, {@code Duration} and volume value. The new {@code Note}
     * will be created using the value of the nearest valid note frequency.
     *
     * @param frequency a specified frequency value.
     * @param duration a specified {@code Duration}.
     * @param volume a specified volume value.
     * @throws IllegalArgumentException
     *         if the specified frequency is not a positive double between 8.176
     *         and 12543.854.
     * @see medley.util.Frequency#getMIDINumber(double frequency)
     * @see medley.tone.Tone#Tone(double frequency)
     */
    public Note(double frequency, Duration duration, int volume)
            throws IllegalArgumentException {
        super(duration, volume);

        this.midiNumber = Frequency.getMIDINumber(frequency);
        this.frequency = frequency;
        this.tone = new Tone(frequency);
        this.octave = (midiNumber / 12) - 1;
    }

    /**
     * Initializes a newly created {@code Note} object with the specified MIDI
     * number.
     *
     * @param midiNumber a specified MIDI note number.
     * @throws IllegalArgumentException
     *         if the specified MIDI note number is not a positive integer
     *         between 0 and 127.
     * @see #Note(int midiNumber, Duration duration, int volume)
     */
    public Note(int midiNumber) throws IllegalArgumentException {
        this(midiNumber, DEFAULT_DURATION, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object with the specified MIDI
     * number and {@code Duration}.
     *
     * @param midiNumber a specified MIDI note number.
     * @param duration a specified {@code Duration}.
     * @throws IllegalArgumentException
     *         if the specified MIDI note number is not a positive integer
     *         between 0 and 127.
     * @see #Note(int midiNumber, Duration duration, int volume)
     */
    public Note(int midiNumber, Duration duration)
            throws IllegalArgumentException {
        this(midiNumber, duration, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object with the specified MIDI
     * number, {@code Duration} and volume value.
     *
     * @param midiNumber a specified MIDI note number.
     * @param duration a specified {@code Duration}.
     * @param volume a specified volume value.
     * @throws IllegalArgumentException
     *         if the specified MIDI note number is not a positive integer
     *         between 0 and 127.
     * @see medley.util.Frequency#getMIDINumberFrequency(int midiNumber)
     * @see medley.tone.Tone#Tone(int midiNumber)
     */
    public Note(int midiNumber, Duration duration, int volume)
            throws IllegalArgumentException {
        super(duration, volume);

        this.frequency = Frequency.getMIDINumberFrequency(midiNumber);
        this.midiNumber = midiNumber;
        this.tone = new Tone(midiNumber);
        this.octave = (midiNumber / 12) - 1;
    }

    /**
     * Initializes a newly created {@code Note} object by parsing the specified
     * string.
     *
     * <p>For further details about the parsing procedure please refer to
     * the {@link medley.util.StringParser StringParser} class.
     *
     * @param note a specified note string.
     * @throws IllegalArgumentException
     *         if the specified note string does not match the note pattern
     *         {@code [A-G][b#]*(-)?[0-9]*}.
     * @see #Note(String note, Duration duration, int volume)
     */
    public Note(String note) throws IllegalArgumentException {
        this(note, DEFAULT_DURATION, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object by parsing the specified
     * string and with the specified {@code Duration}.
     *
     * <p>For further details about the parsing procedure please refer to
     * the {@link medley.util.StringParser StringParser} class.
     *
     * @param note a specified note string.
     * @param duration a specified {@code Duration}.
     * @throws IllegalArgumentException
     *         if the specified note string does not match the note pattern
     *         {@code [A-G][b#]*(-)?[0-9]*}.
     * @see #Note(String note, Duration duration, int volume)
     */
    public Note(String note, Duration duration)
            throws IllegalArgumentException {
        this(note, duration, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object by parsing the specified
     * string and with the specified {@code Duration} and volume value.
     *
     * <p>For further details about the parsing procedure please refer to
     * the {@link medley.util.StringParser StringParser} class.
     *
     * @param note a specified note string.
     * @param duration a specified {@code Duration}.
     * @param volume a specified volume value.
     * @throws IllegalArgumentException
     *         if the specified note string does not match the note pattern
     *         {@code [A-G][b#]*(-)?[0-9]*}.
     * @see medley.util.StringParser#getNote(String note)
     */
    public Note(String note, Duration duration, int volume)
            throws IllegalArgumentException {
        super(duration, volume);

        Note newNote = StringParser.getNote(note);
        this.frequency = newNote.frequency;
        this.midiNumber = newNote.midiNumber;
        this.tone = newNote.tone;
        this.octave = newNote.octave;
    }

    /**
     * Initializes a newly created {@code Note} object with the specified
     * {@code Tone} and octave.
     *
     * @param tone a specified {@code Tone}.
     * @param octave a specified octave.
     * @see #Note(Tone tone, int octave, Duration duration, int volume)
     */
    public Note(Tone tone, int octave) {
        this(tone, octave, DEFAULT_DURATION, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object with the specified
     * {@code Tone}, octave, and {@code Duration}.
     *
     * @param tone a specified {@code Tone}.
     * @param octave a specified octave.
     * @param duration a specified {@code Duration}.
     * @see #Note(Tone tone, int octave, Duration duration, int volume)
     */
    public Note(Tone tone, int octave, Duration duration) {
        this(tone, octave, duration, DEFAULT_VOLUME);
    }

    /**
     * Initializes a newly created {@code Note} object with the specified
     * {@code Tone}, octave, {@code Duration} and volume value.
     *
     * @param tone a specified {@code Tone}.
     * @param octave a specified octave.
     * @param duration a specified {@code Duration}.
     * @param volume a specified volume value.
     * @see medley.util.Frequency#getMIDINumberFrequency(int midiNumber)
     */
    public Note(Tone tone, int octave, Duration duration, int volume) {
        super(duration, volume);

        this.tone = tone;
        this.octave = octave;
        this.midiNumber = tone.getPitchClass() + 12 * (octave + 1);
        this.frequency = Frequency.getMIDINumberFrequency(midiNumber);
    }

    /**
     * Initializes a newly created {@code Note} object with the same frequency
     * value, MIDI note number, {@code Tone} and octave of the specified note.
     * In other words, the newly created note is a copy of the argument note.
     *
     * <p>The new {@code Note} has obviously the same {@code Duration} and
     * volume value of the specified note.
     *
     * @param note a specified {@code Note}.
     */
    public Note(Note note) {
        this.frequency = note.frequency;
        this.midiNumber = note.midiNumber;
        this.tone = note.tone;
        this.octave = note.octave;
        // Inherited fields
        this.duration = note.duration;
        this.volume = note.volume;
    }

    /**
     * Returns the {@code Name} of this {@code Note}.
     *
     * @return the {@code Name} of this {@code Note}.
     */
    public Name getName() {
        return tone.getName();
    }

    /**
     * Returns the {@code Accidental} of this {@code Note}.
     *
     * @return the {@code Accidental} of this {@code Note}.
     */
    public Accidental getAccidental() {
        return tone.getAccidental();
    }

    /**
     * Returns the octave of this {@code Note}.
     *
     * @return the octave of this {@code Note}.
     */
    public int getOctave() {
        return octave;
    }

    /**
     * Returns the frequency of this {@code Note}.
     *
     * @return the frequency of this {@code Note}.
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Returns the MIDI note number of this {@code Note}.
     *
     * @return the MIDI note number of this {@code Note}.
     */
    public int getMIDINumber() {
        return midiNumber;
    }

    /**
     * Raises this {@code Note} by a half step.
     *
     * @see #transpose(int semitones)
     */
    public void semitoneUp() {
        transpose(1);
    }

    /**
     * Lowers this {@code Note} by a half step.
     *
     * @see #transpose(int semitones)
     */
    public void semitoneDown() {
        transpose(-1);
    }

    /**
     * Transposes this {@code Note} by a specified number of half steps.
     *
     * <p>This operation does not affect the {@code Duration} and the volume of
     * the note.
     *
     * @param semitones a specified number of half steps.
     * @see medley.util.Validator#transpose(int midiNumber, int semitones)
     */
    public void transpose(int semitones) throws IllegalArgumentException {
        if (semitones == 0) {
            return;
        }

        Validator.transpose(midiNumber, semitones);

        this.midiNumber += semitones;
        this.frequency = Frequency.getMIDINumberFrequency(midiNumber);
        this.tone = new Tone(midiNumber);
        this.octave = (midiNumber / 12) - 1;
    }

    /**
     * Switches the {@code Accidental} of this {@code Note} with an equivalent
     * one.
     */
    public void switchAccidental() {
        // Adjusts the octave when switching from C to B-Sharp...
        if (tone.getName() == Name.C
                && tone.getAccidental() == Accidental.Natural) {
            octave--;
        }

        // ...and viceversa.
        if (tone.getName() == Name.B
                && tone.getAccidental() == Accidental.Sharp) {
            octave++;
        }

        tone.switchAccidental();
    }

    /**
     * Returns an extended string representation of this {@code Note}. The
     * string contains all the informations about the note.
     *
     * @return an extended string representation of this {@code Note}.
     */
    public String toStringExt() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nNOTE:\t\t");
        sb.append(this.toString());

        sb.append("\nName:\t\t");
        sb.append(tone.getName());

        sb.append("\nAccidental:\t");
        sb.append(tone.getAccidental().getName());

        sb.append("\nOctave:\t\t");
        sb.append(octave);

        sb.append("\nFrequency:\t");
        sb.append(String.format(Locale.ENGLISH, "%1.3f", frequency));
        sb.append("Hz");

        sb.append("\nMIDI number:\t");
        sb.append(midiNumber);

        sb.append("\nDuration:\t");
        sb.append(duration);

        sb.append("\nDuration Value:\t");
        sb.append(duration.getDurationValue());

        sb.append("\nVolume:\t\t");
        sb.append(volume);

        sb.append("\n");

        return sb.toString();
    }

    /**
     * Returns a string representation of this {@code Note}. The string consists
     * of a concatenation of the name, an eventual accidental's symbol and the
     * octave of the note.
     *
     * @return a string representation of this {@code Note}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(3);

        sb.append(tone.getName());

        if (tone.getAccidental() != Accidental.Natural) {
            sb.append(tone.getAccidental());
        }

        sb.append(octave);

        return sb.toString();
    }

    /**
     * Compares the specified object with this {@code Note} for equality.
     *
     * <p>This implementation first checks if the specified object is a
     * {@code Note}. If not, it returns {@code false}; if so, it checks if the
     * specified object is this {@code Note}. If not, it returns {@code false};
     * if so, it compares the frequency of both notes.
     *
     * @param o object to be compared for equality with this {@code Note}.
     * @return {@code true} if the specified object is equals to this
     *         {@code Note}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Note)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Note n = (Note) o;

        if (n.getFrequency() == this.frequency
                && n.duration.equals(this.duration)) {
            return true;
        }

        return false;
    }

    /**
     * Returns the hash code value for this {@code Note}.
     *
     * @return the hash code value for this {@code Note}.
     */
    @Override
    public int hashCode() {
        final int prime = 911;

        int hash = super.hashCode();
        hash = prime * hash + new Double(frequency).hashCode();
        hash = prime * hash + midiNumber;
        hash = prime * hash + tone.hashCode();
        hash = prime * hash + octave;

        return hash;
    }

}
