<template>
    <div>
        <table class="table table-hover" id="board_list">
            <thead>
                <tr>
                    <th class="text-center d-none d-md-table-cell">번호</th>
                    <th class="text-center d-none d-md-table-cell">제목</th>
                    <th class="text-center d-none d-md-table-cell">작성자</th>
                    <th class="text-center d-none d-md-table-cell">작성날짜</th>
                    <th class="text-center d-none d-md-table-cell">읽음</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(message, index) in messages" v-bind:key="index">
                    <td class="text-center d-none d-md-table-cell">{{ message.no }}</td>
                    <td class="text-center d-none d-md-table-cell" data-toggle="modal" :data-target="modalId(index)">{{ message.subject }}</td>
                    <td class="text-center d-none d-md-table-cell">{{ message.writerNickname }}</td>
                    <td class="text-center d-none d-md-table-cell">{{ message.date }}</td>
                    <td class="text-center d-none d-md-table-cell">{{ message.readcheck }}</td>

                    <!-- The Modal -->
                    <div class="modal" :id="index">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">{{ message.subject }}</h4>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    {{ message.text }}
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <div class="col-lg-4">작성자 : {{ message.writerNickname }}</div>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" @click="replyMessageHandler(message.writerNickname, index)">답장</button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" @click="readHandler(index)">닫기</button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" @click="deleteHandler(index)">삭제</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;

export default {
    data() {
        return {
            nickname: "",
            messages: [],
            showNo: 0,
        };
    },
    created() {
        this.nickname = storage.getItem("nickname");
        this.getMessageHandler();
    },
    methods: {
        getMessageHandler() {
            console.log("getmessages");
            axios
                .post("http://localhost:9999/happyhouse/message/selectAll", {
                    nickname: this.nickname,
                })
                .then(({ data }) => {
                    this.messages = data.data;
                    console.log(this.messages);
                });
        },
        changeShowNo(no) {
            this.showNo = no;
        },
        modalId(i) {
            return "#" + i;
        },
        readHandler(messageNo) {
            console.log("read" + messageNo);
            axios
                .post("http://localhost:9999/happyhouse/message/readMessage", {
                    no: this.messages[messageNo].no,
                })
                .then(({ data }) => {
                    location.href = "/userinfo/messagelist";
                });
        },
        deleteHandler(messageNo) {
            console.log("read" + messageNo);
            axios
                .post("http://localhost:9999/happyhouse/message/deleteMessage", {
                    no: this.messages[messageNo].no,
                })
                .then(({ data }) => {
                    location.href = "/userinfo/messagelist";
                });
        },
        replyMessageHandler(toNickname, index) {
            axios
                .post("http://localhost:9999/happyhouse/message/readMessage", {
                    no: this.messages[index].no,
                })
                .then(({ data }) => {
                    console.log(data);
                });
            this.$router.push("/message/sendmessage?toNickname=" + toNickname);
        },
    },
};
</script>

<style></style>
