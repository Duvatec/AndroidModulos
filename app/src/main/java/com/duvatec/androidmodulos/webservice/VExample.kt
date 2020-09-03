package com.duvatec.androidmodulos.webservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duvatec.androidmodulos.R
import com.duvatec.androidmodulos.webservice.interfaces.IExample
import com.duvatec.androidmodulos.webservice.models.MModel
import com.duvatec.androidmodulos.webservice.presenters.PExample
import kotlinx.android.synthetic.main.activity_main.*

class VExample: AppCompatActivity(), IExample.View {

    lateinit var presenter: PExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = PExample(this)
        presenter.getData(78912, "Jason Sweet", 1, 18.00)

    }

    override fun successResponse(model: MModel) {
        amTvResponse.text = "La espuesta es:\n${model.isOk}"
    }

    override fun errorResponse(error: String) {
        amTvResponse.text = "El error es:\n$error"
    }
}