package me.kokodevs.kokoapi.utils;

import me.kokodevs.kokoapi.KokoAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardUtils {
    public ScoreboardUtils(){}

    public static void setScoreBoard(Player player, String title, List<String> scoreBoard){
        KokoAPI.titleManager.giveScoreboard(player);
        KokoAPI.titleManager.setScoreboardTitle(player, title);

        for(int i = 0; i < scoreBoard.size(); ++i){
            KokoAPI.titleManager.setScoreboardValue(player, i + 1, scoreBoard.get(i));
        }
    }

}
