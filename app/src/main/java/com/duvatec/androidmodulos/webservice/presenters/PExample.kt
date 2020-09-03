package com.duvatec.androidmodulos.webservice.presenters

import com.duvatec.androidmodulos.webservice.networking.DataManager
import com.duvatec.androidmodulos.webservice.interfaces.IExample
import com.duvatec.androidmodulos.webservice.models.MModel
import org.json.JSONObject

//Presenter of activity VExample
class PExample(view: IExample.View) :
    IExample.Presenter {

    var view: IExample.View? = view

    override fun getData(id: Int, customer: String, quantity: Int, price: Double) {
        val url = "https://reqbin.com/echo/post/json" //Url from Web Service

        val jsonObject = JSONObject()
        jsonObject.put("Id", id)
        jsonObject.put("Customer", customer)
        jsonObject.put("Quantity", quantity)
        jsonObject.put("Price", price)

        val dm = DataManager()
        dm.petitionPost<MModel>(
            url,
            jsonObject = jsonObject,
            onRes = { r -> successResponse(r) },
            onErr = { e -> errorResponse(e) }
        )
    }

    override fun successResponse(model: MModel) {
        view?.successResponse(model)
    }

    override fun errorResponse(error: String) {
        view?.errorResponse(error)
    }

}