<template>
    <div>
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" ref="id" placeholder="ID를 입력하세요" v-model="id" readonly />
        </div>
        <div class="form-group">
            <label for="pw">PW</label>
            <input type="text" class="form-control" id="pw" ref="pw" placeholder="pw를 입력하세요" v-model="pw" />
        </div>
        <div class="text-right">
            <button class="btn btn-info" @click="checkHandler">탈퇴</button>
            <button class="btn btn-info" @click="move">취소</button>
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
    created() {
        this.id = storage.getItem("id");
    },
    methods: {
        checkHandler() {
            let err = true;
            let msg = "";
            !this.id && !this.pw && ((msg = "ID, 비밀번호를 입력해주세요"), (err = false), this.$refs.id.focus());

            if (!err) alert(msg);
            else this.dropidHandler();
        },
        dropidHandler() {
            axios
                .post("http://localhost:9999/happyhouse/userinfoR/dropId", {
                    id: this.id,
                    pw: this.pw,
                })
                .then(({ data }) => {
                    console.log(data);
                    this.logout();
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
        move() {
            location.href = "/";
        },
    },
};
</script>

<style></style>
