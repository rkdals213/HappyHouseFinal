<template>
  <div>
    <div class="col-lg-6">
      <div class="card shadow">
        <div class="card-body">
          <h4>{{ houseinfo.aptName }}</h4>
          <!-- <img src="{{img}}" /> -->
          <!-- <img :src="require(`${houseinfo.img}`)" /><br /> -->
          <img class="aptimg" :src="houseinfo.img" /><br />

          <!-- <img class="aptimg" src="../../image/default.jpg" /> <br /> -->
          주소: {{ location }} <br />건설연도: {{ houseinfo.buildYear }}<br />
          <div>
            <div @click="searchPlace('FD6')" class="inline">
              <img src="../../image/restaurant.jpg" alt="" />
            </div>
            <div @click="searchPlace('CE7')" class="inline">
              <img src="../../image/cafe.jpg" alt="" />
            </div>
            <div @click="searchPlace('SW8')" class="inline">
              <img src="../../image/subway.jpg" alt="" />
            </div>
            <div @click="searchPlace('MT1')" class="inline">
              <img src="../../image/mart.jpg" alt="" />
            </div>
          </div>
          <div>
            <div @click="searchPlace('HP8')" class="inline">
              <img src="../../image/hospital.jpg" alt="" />
            </div>
            <div @click="searchPlace('PM9')" class="inline">
              <img src="../../image/pharmacy.jpg" alt="" />
            </div>
            <div @click="searchPlace('CS2')" class="inline">
              <img src="../../image/convi.png" alt="" />
            </div>
            <div @click="searchPlace('BK9')" class="inline">
              <img src="../../image/bank.png" alt="" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-6" id="map" style="width:500px;height:500px;"></div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      houseinfo: {},
      location: "",
      center: {
        lat: 37.5743822,
        lng: 126.9688505,
      },
      address: "",
      map: {},
      container: {},
      options: {},
      markers: [],
      marker: {},
      infowindows: [],
      infowindow: {},
      img: "../../image/default.jpg",
    };
  },
  created() {
    console.log("read:" + this.$route.params.no);
    axios.get("http://localhost:9999/happyhouse/houseinfo/" + this.$route.params.no).then(({ data }) => {
      console.log(data);
      this.houseinfo = data.data;
      console.log(this.houseinfo);
      this.center.lat = this.houseinfo.lat;
      this.center.lng = this.houseinfo.lng;
      this.img = this.houseinfo.img;
      this.searchDetailAddrFromCoords({ lng: this.houseinfo.lng, lat: this.houseinfo.lat }, this.setaddress);
    });
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  beforeUpdate() {
    this.initMap();
  },
  methods: {
    searchPlace(place) {
      console.log(place);
      this.removeMarker();
      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(this.map);
      // 카테고리로 은행을 검색합니다
      ps.categorySearch(place, this.placesSearchCB, { useMapBounds: true });
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          console.log(data[i]);
          this.displayMarker(data[i]);
        }
      }
    },
    displayMarker(place) {
      // var map = new kakao.maps.Map(mapContainer, mapOption);
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // 마커를 생성하고 지도에 표시합니다
      this.marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });
      this.markers.push(this.marker);
      this.infowindows.push(this.infowindow);
      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(this.marker, "click", function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        console.log("click");
        console.log(place.place_name);
        console.log(infowindow);
        console.log(place);
        alert("이름: " + place.place_name + "\n주소: " + place.address_name + "\n카테고리: " + place.category_name + "\n전화번호: " + place.phone);
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + "</div>");
        infowindow.open(this.map, this.marker);
      });
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    searchAddrFromCoords(lng, lat, setaddress) {
      // 좌표로 행  정동 주소 정보를 요청합니다
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.coord2RegionCode(lng, lat, setaddress);
    },
    setaddress(result, status) {
      console.log({ result, status });
      if (status === kakao.maps.services.Status.OK) {
        this.location = result[0].address.address_name;
      } else {
        console.log("not ok");
      }
    },
    searchDetailAddrFromCoords(coords, callback) {
      // 좌표로 법정동 상세 주소 정보를 요청합니다
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.coord2Address(coords.lng, coords.lat, callback);
    },

    initMap() {
      this.container = document.getElementById("map");
      this.options = {
        center: new kakao.maps.LatLng(this.center.lat, this.center.lng),
        // center: new kakao.maps.LatLng(lat, lng),
        level: 3,
        // tileAnimation: false,
      };
      this.map = new kakao.maps.Map(this.container, this.options);

      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      //마커추가하려면 객체를 아래와 같이 하나 만든다.

      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // console.log(this.items[i].lat + "," + this.items[i].lng);
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: this.map, // 마커를 표시할 지도
        position: new kakao.maps.LatLng(this.houseinfo.lat, this.houseinfo.lng), // 마커를 표시할 위치
        title: this.houseinfo.aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
      this.searchAddrFromCoords(this.houseinfo.lng, this.houseinfo.lat, null);
      marker.setMap(this.map);
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */ script.onload = () => kakao.maps.load(this.initMap);
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ee8796f7267ad78a04e16e70d60e58a1&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    },
  },
};
</script>

<style scoped>
.inline {
  display: inline;
}
.inline > img {
  width: 70px;
  height: 70px;
  margin: 5px;
}
.aptimg {
  height: 200px;
  width: 300px;
}
</style>
