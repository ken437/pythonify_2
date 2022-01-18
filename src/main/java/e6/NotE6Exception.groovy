package e6

import general.NoAntipatternException

class NotE6Exception extends NoAntipatternException{
    @Override
    String getAntipatternType() {
        return "E6";
    }
}