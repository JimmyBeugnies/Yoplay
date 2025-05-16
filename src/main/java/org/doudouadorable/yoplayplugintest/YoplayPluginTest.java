// Alors là jimporte les truc qu'il me faut pour mon plugin
package org.doudouadorable.yoplayplugintest;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

// C'est la classe principal de mon plugin, elle hérite de JavaPlugin
public final class YoplayPluginTest extends JavaPlugin {

    // Cette méthode elle s'execute quand le plugin s'active
    @Override
    public void onEnable() {
        // J'affiche un message dans la console pour dire que le plugin est activé
        getLogger().info("YoplayPluginTest activé !");
    }

    // Cette méthode elle s'execute quand le plugin se désactive
    @Override
    public void onDisable() {
        // J'affiche un message dans la console pour dire que le plugin est désactivé
        getLogger().info("YoplayPluginTest désactivé !");
    }

    // Cette méthode elle gère les commandes que les joueurs tapent
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Si la commande c'est "particule"
        if (command.getName().equalsIgnoreCase("particule")) {
            // Je vérifie si c'est un joueur qui a tapé la commande
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // J'envoie un message au joueur pour lui dire que des particules vont apparaître
                player.sendMessage("§aDes particules apparaissent autour de vous.");
                // J'envoie un autre message pour dire que j'utilise une tâche répétitive
                player.sendMessage("§7Une tâche répétitive (BukkitRunnable) est utilisée pour générer les particules toutes les 0.5 secondes pendant 10 secondes.");

                // Je crée une tâche qui va se répéter pour générer des particules
                new BukkitRunnable() {
                    int count = 0;

                    @Override
                    public void run() {
                        // Si on a déjà exécuté la tâche 20 fois (10 secondes), on l'arrête
                        if (count >= 20) {
                            cancel();
                            return;
                        }

                        // Je fais apparaître des particules en forme de cœur autour du joueur
                        player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0, 1, 0), 10, 0.5, 0.5, 0.5, 0.01);
                        count++;
                    }
                }.runTaskTimer(this, 0L, 10L); // Je démarre la tâche tout de suite et elle se répète toutes les 10 ticks (0.5 seconde)

                return true;
            }
        }
        return false;
    }
}
