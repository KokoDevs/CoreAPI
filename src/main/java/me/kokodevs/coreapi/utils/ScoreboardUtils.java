package me.kokodevs.coreapi.utils;

import me.kokodevs.coreapi.CoreAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardUtils {
    public ScoreboardUtils(){}

    public static void setScoreBoard(Player player, String title, List<String> scoreBoard){
        CoreAPI.titleManager.giveScoreboard(player);
        CoreAPI.titleManager.setScoreboardTitle(player, title);

        for(int i = 0; i < scoreBoard.size(); ++i){
            CoreAPI.titleManager.setScoreboardValue(player, i + 1, scoreBoard.get(i));
        }
    }

}
