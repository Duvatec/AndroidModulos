package com.duvatec.androidmodulos.webservice.interfaces

import com.duvatec.androidmodulos.webservice.models.MModel

//Interface of view VExample
interface IExample {
    interface View {
        fun successResponse(model: MModel)
        fun errorResponse(error: String)
    }

    interface Presenter:
        View {
        fun getData(id: Int, customer: String, quantity: Int, price: Double)
    }
}