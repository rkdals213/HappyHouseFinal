<template>
    <div id="header" :style="{ backgroundImage: 'url(' + require('@/assets/헤더.png') + ')' }">
        <div v-if="logincheck == 0 || logincheck == 3">
            <span>로그인 해주세요</span>
            <span>
                <button class="btn btn-link" @click="login">로그인</button>
            </span>
        </div>
        <div v-else>
            <span>{{ nickname }}님 환영합니다 </span>
            <span>
                <button class="btn btn-link" @click="message">쪽지 : {{ messageNum }}</button>
                <button class="btn btn-link" @click="change">개인정보 변경</button>
                <button class="btn btn-link" @click="logout">로그아웃</button>
            </span>
        </div>
    </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;
export default {
    data() {
        return {
            logincheck: 0,
            nickname: "",
            messageNum: 0,
        };
    },
    created() {
        console.log("login created" + this.logincheck);
        if (storage.getItem("kind") != 0 && storage.getItem("kind") != null) {
            this.logincheck = storage.getItem("kind");
            this.nickname = storage.getItem("nickname");
        }
        console.log("login created" + this.logincheck);
        if (this.logincheck == 1) this.getdata();
        this.messageNum = 0;
        this.getMessageHandler();
    },
    methods: {
        login() {
            this.$router.push("/userinfo/loginform");
        },
        getMessageHandler() {
            console.log("getmessages");
            axios
                .post("http://localhost:9999/happyhouse/message/selectCount", {
                    nickname: this.nickname,
                })
                .then(({ data }) => {
                    this.messageNum = data.data;
                });
        },
        getdata() {
            axios.get("http://localhost:9999/happyhouse/userinfoR/getkakao").then(({ data }) => {
                console.log(data);
                storage.setItem("data", JSON.stringify(data.data));
                storage.setItem("token", data.data.token);
                storage.setItem("id", data.data.id);
                storage.setItem("nickname", data.data.nickname);
                this.nickname = storage.getItem("nickname");
                this.getMessageHandler();
            });
        },
        logout() {
            if (storage.getItem("kind") == 1) {
                axios
                    .post("http://localhost:9999/happyhouse/userinfoR/logout", {
                        token: storage.getItem("token"),
                    })
                    .then(() => {
                        storage.setItem("data", "");
                        storage.setItem("kind", 0);
                        storage.setItem("token", "");
                        storage.setItem("id", "");
                        storage.setItem("nickname", "");
                        this.nickname = "";
                        this.move();
                    });
            } else if (storage.getItem("kind") == 2) {
                storage.setItem("data", "");
                storage.setItem("kind", 0);
                storage.setItem("token", "");
                storage.setItem("id", "");
                storage.setItem("nickname", "");
                this.nickname = "";
                this.move();
            }
        },
        change() {
            this.$router.push("/userinfo/changeform");
        },
        message() {
            this.$router.push("/userinfo/messagelist");
        },
        move() {
            location.href = "http://localhost:8080";
        },
    },
};
</script>

<style>
#header {
    /* background-image: url("http://blogfiles.naver.net/MjAxODEwMDJfMjA4/MDAxNTM4NDU1ODUyMTA0.fFff7S4ry8s_EP9ub8mpfY2v66ssPBKO-7lXMqXaJ68g.MdOsvGf-X29hd0XA7OlAa4AJghgeKgjZWFdlmSNtljkg.JPEG.dlxornjs0512/%B1%D7%B8%B27.jpg"); */
    background-repeat: no-repeat;
    background-size: 100%;
    /* background-color: aqua; */
    height: 200px;
    color: black;
}
</style>
