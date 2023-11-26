package utilz;

public class Constants {
    public static class Directions {
        public static final int RIGHT = 1;
        public static final int LEFT = 2;
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
                    return 8; // 3
                case ATTACK:
                    return 10; // 14
                default:
                    return 1;
            }
        }
    }
}
