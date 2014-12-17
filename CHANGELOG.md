Changelog
=========
All the major updates will be listed below.

### Version 0.1.0 (2014-12-16)

    + medley.Note:
        * Ability to create a note using:
            - a note frequency;
            - a MIDI note number;
            - a note string;
            - a note tone and octave;
        * Ability to transpose a note;
        * Ability to switch the accidental representation of a note;

    + medley.tone.Tone:
        * Ability to create a tone using:
            - a note frequency;
            - a MIDI note number;
        * Ability to get a tone from given note name and note accidental;
        * Ability to get the pitch class of given note name and accidental;

    + medley.util.Frequency: utility class that contains methods useful during frequencies manipulation;

    + medley.util.StringParser:
        * Ability to get a note from a given string;
        * Ability to get a note name from a given string;
        * Ability to get a note accidental from a given string;

