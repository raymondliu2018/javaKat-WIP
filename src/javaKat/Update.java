package javaKat;  

import java.util.List;
import workspace.Script;

final class Update extends Manipulator implements GameData
{
    protected static void run() {
        for( Entity entity: GameData.allEntities ){
            entity.update();
        }
        for( List <Album> list: GameData.sprites ) {
            for ( Album sprite: list ) {
                sprite.update();
            }
        }
        for( Tag text: GameData.stats ){
            text.update();
        }
        for( Ender ender: GameData.enders ) {
            if (ender.checkEndGameCondition()) {Script.end();}
        }
    }
}
