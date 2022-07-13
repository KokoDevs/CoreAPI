package me.kokodevs.kokoapi.utils;

import me.kokodevs.kokoapi.KokoAPI;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class SoundUtils {
    public static HashMap<Player, HashMap<String, Integer>> playerLoopSounds;

    public SoundUtils(){}

    public static void playMusic(Player player, String music){
        playMusic(player, music, 1000000.0F, 1.0F);
    }
    public static void playMusic(Player player, String music, float volume){
        playMusic(player, music, volume, 1.0F);
    }
    public static void playMusic(Player player, String music, float volume, float pitch){
        player.playSound(player.getLocation(), music, SoundCategory.MASTER, volume, pitch);
    }




    public static void playMusicLoop(Player player, String music, int length){
        playMusicLoop(player, music, 1000000.0F, 1.0F, length);
    }
    public static void playMusicLoop(Player player, String music, float volume, int length) {
        playMusicLoop(player, music, volume, 1.0F, length);
    }
    public static void playMusicLoop(final Player player, final String music, final float volume, final float pitch, final int length) {
        if(!playerLoopSounds.containsKey(player)){
            playerLoopSounds.put(player, new HashMap<>());
        }

        final HashMap<String, Integer> soundMap = playerLoopSounds.get(player);
        soundMap.put(music, length);
        (new BukkitRunnable(){

            @Override
            public void run() {
                if(soundMap.containsKey(music) && soundMap.get(music) != 0){
                    playMusic(player, music, volume, pitch);
                }else {
                    this.cancel();
                }
            }
        }).runTaskTimer(KokoAPI.getPlugin(), 0L, (long)length * 20);
    }
    public static void stopMusicLoop(Player player, String music){
        if(!playerLoopSounds.containsKey(player)){
            playerLoopSounds.put(player, new HashMap());
        }

        player.stopSound(music, SoundCategory.MASTER);
        HashMap<String, Integer> soundMap = playerLoopSounds.get(player);
        soundMap.remove(music);
    }
    static {
        playerLoopSounds = new HashMap<>();
    }
}
