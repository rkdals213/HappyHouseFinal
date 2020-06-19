<template>
    <div>
        <div class="form-group">
            <label for="writerNickname">보내는사람</label>
            <input type="text" class="form-control" id="writerNickname" ref="writerNickname" v-model="writerNickname" readonly />
        </div>
        <div class="form-group">
            <label for="toNickname">받는사람</label>
            <input type="text" class="form-control" id="toNickname" ref="toNickname" v-model="toNickname" readonly />
        </div>
        <div class="form-group">
            <label for="subject">제목</label>
            <input type="text" class="form-control" id="subject" ref="subject" v-model="subject" placeholder="제목을 입력하세요" />
        </div>
        <div class="form-group">
            <label for="text">내용</label>
            <input type="text" class="form-control" id="text" ref="text" v-model="text" placeholder="내용을 입력하세요" />
        </div>

        <div class="text-right">
            <button class="btn btn-primary" @click="sendMessageHandler">전송</button>
            <button class="btn btn-primary" @click="cancle">취소</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;

export default {
    data() {
        return {
            writerNickname: "",
            toNickname: "",
            subject: "",
            text: "",
            date: "",
            readcheck: 0,
        };
    },
    created() {
        this.writerNickname = storage.getItem("nickname");
        this.date = new Date();
        console.log(this.$route.params.toNickname);
        this.toNickname = this.$route.query.toNickname;
    },
    methods: {
        sendMessageHandler() {
            axios
                .post("http://localhost:9999/happyhouse/message/sendMessage", {
                    writerNickname: this.writerNickname,
                    toNickname: this.toNickname,
                    subject: this.subject,
                    text: this.text,
                    date: this.date,
                    readcheck: this.readcheck,
                })
                .then(({ data }) => {
                    console.log(data);
                    this.move();
                });
        },
        move() {
            this.$router.push("/");
        },
        cancle() {
            this.$router.push("/");
        },
    },
};
</script>

<style></style>
