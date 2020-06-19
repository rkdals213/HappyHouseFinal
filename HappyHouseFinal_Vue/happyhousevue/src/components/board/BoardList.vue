<template>
    <!-- 게시글 리스트 -->
    <div class="container" style="margin-top:100px;">
        <div class="card-body col-lg-2">
            <ul class="menunav">
                <li><a class="active" href="#home">카테고리</a></li>
                <li><router-link to="/boardlist/1/1">밥 번개</router-link></li>
                <li><router-link to="/boardlist/2/1">취미 공유</router-link></li>
                <li><router-link to="/boardlist/3/1">자취 꿀팁</router-link></li>
            </ul>
        </div>
        <div class="col-lg-10">
            <h4 class="text-left h3" v-if="board_idx == 1">밥 번개</h4>
            <h4 class="text-left h3" v-else-if="this.board_idx == 2">취미 공유</h4>
            <h4 class="text-left h3" v-else-if="this.board_idx == 3">자취 꿀팁</h4>
            <table class="table table-hover" id="board_list">
                <thead>
                    <tr>
                        <th class="text-center d-none d-md-table-cell">글번호</th>
                        <th class="text-center d-none d-md-table-cell">제목</th>
                        <th class="text-center d-none d-md-table-cell">작성자</th>
                        <th class="text-center d-none d-md-table-cell">작성날짜</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(obj, idx) in server_data" v-bind:key="idx">
                        <td class="text-center d-none d-md-table-cell">{{ obj.no }}</td>
                        <td @click="go_board_read(obj.no)">{{ obj.subject }}</td>
                        <td class="text-center d-none d-md-table-cell">{{ obj.writerNickname }}</td>
                        <td class="text-center d-none d-md-table-cell">{{ obj.date }}</td>
                    </tr>
                </tbody>
            </table>
            <div class="text-right" v-if="logincheck == 1">
                <router-link :to="'/boardwrite/' + $route.params.board_idx" class="btn btn-primary">글쓰기</router-link>
            </div>
            <paginate class="d-none d-md-block " v-if="totalpages" v-model="page" :page-count="totalpages" :page-range="10" :margin-pages="0" :click-handler="clickCallback" :prev-text="'Prev'" :next-text="'Next'" :container-class="'pagination'" :page-class="'page-item'"> </paginate>
            <div class="card-body">
                <div class="form-horizontal">
                    <select v-model="selected" class="form-control-static col-lg-2">
                        <option disabled value="">검색조건</option>
                        <option>제목</option>
                        <option>작성자</option>
                    </select>
                    <input type="text" v-model="keyword" class="form-control-static col-lg-6" />
                    <button @click="search" class="form-control-static btn col-lg-2">검색</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Paginate from "vuejs-paginate";
import loginformVue from "../loginform.vue";
const storage = window.sessionStorage;

const apiClient = axios.create();

apiClient.interceptors.request.use(
    (config) => {
        config.headers["Content-Type"] = "application/json; charset=UTF-8";
        return config;
    },
    function(error) {
        // Do something with request error
        return Promise.reject(error);
    }
);

export default {
    data: function() {
        return {
            temp_list: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
            server_data: [],
            totalpages: 10,
            page: 1,
            selected: "",
            keyword: "",
            board_idx: 1,
            logincheck: 0,
        };
    },
    methods: {
        go_board_read: function(content_idx) {
            this.$router.push("/boardread/" + this.$route.params.board_idx + "/" + this.$route.params.page + "/" + content_idx);
        },
        get_board_list() {
            console.log(this.$route.params.board_idx);
            console.log(this.page);
            axios.get("http://localhost:9999/happyhouse/board/list/" + this.$route.params.board_idx + "/" + this.page).then(({ data }) => {
                console.log(data);
                console.log(data.totalpages);
                this.totalpages = data.totalPages;
                this.server_data = data.data;
                this.board_idx = this.server_data[0].boardInfoIdx;
                console.log(this.board_idx);
            });
        },
        clickCallback(pageNum) {
            axios.get("http://localhost:9999/happyhouse/board/list/" + this.$route.params.board_idx + "/" + this.page).then(({ data }) => {
                console.log(data);
                this.totalpages = data.totalPages;
                this.server_data = data.data;
            });
        },
        search() {
            axios
                .get("http://localhost:9999/happyhouse/board/results/" + this.$route.params.board_idx + "/" + this.page, {
                    params: {
                        keyword: this.keyword,
                        selected: this.selected,
                    },
                    headers: {
                        "Content-type": "application/json;charset=UTF-8",
                    },
                })
                .then(({ data }) => {
                    console.log(data);
                    this.totalpages = data.totalpages;
                    this.server_data = data.data;
                });
        },
    },
    watch: {
        $route(to, from) {
            this.get_board_list();
        },
    },
    created() {
        this.get_board_list();
        if (storage.getItem("id") != "") {
            this.logincheck = 1;
        } else {
            this.logincheck = 0;
        }
    },
};
</script>

<style scoped lang="scss">
#board_list > tbody > tr {
    cursor: pointer;
}
.pagination {
}
.page-item {
}
ul.menunav {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 150px;
    background-color: #f1f1f1;
}
li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}
li a.active {
    background-color: #337ab7;
    color: white;
}
li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
</style>
