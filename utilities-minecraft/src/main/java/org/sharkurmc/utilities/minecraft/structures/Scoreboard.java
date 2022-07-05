package org.sharkurmc.utilities.minecraft.structures;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Scoreboard {
    public org.bukkit.scoreboard.Scoreboard originalScoreboard;
    public Objective objective;
    public Component title;
    public Scoreboard(Component title) {
        this.title = title;
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
        Team team = originalScoreboard.getTeam("line"+i);

        String entry = scoreToName(i);
        if (team != null) {
            team.getEntries().forEach(team::removeEntry);
            team.addEntry(entry);
        } else {
            team = originalScoreboard.registerNewTeam("line"+i);
            team.addEntry(entry);
        }

        String prefix = line.substring(0, 64);
        String suffix = line.substring(64);

        team.prefix(MiniMessage.miniMessage().deserialize(prefix));
        team.suffix(MiniMessage.miniMessage().deserialize(suffix));

        objective.getScore(entry).setScore(i);
    }

    /**
     * Set lines in scoreboard
     * @param lines
     */
    public void setLines(String... lines) {
        setLines(Arrays.stream(lines).collect(Collectors.toList()), true);
    }

    /**
     * Set lines in scoreboard
     * @param lines
     * @param reset
     */
    public void setLines(List<String> lines, boolean reset) {
        Collections.reverse(lines);

        if (reset) {
            originalScoreboard.clearSlot(DisplaySlot.SIDEBAR);
            originalScoreboard.getEntries().forEach(originalScoreboard::resetScores);
            originalScoreboard.getTeams().forEach(Team::unregister);

            createObjective();
        }

        int i = 1;
        for (String line : lines) {
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
                Arrays.stream(lines).collect(Collectors.toList())
        );
    }

    /**
     * Create scoreboard
     * @param title
     * @param lines
     * @return scoreboard
     */
    public static org.sharkurmc.utilities.minecraft.structures.Scoreboard createScoreboard(Component title, List<String> lines) {
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
        objective = originalScoreboard.registerNewObjective("dummy", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
}
