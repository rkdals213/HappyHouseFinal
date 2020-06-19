<template>
    <div>
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" ref="id" placeholder="ID를 입력하세요" v-model="id" />
        </div>
        <div class="form-group">
            <label for="pw">PW</label>
            <input type="password" class="form-control" id="pw" ref="pw" placeholder="pw를 입력하세요" v-model="pw" />
        </div>
        <div class="text-right">
            <button class="btn btn-info" @click="checkHandler">로그인</button>
            <button class="btn btn-warning" @click="login">카카오톡 로그인</button>
            <button class="btn btn-info" @click="join">회원가입</button>
            <button class="btn btn-dark" @click="move">취소</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;
export default {
    data() {
        return {
            id: "",
            pw: "",
        };
    },
    methods: {
        checkHandler() {
            let err = true;
            let msg = "";
            !this.id && !this.pw && ((msg = "ID, 비밀번호를 입력해주세요"), (err = false), this.$refs.id.focus());

            if (!err) alert(msg);
            else this.loginHandler();
        },
        loginHandler() {
            axios
                .post("http://localhost:9999/happyhouse/userinfoR/normalLogin", {
                    id: this.id,
                    pw: this.pw,
                })
                .then(({ data }) => {
                    console.log(data);
                    storage.setItem("kind", 2);
                    storage.setItem("id", data.data.id);
                    storage.setItem("nickname", data.data.nickname);
                    this.move();
                });
        },
        login() {
            storage.setItem("kind", 1);
            location.href = "http://localhost:9999/happyhouse/userinfo/kakaoLogin";
        },
        move() {
            location.href = "http://localhost:8080";
        },
        join() {
            this.$router.push("/userinfo/normaljoin");
        },
    },
};
</script>

<style></style>
