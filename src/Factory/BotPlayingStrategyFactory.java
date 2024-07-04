package Factory;

import Models.BotDifficultyLevel;
import Strategy.BotPlayingStrategies.BotPlayingStrategy;
import Strategy.BotPlayingStrategies.EasyBotPlayingStrategy;
import Strategy.BotPlayingStrategies.HardBotPlayingStrategy;
import Strategy.BotPlayingStrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStategy(BotDifficultyLevel difficultyLevel){
        if(difficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }else if(difficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        } else if (difficultyLevel.equals(BotDifficultyLevel.HARD)) {
            return new HardBotPlayingStrategy();
        }
        return null;
    }
}
