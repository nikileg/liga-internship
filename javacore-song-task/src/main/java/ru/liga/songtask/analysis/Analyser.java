package ru.liga.songtask.analysis;

import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;

public class Analyser {
    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Analyser.class);
    public static final String ENDL = System.getProperty("line.separator");

    public static void analyse(SimpleMidiFile midiFile, Writer out) throws IOException {
        List<Note> notes = midiFile.vocalNoteList();

        out.write("Количество нот:%d" + notes.size() + ENDL);
        out.write("Длительность (сек):%f" + (midiFile.durationMs() / 1000.0) + ENDL);
        out.write(ENDL);

        Stream<NoteSign> signStream = notes.stream().map(Note::sign);
        NoteSign min = signStream
                .min(Comparator.comparing(NoteSign::getMidi))
                .get();
        NoteSign max = signStream
                .max(Comparator.comparing(NoteSign::getMidi))
                .get();

        out.write("Анализ диапазона:" + ENDL);
        out.write("верхняя: " + max.shortName() + ENDL);
        out.write("нижняя: " + min.shortName() + ENDL);
        out.write("диапазон: " + max.diffInSemitones(min) + ENDL);
        out.write(ENDL);

        Map<Long, Long> dur2count = notes.stream().collect(
                Collectors.groupingBy(
                        note -> Math.round(note.durationTicks() * midiFile.tickInMs() / 1000.0), Collectors.counting()
                )
        );
        String durFormated = dur2count.entrySet().stream()
                .sorted(reverseOrder(Map.Entry.comparingByKey()))
                .map(e -> e.getValue() + ": " + e.getKey())
                .collect(Collectors.joining(ENDL));

        out.write("Анализ длительностей нот (мс):" + ENDL);
        out.write(durFormated + ENDL);
        out.write(ENDL);

    }

    public static void main(String[] args) {
        String infilename = args[0];
        String command = args[1];
        if (command.equals("analyze")) {
            if (args.length > 2) {
                String param = args[2];

            }
        }
    }
}
