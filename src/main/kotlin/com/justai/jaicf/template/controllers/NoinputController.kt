package com.justai.jaicf.template.controllers

import com.justai.jaicf.context.BotContext

class NoinputController(context: BotContext) {
    var attempts: Int? by context.session
    var attemptsTemp: Int? by context.temp

}