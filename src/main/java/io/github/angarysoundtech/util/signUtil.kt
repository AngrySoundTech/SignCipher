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
package io.github.angarysoundtech.util

import io.github.angarysoundtech.SignCipher
import net.minecraft.tileentity.SignTileEntity
import net.minecraft.util.text.StringTextComponent

private val TAG_REGEX = Regex("^\\[(.+)]$")

fun getKeyForSign(te: SignTileEntity): String? {
    val tag = getTagForSign(te)

    if (tag != null) {
        if (SignCipher.library.keys.containsKey(tag)) {
            return SignCipher.library.keys[tag]
        }
    }

    return null
}

fun getTagForSign(te: SignTileEntity): String? {
    val match = TAG_REGEX.find(te.getText(0).unformattedComponentText)

    if (match != null) {
        return match.groupValues[1]
    }

    return null
}

fun encryptSign(te: SignTileEntity, key: String) {
    for (i in 1..3) {
        val encrypted = encrypt(te.getText(i).formattedText, key)
        te.setText(i, StringTextComponent(encrypted))
    }
}

fun decryptSign(te: SignTileEntity, key: String, tag: String) {
    for (i in 1..3) {
        val decrypted = decrypt(te.getText(i).formattedText, key)
        te.setText(i, StringTextComponent(decrypted))
    }

    // Set the first line of the sign to something
    // that doesn't match, so it doesn't try to decrypt again
    te.setText(0, StringTextComponent("<$tag>"))
}