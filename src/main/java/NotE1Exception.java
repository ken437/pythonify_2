import org.jetbrains.annotations.NotNull;

public class NotE1Exception extends NoAntipatternException {
    @Override
    public @NotNull String getAntipatternType() {
        return "E1";
    }
}
