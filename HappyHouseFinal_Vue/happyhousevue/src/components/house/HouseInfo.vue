<template>
  <div>
    <div class="col-lg-6">
      <select v-model="sido" class="form-group" @change="sidoChange">
        <option value="" selected disabled hidden>도/광역시</option>
        <option v-for="(item, idx) in sidos" v-bind:value="item" v-bind:key="idx">{{ item }}</option>
      </select>
      <select v-model="gugun" class="form-group" @change="gugunChange">
        <option value="" selected disabled hidden>시/구/군</option>
        <option v-for="(item, idx) in guguns" v-bind:value="item" v-bind:key="idx">{{ item }}</option>
      </select>
      <select v-model="dong" class="form-group">
        <option value="" selected disabled hidden>동</option>
        <option v-for="(item, idx) in dongs" v-bind:value="item" v-bind:key="idx">{{ item }}</option>
      </select>
      <button @click="dongChange">검색</button>
      <div class="card shadow">
        <div class="card-body">
          <table class="table table-hover">
            <thead>
              <tr>
                <th class="text-center d-none d-md-table-cell">번호</th>
                <th class="text-center d-none d-md-table-cell">동</th>
                <th class="text-center d-none d-md-table-cell">주택 이름</th>
                <th class="text-center d-none d-md-table-cell">건설연도</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in items" v-bind:key="index">
                <td class="text-center d-none d-md-table-cell">
                  {{ item.no }}
                </td>
                <td class="text-center d-none d-md-table-cell">{{ item.dong }}</td>
                <td class="text-center d-none d-md-table-cell">
                  <router-link :to="'houseinforead/' + item.no">
                    {{ item.aptName }}
                  </router-link>
                </td>
                <td class="text-center d-none d-md-table-cell">{{ item.buildYear }}</td>
              </tr>
            </tbody>
          </table>

          <paginate
            class="d-none d-md-block"
            v-if="totalpages"
            v-model="page"
            :page-count="totalpages"
            :page-range="10"
            :margin-pages="0"
            :click-handler="clickCallback"
            :prev-text="'Prev'"
            :next-text="'Next'"
            :container-class="'pagination'"
            :page-class="'page-item'"
          >
          </paginate>
        </div>
      </div>
    </div>
    <div class="col-lg-6" id="map" style="width:500px;height:500px;"></div>
  </div>
</template>

<script>
import axios from "axios";
import Paginate from "vuejs-paginate";
import Vue from "vue";

Vue.component("paginate", Paginate);

export default {
  data() {
    return {
      items: [],
      page: 1,
      totalpages: 10,
      center: {
        lat: 37.5743822,
        lng: 126.9688505,
      },
      sidos: [
        "서울특별시",
        "부산광역시",
        "대구광역시",
        "인천광역시",
        "광주광역시",
        "대전광역시",
        "울산광역시",
        "세종특별자치시",
        "경기도",
        "강원도",
        "충청북도",
        "충청남도",
        "전라북도",
        "전라남도",
        "경상북도",
        "경상남도",
        "제주특별자치도",
      ],
      sido: "",
      guguns: [],
      gugun: "",
      dongs: [],
      dong: "",
    };
  },
  created() {
    this.addScript();
    axios.get("http://localhost:9999/happyhouse/houseinfo/all/" + 1).then(({ data }) => {
      console.log(data);
      this.items = data.data;
      this.center.lat = this.items[0].lat;
      this.center.lng = this.items[0].lng;
      console.log(data.totalPages);
      this.totalpages = data.totalPages;
    });
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  beforeUpdate() {
    this.initMap();
  },
  methods: {
    clickCallback(pageNum) {
      if (this.dong != "") {
        console.log(this.dong);
        this.dongChange();
      } else {
        console.log("none");
        axios.get("http://localhost:9999/happyhouse/houseinfo/all/" + pageNum).then(({ data }) => {
          console.log(data);
          this.items = data.data;
          this.center.lat = this.items[0].lat;
          this.center.lng = this.items[0].lng;

          this.initMap();
        });
      }
    },
    initMap() {
      let container = document.getElementById("map");
      let options = {
        center: new kakao.maps.LatLng(this.center.lat, this.center.lng),
        // center: new kakao.maps.LatLng(lat, lng),
        level: 3,
        // tileAnimation: false,
      };
      let map = new kakao.maps.Map(container, options);

      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      for (var i = 0; i < this.items.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // console.log(this.items[i].lat + "," + this.items[i].lng);
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(this.items[i].lat, this.items[i].lng), // 마커를 표시할 위치
          title: this.items[i].aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        marker.setMap(map);
      }
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */ script.onload = () => kakao.maps.load(this.initMap);
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ee8796f7267ad78a04e16e70d60e58a1&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    },
    sidoChange() {
      console.log(this.sido);
      axios
        .get("http://localhost:9999/happyhouse/houseinfo/gugunlist", {
          params: {
            province: this.sido,
          },
        })
        .then(({ data }) => {
          console.log(data.data);
          this.guguns = data.data;
        });
    },
    gugunChange() {
      console.log(this.gugun);
      axios
        .get("http://localhost:9999/happyhouse/houseinfo/donglist", {
          params: {
            city: this.gugun,
          },
        })
        .then(({ data }) => {
          console.log(data.data);
          this.dongs = data.data;
        });
    },
    dongChange() {
      console.log(this.dong);
      axios
        .get("http://localhost:9999/happyhouse/houseinfo/dong", {
          params: {
            dong: this.dong,
            pageno: this.page,
          },
        })
        .then(({ data }) => {
          console.log(data.data);
          this.items = data.data;
          this.center.lat = this.items[0].lat;
          this.center.lng = this.items[0].lng;
          this.initMap();
        });
    },
  },
};
</script>

<style scoped lang="scss">
// @import "@/assets/stylesheets/index.scss";
// #map {
//   @include css-size(300px, 300px);
// }

.pagination {
}
.page-item {
}
</style>
