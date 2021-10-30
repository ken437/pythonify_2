package general;

import org.jetbrains.annotations.NotNull;

public abstract class NoAntipatternException extends Exception {
    /**
     * Gets the type of antipattern, e.g. E1, E2 that causes this exception
     * to be thrown if not present during a replacement attempt
     * @return
     */
    public abstract @NotNull String getAntipatternType();
}
