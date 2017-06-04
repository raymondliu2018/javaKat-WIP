package thePackage;

final class Drawing extends Manipulator implements GameData {
    protected static void run() {
        if (enabled) {
            GameMaster.getFrame().getPanel().preparePaint(GameData.sprites);
            GameMaster.getFrame().getPanel().prepareWrite(GameData.stats);
        }
    }
}