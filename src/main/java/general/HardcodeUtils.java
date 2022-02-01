package general;

import java.util.List;

/**
 * Helpful class with utility functions related to detecting sensitive
 * hardcoded data
 */
public class HardcodeUtils {
    /**
     * List of all of the possible keyword arguments that are probably for passwords
     * @return
     */
    public static List<String> getPasswordKeywords() {
        return List.of(
                "password",
                "passwd",
                "pass_word",
                "password"
        );
    }
}
