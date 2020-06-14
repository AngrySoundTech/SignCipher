/**
 * Copyright (C) 2020 AngrySoundTech
 * This file is part of SignPad.
 *
 * SignPad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SignPad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SignPad.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.angarysoundtech.util

const val FIRST_CHAR = ' '
const val LAST_CHAR = '~'
const val SIZE = LAST_CHAR - FIRST_CHAR

fun encrypt(text: String, key: String): String {
    return vigenere(text, key, true)
}

fun decrypt(text: String, key: String): String {
    return vigenere(text, key, false)
}

private fun vigenere(text: String, key: String, encrypt: Boolean = true): String {
    val sb = StringBuilder()

    for (i in text.indices) {
        val c = text[i]

        if (c < FIRST_CHAR || c > LAST_CHAR) {
            continue
        }

        val o = if (encrypt) {
            (c + key[i % key.length].toInt() - 2 * FIRST_CHAR.toInt()).toInt() % SIZE + FIRST_CHAR.toInt()
        } else {
            (c - key[i  % key.length].toInt() + SIZE).toInt() % SIZE + FIRST_CHAR.toInt()
        }

        sb.append(o.toChar())
    }

    return sb.toString()
}

