package com.mtdl.pooapp


import android.Manifest
import android.content.Intent


import android.content.pm.PackageManager
import android.location.Location
import android.os.Build

import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle


import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.location.*

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mtdl.pooapp.databinding.ActivityMapsBinding
import com.mtdl.pooapp.user.User
import kotlin.random.Random


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        private lateinit var mMap: GoogleMap

        private const val REQUEST_CODE_PERMISSIONS = 10

    }


    private lateinit var binding: ActivityMapsBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var fab: FloatingActionButton


    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */



    private var message : String? = null

    protected var latitude: Double = 0.0// 44.439663
    protected var longitude:Double = 0.0//26.096306





    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val REQUIRED_PERMISSIONS =
        mutableListOf (
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()

    @RequiresApi(Build.VERSION_CODES.Q)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        message = intent.getStringExtra("EXTRA_MESSAGE")

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        fab = findViewById(R.id.floating_action_button)
        fab.setOnClickListener{
            addDevice();
        }
        fab.show()

        fab.setOnClickListener{
            val intent = Intent(this@MapsActivity, AddDeviceActivity::class.java)
            startActivity(intent)
        }


       // locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?;


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        updateLoc();
        // Add a marker in Sydney and move the camera
        val bucharest = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(bucharest).title("Marker in Bucharest"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bucharest))

        var indx = 0;
        for ( b in User.userInstance().getBoards()){
            indx +=1
         val pos = LatLng(b.latLng.first + Random.nextDouble(),b.latLng.second + Random.nextDouble());//LatLng(Random.nextDouble(), Random.nextDouble())//LatLng(b.latLng.first,b.latLng.second);

          mMap.addMarker(MarkerOptions().position(pos).title("Marker "+indx))
//            Toast.makeText(this,
//                "board at " + b.sensorList.toString(),
//                Toast.LENGTH_SHORT).show()
//            //mMap.moveCamera(CameraUpdateFactory.newLatLng(bucharest))
        }
    }

    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@MapsActivity, ViewMyBoardsActivity::class.java)
        startActivity(intent)
        return actionBarDrawerToggle.onOptionsItemSelected(item)
    }

    protected fun startLocationUpdates() {
        // initialize location request object
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            setInterval(UPDATE_INTERVAL)
            setFastestInterval(FASTEST_INTERVAL)
        }

        // initialize location setting request builder object
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // initialize location service object
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient!!.checkLocationSettings(locationSettingsRequest)

        // call register location listener
        registerLocationListner()
    }

    private fun registerLocationListner() {
        // initialize location callback object
        Toast.makeText(this@MapsActivity,"msg111111111", Toast.LENGTH_LONG).show()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                Toast.makeText(this@MapsActivity,"msg222222222", Toast.LENGTH_LONG).show()
                onLocationChanged(p0!!.getLastLocation())
            }

        }
        // 4. add permission if android version is greater then 23
        if(Build.VERSION.SDK_INT >= 23 && checkPermission()) {
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest!!, locationCallback, Looper.myLooper()!!)
            Toast.makeText(this,"msg", Toast.LENGTH_LONG).show()
            User.setCurentLocation(latitude, longitude)
        }
    }

    //
    private fun onLocationChanged(location: Location) {
        // create message for toast with updated latitude and longitudefa
        var msg = "Updated Location: " + location.latitude  + " , " +location.longitude

        // show toast message with updated location
        //Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        Log.d("Message int", message!!)
        if(message!!.equals("Add")) {
            Log.d("MARKER", "Adding")
            val location = LatLng(location.latitude, location.longitude)
            mMap!!.clear()
            mMap!!.addMarker(MarkerOptions().position(location).title("Current Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        }
    }

    private fun checkPermission() : Boolean {
        if (ContextCompat.checkSelfPermission(this , android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions()
            return false
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"),1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if (permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION ) {
                registerLocationListner()
            }
        }
    }


    fun addDevice(){
        val intent = Intent(this, AddDeviceActivity::class.java)
        startActivity(intent)
    }






    fun updateLoc(){
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                REQUIRED_PERMISSIONS,
//                Companion.REQUEST_CODE_PERMISSIONS
//            )
//            return
//        }
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//                    Toast.makeText(this,
//                "fused location " ,
//                Toast.LENGTH_SHORT).show()
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location : Location? ->
//                latitude = 40.00;
//                longitude = 60.00
//
//
//                if (location != null) {
//                    latitude = location.getLatitude()
//                    Toast.makeText(this,
//                        "lat $latitude",
//                        Toast.LENGTH_SHORT).show()
//                };
//                if (location != null) {
//                    longitude = location.getLongitude()
//                    Toast.makeText(this,
//                        "lat $longitude",
//                        Toast.LENGTH_SHORT).show()
//                };
//
//            }
//
//
    }


}