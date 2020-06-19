<template>
    <div>
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" ref="id" placeholder="ID를 입력하세요" v-model="id" readonly />
        </div>
        <div class="form-group">
            <label for="pw">PW</label>
            <input type="password" class="form-control" id="pw" ref="pw" placeholder="pw를 입력하세요" v-model="pw" />
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="text" class="form-control" id="email" ref="email" placeholder="이메일을 입력하세요" v-model="email" />
        </div>
        <div class="form-group">
            <label for="nickname">이름</label>
            <input type="text" class="form-control" id="nickname" ref="nickname" placeholder="이름를 입력하세요" v-model="nickname" />
        </div>
        <div class="form-group">
            <label for="phone">전화번호</label>
            <input type="text" class="form-control" id="phone" ref="phone" placeholder="전화번호를 입력하세요" v-model="phone" />
        </div>
        <div class="form-group">
            <label for="address">주소</label>
            <input type="text" class="form-control" id="address" ref="address" placeholder="주소를 입력하세요" v-model="address" />
        </div>

        <div class="text-right">
            <button class="btn btn-info" @click="checkHandler">등록</button>
            <button class="btn btn-dark" @click="cancle">취소</button>
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
            nickname: "",
            email: "",
            phone: "",
            address: "",
        };
    },
    created() {
        this.getdata();
        storage.setItem("id", 3);
    },
    methods: {
        getdata() {
            axios.get("http://localhost:9999/happyhouse/userinfoR/getkakao").then(({ data }) => {
                console.log(data);
                storage.setItem("token", data.data.token);
                storage.setItem("id", data.data.id);
                this.id = data.data.id;
                this.email = data.data.email;
                this.nickname = data.data.nickname;
            });
            console.log("getdata");
        },
        checkHandler() {
            let err = true;
            let msg = "";
            !this.name && !this.pw && ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.pw.focus());
            err && !this.nickname && ((msg = "이름을 입력해주세요"), (err = false), this.$refs.nickname.focus());
            err && !this.email && ((msg = "이메일을 입력해주세요"), (err = false), this.$refs.email.focus());
            err && !this.phone && ((msg = "전화번호를 입력해주세요"), (err = false), this.$refs.phone.focus());
            err && !this.address && ((msg = "주소를 입력해주세요"), (err = false), this.$refs.address.focus());

            if (!err) alert(msg);
            else this.joinHandler();
        },
        joinHandler() {
            axios
                .post("http://localhost:9999/happyhouse/userinfoR/join", {
                    id: this.id,
                    pw: this.pw,
                    nickname: this.nickname,
                    email: this.email,
                    phone: this.phone,
                    address: this.address,
                })
                .then(({ data }) => {
                    console.log(data);
                    storage.setItem("id", 1);
                    this.move();
                });
        },
        move() {
            location.href = "/";
        },
        cancle() {
            if (storage.getItem("kind") == 3) {
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
    },
};
</script>

<style></style>
