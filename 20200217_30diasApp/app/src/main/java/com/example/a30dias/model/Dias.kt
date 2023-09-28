package com.example.a30dias.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dias(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val stringTextId: Int
)
