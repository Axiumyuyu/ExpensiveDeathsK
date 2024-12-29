package me.axiumyu.expensivedeathsk

import me.axiumyu.expensivedeathsk.ExpensiveDeathsK.Companion.config
import me.axiumyu.expensivedeathsk.ExpensiveDeathsK.Companion.loadConfig
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object ReloadConfig : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (!p0.hasPermission("expensivedeathsk.admin")) {
            p0.sendMessage("You do not have permission to use this command")
            return false
        }
        p3 ?: return false
        if (p3.isEmpty()) return false

        when (p3[0]){
            "reload" -> {
                p0.sendMessage("Reloading Config")
                loadConfig()
                p0.sendMessage("Reload Complete.")
            }
            "set" -> {
                if (p3.size < 2) return false
                val key = p3[1]
                val value = p3[2]
                when (key.lowercase()){
                    "mincost" -> config.set("mincost", value.toInt())

                    "maxcost" -> config.set("maxcost", value.toInt())
                    "minloseheart" -> config.set("minloseheart", value.toInt())
                    "maxloseheart" -> config.set("maxloseheart", value.toInt())
                }
                loadConfig()
                p0.sendMessage("Set $key to $value")
            }
        }

        return true
    }
}