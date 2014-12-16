package medley.util;

import medley.Note;
import medley.score.Element;
import medley.tone.Accidental;
import medley.tone.Name;
import medley.tone.Tone;

/**
 * {@code StringParser} class that is used to manage strings that
 * represent musical elements such as notes.
 *
 * <p>The class provides the {@code static} method
 * {@link #getNote(String) getNote}, that returns the {@code Note} corresponding
 * to the specified string.
 *
 * <p>The class also provides the {@code static} methods
 * {@link #getName(String) getName} and
 * {@link #getAccidental(String) getAccidental},
 * that returns respectively the {@code Name} and the {@code Accidental}
 * corresponding to the specified string.
 *
 * @author Salvatore Gentile
 */
public final class StringParser {

    private static final String NOTE_NAME_PATTERN = "[A-G]";
    private static final String NOTE_ACCIDENTAL_PATTERN = "[b#]";
    private static final String NOTE_PATTERN = "[A-G][b#]*(-)?[0-9]*";

    private StringParser() {}

    /**
     * Returns the {@code Name} corresponding to the specified string.
     *
     * @param noteName a specified note name string.
     * @return the {@code Name} corresponding to the specified string.
     * @throws IllegalArgumentException
     *         if the specified note name string does not match the note name
     *         pattern {@code [A-G]}.
     */
    public static Name getName(String noteName) {
        noteName = noteName.toUpperCase();

        if (!(noteName.matches(NOTE_NAME_PATTERN))) {
            throw new IllegalArgumentException("Invalid note name!");
        }

        switch (noteName) {
            case "C":
                return Name.C;

            case "D":
                return Name.D;

            case "E":
                return Name.E;

            case "F":
                return Name.F;

            case "G":
                return Name.G;

            case "A":
                return Name.A;

            case "B":
                return Name.B;

            default:
                return null;
        }
    }

    /**
     * Returns the {@code Accidental} corresponding to the specified string.
     *
     * @param noteAccidental a specified note accidental string.
     * @return the {@code Accidental} corresponding to the specified string.
     * @throws IllegalArgumentException
     *         if the specified note accidental string does not match the
     *         accidental pattern {@code [b#]}.
     */
    public static Accidental getAccidental(String noteAccidental) {
        if (!(noteAccidental.matches(NOTE_ACCIDENTAL_PATTERN))) {
            throw new IllegalArgumentException("Invalid note accidental!");
        }

        switch (noteAccidental) {
            case "b":
                return Accidental.Flat;

            case "#":
                return Accidental.Sharp;

            default:
                return null;
        }
    }

    /**
     * Returns the {@code Note} corresponding to the specified string.
     *
     * @param note a specified note string.
     * @return the {@code Note} corresponding to the specified string.
     * @throws IllegalArgumentException
     *         if the specified note string does not match the note pattern
     *         {@code [A-G][b#]*(-)?[0-9]*}.
     */
    public static Note getNote(String note) {
        if (!(note.matches(NOTE_PATTERN))) {
            throw new IllegalArgumentException("Invalid note!");
        }

        int argLength = note.length();

        String name = String.valueOf(note.charAt(0));

        if (argLength > 1) {
            // Name + Octave
            if (note.substring(1, argLength).matches("(-)?[0-9]+")) {
                String octave = note.substring(1, argLength);
                Tone tone = Tone.getTone(StringParser.getName(name),
                                         Element.DEFAULT_ACCIDENTAL);

                return new Note(tone, Integer.parseInt(octave));
            }

            String accidental = String.valueOf(note.charAt(1));

            // Name + Accidental + Octave
            if (argLength > 2) {
                String octave = note.substring(2, argLength);
                Tone tone = Tone.getTone(StringParser.getName(name),
                                         StringParser.getAccidental(accidental));

                return new Note(tone, Integer.parseInt(octave));
            }

            // Name + Accidental
            Tone tone = Tone.getTone(StringParser.getName(name),
                                     StringParser.getAccidental(accidental));

            return new Note(tone, Element.DEFAULT_OCTAVE);
        }

        // Name
        Tone tone = Tone.getTone(StringParser.getName(name),
                                 Element.DEFAULT_ACCIDENTAL);

        return new Note(tone, Element.DEFAULT_OCTAVE);
    }

}
