package e4;

import general.NoAntipatternException;
import org.jetbrains.annotations.NotNull;

public class NotE4Exception extends NoAntipatternException {
    @Override
    public @NotNull String getAntipatternType() {
        return "E4";
    }
}
