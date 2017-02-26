package com.example.anusara.bcv2.GPS;

import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.example.anusara.bcv2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            // Show rationale and request permission.
        }
        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.9047413, 100.5242884))
                .title("โรงพยาบาลเวิลด์เมดิคอลเซ็นเตอร์")
                .snippet("44 หมู่ 4, ถนนแจ้งวัฒนะ, ตำบลปากเกร็ด อำเภอปากเกร็ด จังหวัดนนทบุรี, 11120"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.855966, 100.5427659))
                .title("โรงพยาบาล นนทเวช")
                .snippet("30/8 ถนน งามวงศ์วาน อำเภอเมืองนนทบุรี"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.835429, 100.574193))
                .title("โรงพยาบาลเปาโล เกษตร")
                .snippet("2012/5-6 Phaholyothin Road, แขวง เสนานิคม, เขต จตุจักร"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8805212, 100.5781228))
                .title("โรงพยาบาลจุฬาภรณ์")
                .snippet("54 กำแพงเพชร 6 Chang Wat Bangkok จังหวัด กรุงเทพมหานคร"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.831756, 100.538655))
                .title("โรงพยาบาล เกษมราษฎร์ ประชาชื่น")
                .snippet("953 Pracha Bangsue. Bangkok 10800"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8451503, 100.5172667))
                .title("โรงพยาบาลศรีธัญญา")
                .snippet("47 ถนน ติวานนท์ ตำบล ตลาดขวัญ อำเภอเมืองนนทบุรี"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8940435, 100.5616074))
                .title("โรงพยาบาลมงกุฎวัฒนะ")
                .snippet("34/40 Chaengwattana Rd แขวง ทุ่งสองห้อง เขต หลักสี่"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8462614, 100.5620685))
                .title("บริษัทโรงพยาบาลวิภาวดีจำกัดมหาชน")
                .snippet("51/3 ถนนงามวงศ์วาน แขวงลาดยาว เขต จตุจักร"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.9130382, 100.5090856))
                .title("โรงพยาบาล กรุงไทย")
                .snippet("56/96 อำเภอ ปากเกร็ด"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8618809, 100.521635))
                .title("สถาบันโรคทรวงอก")
                .snippet("74 ถนนติวานนท์ ตำบลบางกระสอ อำเภอเมืองนนทบุรี"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8941089, 100.5245956))
                .title("บำรุงราษฎร์ นิชดา แฟมิลี่ คลินิก")
                .snippet("ตำบล บางตลาด อำเภอ ปากเกร็ด"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.9024494, 100.5800837))
                .title("ศูนย์บริการสาธารณสุข 53 ทุ่งสองห้อง")
                .snippet("กำแพงเพชร 6 ซอย 7 กำแพงเพชร 6 แขวง ทุ่งสองห้อง เขต หลักสี่"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8343559, 100.6642078))
                .title("โรงพยาบาลสินแพทย์")
                .snippet("9/99 ถนนรามอินทรา ก.ม.8.5 แขวงคันนายาว เขตคันนายาว กรุงเทพฯ 10230"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8979676, 100.5037162))
                .title("โรงพยาบาล ชลประทาน")
                .snippet("หมู่ 1 ติวานนท์ 222 ตำบล บางตลาด อำเภอ ปากเกร็ด นนทบุรี 11120"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.7140132,100.5854883))
                .title("โรงพยาบาล กล้วยน้ำไท")
                .snippet("ถนน สุขุมวิท แขวง พระโขนง เขต คลองเตย กรุงเทพมหานคร 10110"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8049412,100.5322515))
                .title("เซ็นทรัลเมดิกเกษตรคลินิกเวชกรรม")
                .snippet("งามวงศ์วาน บางเขน จตุจักร กทม. 10900"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8354342,100.5720043))
                .title("ร.พ.เมโย")
                .snippet("2012/5 - 7  ถ.พหลโยธิน  ลาดยาว  จตุจักร  กรุงเทพฯ  10900"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.8462666,100.5598798))
                .title("ร.พ.วิภาวดี 1")
                .snippet("51/3  ถ.งามวงศ์วาน    จตุจักร  กรุงเทพฯ  10900 "));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.6816042,100.4725233))
                .title("ร.พ.บางปะกอก 9 อินเตอร์เนชั่นแนล")
                .snippet("SNIPPET"));

//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(13.9047413, 100.5242884))
//                .title("โรงพยาบาลเวิลด์เมดิคอลเซ็นเตอร์ /n 44 หมู่ 4, ถนนแจ้งวัฒนะ, ตำบลปากเกร็ด อำเภอปากเกร็ด จังหวัดนนทบุรี, 11120")
//                .snippet("SNIPPET"));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(13.9047413, 100.5242884))
//                .title("โรงพยาบาลเวิลด์เมดิคอลเซ็นเตอร์ /n 44 หมู่ 4, ถนนแจ้งวัฒนะ, ตำบลปากเกร็ด อำเภอปากเกร็ด จังหวัดนนทบุรี, 11120")
//                .snippet("SNIPPET"));






//        // Add a marker in Sydney and move the camera
        LatLng thailand = new LatLng(13.725109, 100.3522138);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thailand));


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

}
