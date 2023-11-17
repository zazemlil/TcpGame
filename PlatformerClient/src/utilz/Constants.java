package utilz;

public class Constants {
    public static class ClientCommands {
        public static final String BUTTON_W = "BUTTON_W_PRESSED";
        public static final String BUTTON_A = "BUTTON_A_PRESSED";
        public static final String BUTTON_S = "BUTTON_S_PRESSED";
        public static final String BUTTON_D = "BUTTON_D_PRESSED";
        public static final String SPACE = "BUTTON_SPACE_PRESSED";
        public static final String BUTTON_W_RM = "BUTTON_W_REMOVED";
        public static final String BUTTON_A_RM = "BUTTON_A_REMOVED";
        public static final String BUTTON_S_RM = "BUTTON_S_REMOVED";
        public static final String BUTTON_D_RM = "BUTTON_D_REMOVED";
        public static final String SPACE_RM = "BUTTON_SPACE_REMOVED";
    }
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;

        public static int getSpriteAmount(int playerAction) {
            switch (playerAction) {
                case RUNNING:
                    return 8;
                case IDLE:
                    return 3;
                case ATTACK:
                    return 14;
                default:
                    return 1;
            }
        }
    }
}
