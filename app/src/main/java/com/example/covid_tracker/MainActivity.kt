package com.example.covid_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.Response



class MainActivity : AppCompatActivity() {

    lateinit var stateAdapter: StateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // rv.addHeaderView(LayoutInflater.from(this).inflate(R.))

        fetchResult();
    }


    private fun fetchResult() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.execute() }
            if(response.isSuccessful)
            {
             val data = Gson().fromJson(response.body?.string(),com.example.covid_tracker.Response::class.java)

                launch(Dispatchers.Main) {
                    bindData(data.statewise[0])
                    bindList(data.statewise.subList(1,data.statewise.size))
                }

            }
        }
    }

    private fun bindList(subList: List<StatewiseItem>) {

stateAdapter= StateAdapter(subList)
        rv.adapter= stateAdapter
    }

    private fun bindData(data: StatewiseItem) {

       confirmCase.text=data.confirmed
        activeCase.text = data.active
        deathCase.text= data.deaths
        recoveredCase.text=data.recovered


    }


}