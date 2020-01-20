package br.com.base.common.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toPrice() = "R$ ${this.setScale(2, RoundingMode.HALF_UP)}".replace(".", ",")
