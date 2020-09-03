package com.duvatec.androidmodulos.webservice.models

import com.google.gson.annotations.SerializedName

class MModel {
    @SerializedName("success") // Web service label
    var isOk: Boolean = false // Variable used in the model
}