package com.tangem.commands.common.card.masks

class WalletSettingsMask(var rawValue: Int) {

    fun contains(setting: WalletSetting): Boolean = (rawValue and setting.code) != 0

    fun toList(): List<WalletSetting> = WalletSetting.values().filter { contains(it) }.map { it }
}

enum class WalletSetting(val code: Int) {
    IsReusable(0x0001),
    ProhibitPurgeWallet(0x0004)
}

class WalletSettingsMaskBuilder {
    private var settingsMaskValue = 0

    fun add(settings: WalletSetting) {
        settingsMaskValue = settingsMaskValue or settings.code
    }

    fun build(): WalletSettingsMask = WalletSettingsMask(settingsMaskValue)
}