package e7;

import general.NoAntipatternException;
import org.jetbrains.annotations.NotNull;

public class NotE7Exception extends NoAntipatternException {
    @Override
    public @NotNull String getAntipatternType() {
        return "E7";
    }
}