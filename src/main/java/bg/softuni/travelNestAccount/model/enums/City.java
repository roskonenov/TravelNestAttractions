package bg.softuni.travelNestAccount.model.enums;

import java.util.Arrays;

public enum City {
    SOFIA, VELIKO_TARNOVO, DOBRICH, STARA_ZAGORA, SMOLYAN;

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(name().split("_"))
                .map(word -> word.charAt(0) + word.substring(1).toLowerCase()).toArray())
                .replaceAll("[\\[\\],]", "");

    }
}
