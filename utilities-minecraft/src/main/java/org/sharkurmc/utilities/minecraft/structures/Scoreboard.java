package org.sharkurmc.utilities.minecraft.structures;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.sharkurmc.utilities.java.RandomUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Scoreboard {
    public org.bukkit.scoreboard.Scoreboard originalScoreboard;
    public Objective objective;
    public String randomId;
    public Component title;
    public Scoreboard(Component title) {
        this.title = title;
        this.randomId = RandomUtils.randomString(7);
        this.originalScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        createObjective();
    }

    /**
     * Show scoreboard
     * @param player
     */
    public void show(Player player) {
        player.setScoreboard(this.originalScoreboard);
    }

    /**
     * Hide scoreboard
     * @param player
     */
    public void hide(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    /**
     * Set line in scoreboard
     * @param line line content
     * @param i position of line
     */
    public void setLine(String line, int i) {
        setLine(MiniMessage.miniMessage().deserialize(line), i);
    }

    /**
     * Set line in scoreboard
     * @param line line content
     * @param i position of line
     */
    public void setLine(Component line, int i) {
        Team team = originalScoreboard.getTeam("scoreboard_"+randomId+"_"+i) != null ?
                originalScoreboard.getTeam("scoreboard_"+randomId+"_"+i) :
                originalScoreboard.registerNewTeam("scoreboard_"+randomId+"_"+i);
        team.suffix(line);

        String empty = scoreToName(i);
        team.addEntry(empty);

        objective.getScore(empty).setScore(i);
    }

    /**
     * Set lines in scoreboard
     * @param lines
     */
    public void setLines(String... lines) {
        setLines(Arrays.stream(lines).map((line) -> MiniMessage.miniMessage().deserialize(line)).collect(Collectors.toList()), true);
    }

    /**
     * Set lines in scoreboard
     * @param lines
     * @param reset
     */
    public void setLines(List<Component> lines, boolean reset) {
        Collections.reverse(lines);

        if (reset) {
            for (Team team : originalScoreboard.getTeams()) {
                team.unregister();
            }

            createObjective();
        }

        int i = 1;
        for (Component line : lines) {
            setLine(line, i);
            i++;
        }
    }

    /**
     * Set scoreboard title
     * @param title
     */
    public void setTitle(String title) {
        setTitle(MiniMessage.miniMessage().deserialize(title));
    }

    /**
     * Set scoreboard title
     * @param title
     */
    public void setTitle(Component title) {
        objective.displayName(title);
    }

    /**
     * Create scoreboard
     * @param title
     * @param lines
     * @return scoreboard
     */
    public static org.sharkurmc.utilities.minecraft.structures.Scoreboard createScoreboard(String title, String... lines) {
        return createScoreboard(
                MiniMessage.miniMessage().deserialize(title),
                Arrays.stream(lines).map((line) -> MiniMessage.miniMessage().deserialize(line)).collect(Collectors.toList())
        );
    }

    /**
     * Create scoreboard
     * @param title
     * @param lines
     * @return scoreboard
     */
    public static org.sharkurmc.utilities.minecraft.structures.Scoreboard createScoreboard(Component title, List<Component> lines) {
        Collections.reverse(lines);

        Scoreboard scoreboard = new Scoreboard(title);

        scoreboard.setLines(lines, false);

        return scoreboard;
    }

    private static String scoreToName(int score) {
        return ChatColor.values()[score].toString();
    }

    private void createObjective() {
        if (objective != null) objective.unregister();
        objective = originalScoreboard.registerNewObjective("obj", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
}
