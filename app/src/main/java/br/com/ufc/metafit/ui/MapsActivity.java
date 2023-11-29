package br.com.ufc.metafit.ui

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.ufc.metafit.R
import br.com.ufc.metafit.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationManager: LocationManager
    private lateinit var databaseReference: DatabaseReference
    private val temporalRealTimeMarkers = ArrayList<Marker>()
    private val realTimeMarkers = ArrayList<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        databaseReference = FirebaseDatabase.getInstance().getReference()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val location = LatLng(-4.9793637, -39.0589341)
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("A definir")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_tenda))
                .anchor(0.0f, 0.0f)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(20f)
            .bearing(90f)
            .tilt(45f)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = false

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val minhaEntrega = LatLng(location.latitude, location.longitude)
                mMap.addMarker(
                    MarkerOptions()
                        .position(minhaEntrega)
                        .title("Delivery")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_delivery))
                        .anchor(0.0f, 0.0f)
                )
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}
        }

        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0,
                0f,
                locationListener
            )

            databaseReference.child("destinos").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (dest in realTimeMarkers) {
                        dest.remove()
                    }

                    for (snapshot in dataSnapshot.children) {
                        val dt = snapshot.getValue(Destinos::class.java)
                        val latitude = dt?.latitud ?: 0.0
                        val longitude = dt?.longitud ?: 0.0
                        val codigo = dt?.codigo ?: ""
                        val telefone = dt?.telefone ?: ""
                        val caixa = "N° Caixa $codigo"
                        val telefoneUm = "Tel. $telefone"

                        val markerOptions = MarkerOptions()
                            .position(LatLng(latitude, longitude))
                            .title(caixa)
                            .snippet(telefoneUm)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_entregas))

                        val marker = mMap.addMarker(markerOptions)
                        if (marker != null) {
                            temporalRealTimeMarkers.add(marker)
                        }
                        //temporalRealTimeMarkers.add(mMap.addMarker(markerOptions))
                    }

                    realTimeMarkers.clear()
                    realTimeMarkers.addAll(temporalRealTimeMarkers)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }
    }
}
