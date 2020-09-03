package com.duvatec.androidmodulos.webservice.networking

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.duvatec.androidmodulos.webservice.util.DefaultExclusionStrategy
import com.google.gson.GsonBuilder
import org.json.JSONObject

open class DataManager {

    inline fun <reified T> responseModel(response: String): T {
        /*
        This method allows to pass from a json to an object, this is used when the model uses the tag @SerializedName
        */
        val builder = GsonBuilder()
        builder.setExclusionStrategies(DefaultExclusionStrategy())
        val gSon = builder.create()
        return gSon.fromJson(response, T::class.java)
    }

    inline fun <reified T> petitionPost(
        endPoint: String,
        jsonObject: JSONObject,
        crossinline onRes: (r: T) -> Unit,
        crossinline onErr: (e: String) -> Unit
    ) {

        AndroidNetworking.post(endPoint)
            .addJSONObjectBody(jsonObject)
            .setPriority(Priority.HIGH)
            //.addHeaders("","") You can add headers or check the library documentation to customize headers and body
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // Example response
                    /*
                    {
                        "success": "true"
                    }
                    */
                    try {
                        onRes(responseModel<T>(response.toString()))
                    } catch (e: Exception){
                        /*
                        The error can be handled before being sent to the method so that it can be sent directly to the view
                        */
                        onErr("My custom error")
                    }
                }

                override fun onError(anError: ANError) {
                    /*
                    The error can be handled before being sent to the method so that it can be sent directly to the view
                    */
                    onErr(anError.errorDetail.toString())
                }

            })
    }
}