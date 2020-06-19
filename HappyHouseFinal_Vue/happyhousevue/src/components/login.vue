<template>
    <div v-if="logincheck == 0">
        <div class="text-right">
            <span>로그인 해주세요</span>
            <button class="btn btn-primary" @click="login">로그인</button>
        </div>
    </div>
    <div v-else>
        <div class="text-right">
            <span>{{ nickname }}님 환영합니다</span>
            <button class="btn btn-primary" @click="change">개인정보 변경</button>
            <button class="btn btn-primary" @click="logout">로그아웃</button>
            <button class="btn btn-primary" @click="dismiss">회원탈퇴</button>
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
        };
    },
    created() {
        if (storage.getItem("kind") != 0) this.logincheck = storage.getItem("kind");
        this.nickname = storage.getItem("nickname");
        console.log("login created" + this.logincheck);
        if (this.logincheck == 1) this.getdata();
    },
    methods: {
        login() {
            this.$router.push("/userinfo/loginform");
        },
        getdata() {
            axios.get("http://localhost:9999/happyhouse/userinfoR/getkakao").then(({ data }) => {
                console.log(data);
                storage.setItem("data", JSON.stringify(data.data));
                storage.setItem("token", data.data.token);
                storage.setItem("id", data.data.id);
                storage.setItem("nickname", data.data.nickname);
                this.nickname = storage.getItem("nickname");
            });
        },
        logout() {
            if (storage.getItem("kind") == 1) {
                axios
                    .post("http://localhost:9999/happyhouse/userinfoR/logout", {
                        token: JSON.parse(storage.getItem("data")).token,
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
        move() {
            location.href = "http://localhost:8080";
        },
        dismiss() {
            this.$router.push("/userinfo/dismiss");
        },
    },
};
</script>

<style></style>
