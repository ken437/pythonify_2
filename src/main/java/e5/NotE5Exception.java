package e5;

import general.NoAntipatternException;
import org.jetbrains.annotations.NotNull;

public class NotE5Exception extends NoAntipatternException {
    @Override
    public @NotNull String getAntipatternType() {
        return "E5";
    }
}
