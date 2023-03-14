package com.example.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txt.setOnClickListener {
            Log.d("I am here","clcivk")
            if (hasStoragePermission()) {
                Toast.makeText(this,"permission present already",Toast.LENGTH_SHORT).show()
            } else {
                checkPermission()
            }
        }
    }

    private fun hasStoragePermission() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLOcationPermission() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED



    private fun checkPermission()
    {
        val requests = mutableListOf<String>()

        if (!hasStoragePermission()) {
            requests.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!hasLOcationPermission()) {
            requests.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }


        if (requests.isNotEmpty()) {

            ActivityCompat.requestPermissions(this,requests.toTypedArray(),0)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 0 && grantResults.isNotEmpty()) {

            for (i in grantResults.indices) {

                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permission"," ${permissions[i]} graneted")
                }
            }

        }
    }
}