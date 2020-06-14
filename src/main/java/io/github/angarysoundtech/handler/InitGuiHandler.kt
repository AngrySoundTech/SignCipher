/**
 * Copyright (C) 2020 AngrySoundTech
 * This file is part of SignCipher.
 *
 * SignCipher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SignCipher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SignCipher.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.angarysoundtech.handler

import io.github.angarysoundtech.MOD_ID
import io.github.angarysoundtech.SignCipher
import io.github.angarysoundtech.util.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screen.EditSignScreen
import net.minecraft.client.gui.widget.Widget
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.client.resources.I18n
import net.minecraft.tileentity.SignTileEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.Style
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import kotlin.math.max

@Mod.EventBusSubscriber(Dist.CLIENT, modid = MOD_ID)
object InitGuiHandler {

    @SubscribeEvent
    fun onInitGuiEvent(event: GuiScreenEvent.InitGuiEvent) {
        val gui = event.gui

        if (gui is EditSignScreen) {
            var maxY = 0
            for (button: Widget in event.widgetList) {
                maxY = max(button.y, maxY)
            }

            val tileField = EditSignScreen::class.java.getDeclaredField("tileSign")
            tileField.isAccessible = true
            val tile = tileField.get(event.gui) as SignTileEntity

            event.addWidget(Button(gui.width / 2 - 100, maxY + 24, 200, 20, I18n.format("signcipher.gui.encrypt")) { button ->
                val tag = getTagForSign(tile)

                if (tag == null) {
                    Minecraft.getInstance().player.sendMessage(
                        StringTextComponent(I18n.format("signcipher.chat.badtag", tile.getText(0).formattedText))
                            .setStyle(Style().setColor(TextFormatting.RED))
                    )
                } else { // If the tag is formatted properly
                    val key = getKeyForSign(tile)

                    if (key != null) {
                        encryptSign(tile, key)
                    } else { // If we don't know the key to use
                        Minecraft.getInstance().player.sendMessage(
                            StringTextComponent(I18n.format("signcipher.chat.unknowntag", tag))
                                .setStyle(Style().setColor(TextFormatting.RED))
                        )
                    }
                }


                tile.markDirty()
                gui.minecraft.displayGuiScreen(null)
            })
        }
    }
}