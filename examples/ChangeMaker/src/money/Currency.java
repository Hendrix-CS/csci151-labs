package money;

public enum Currency {
    PENNY(1) {
        @Override
        public String getColor() {
            return "copper";
        }
    },
    NICKLE(5) {
        @Override
        public String getColor() {
            return "nickle";
        }
    },
    DIME(10) {
        @Override
        public String getColor() {
            return "silver";
        }
    },
    FLARGLE(17) {
        @Override
        public String getColor() {
            return "purple";
        }
    },
    QUARTER(25) {
        @Override
        public String getColor() {
            return "silver";
        }
    };

    // Field
    private int value;

    // Constructor
    private Currency(int value) {
        this.value = value;
    }

    // Methods
    public int getValue() {
        return value;
    }

    public abstract String getColor();
}
