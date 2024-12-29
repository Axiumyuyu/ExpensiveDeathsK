package me.axiumyu.expensivedeathsk

import me.yic.xconomy.api.XConomyAPI
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class ExpensiveDeathsK : JavaPlugin() {
    companion object{
        lateinit var xc: XConomyAPI
        lateinit var config: FileConfiguration

        @JvmStatic
        fun loadConfig(){
            Config.maxCost = config.getInt("max-cost",500)
            Config.minCost = config.getInt("min-cost",50)
            Config.maxLoseHeart = config.getInt("max-lose-hearts",4)
            Config.minLoseHeart =  config.getInt("min-lose-hearts",1)
        }
    }

    override fun onEnable() {
        // Plugin startup logic
        val pm: PluginManager = server.pluginManager
        if (pm.isPluginEnabled("XConomy")) {
            pm.registerEvents(OnDeath, this)
            xc = XConomyAPI()
            Companion.config= this.config
            saveDefaultConfig()
            loadConfig()
            getCommand("expdeath")?.setExecutor(ReloadConfig)
        }else{
            logger.severe {
                "XConomy is not enabled! Disabling ExpensiveDeathsK"
            }
            pm.disablePlugin(this)
        }
    }


}
