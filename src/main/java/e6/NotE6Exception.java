package e6;

import general.NoAntipatternException;
import org.jetbrains.annotations.NotNull;

public class NotE6Exception extends NoAntipatternException {
    @Override
    public @NotNull String getAntipatternType() {
        return "E6";
    }
}