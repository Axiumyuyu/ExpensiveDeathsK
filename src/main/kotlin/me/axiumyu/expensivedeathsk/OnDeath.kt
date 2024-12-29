package me.axiumyu.expensivedeathsk

import me.axiumyu.expensivedeathsk.ExpensiveDeathsK.Companion.xc
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import kotlin.random.Random

object OnDeath : Listener {
    @EventHandler
    fun onDeath(e: PlayerDeathEvent){
        val player = e.player
        if (player.hasPermission("expensivedeathsk.pardon")) return
        val cost = (Random.nextFloat() * Config.maxCost + Config.minCost).toBigDecimal()
        val loseHeart = (Random.nextFloat() * Config.maxLoseHeart + Config.minLoseHeart).toInt()
        xc.changePlayerBalance(player.uniqueId,player.name,cost,false)
        val plMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!
        plMaxHealth.baseValue -= loseHeart.toDouble()
        player.sendMessage("你死了，损失了${loseHeart}点生命值上限，损失了${cost}纯净结晶")
    }
}