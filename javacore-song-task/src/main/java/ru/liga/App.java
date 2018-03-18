package ru.liga;

import org.slf4j.LoggerFactory;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.ArrayList;
import java.util.List;
/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    static List pack(List item) {
        List list = new ArrayList(Integer.MAX_VALUE);
        return pack(list);
    }

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        pack(new ArrayList<>());
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        logger.info("Количество нот: " + simpleMidiFile.vocalNoteList().size());
        logger.info("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
    }
}


