package com.hyperdesign.delivaryUser.common.data.models

object Const {
    val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]{8,50}\$")
    val nameRegex = Regex("^[A-Za-z\\s]{3,50}\$")
    val phoneRegex = Regex("^\\+?[0-9]{9,15}\$")
    val optioinalPhoneRegex =Regex("^(\\+?[0-9]{9,15})?$")

}