# SignCipher

Encrypt the contents of a sign in minecraft!

SignCipher uses a Vigenère Cipher, a series of interwoven Caesar ciphers,
to encrypt the contents of a sign in minecraft with a given key.

These signs will only be readable to others who both have the mod installed,
and have the correct key to the tag on the sign. In a shellnut, if someone
doesn't have the key, they won't be able to read your sign!

## Installation
1. Install [Kottle] (This mod depends on it)
2. Install This mod.

## How to use

Keys are stored in `.minecraft/signcipher/keys.json`.
When adding or removing keys, make sure that the file stays in proper json format.
Keys are currently only read once, when you start the game.

When encrypting a sign, simply type the tag of the key you want to use, in square brackets (`[tag_name]`),
and press the Encrypt button.

When a sign is decrypted, the `[tag]` will be replaced with "<tag>", indicating that it has been
decrypted.

## Developing / Compiling
1. `gradle [setupDevWorkspace|setupDecompWorkspace] [eclipse|idea]`
2. `gradle build`

## FAQ

### Can I Use this in a modpack?
Yes. You don't need to ask.

### How secure is the encryption?
For all intents and purposes, as secure as you keep the key.
Common Vigenère "breakers" won't work, because the character set
has been expanded past the simple polyalphabetic substitution.

### I want to help!
Awesome! Please read the Code of Conduct, and Contribution guide in our Github repository.

### How do I contact you?
Discord: SoundTech#1464.
Please read the FAQ before asking any questions.

[kottle]: https://www.curseforge.com/minecraft/mc-mods/kottle
