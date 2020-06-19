<template>
  <div class="container" style="margin-top:100px">
    <div class="row">
      <div class="col-sm-3"></div>
      <div class="col-sm-6">
        <div class="card shadow">
          <div class="card-body">
            <div class="form-group">
              <label for="board_writer_name">작성자</label>
              <input type="text" id="board_writer_name" v-model="server_data.writerNickname" class="form-control" disabled="disabled" />
            </div>
            <div class="form-group">
              <label for="board_date">작성날짜</label>
              <input type="text" id="board_date" v-model="server_data.date" class="form-control" disabled="disabled" />
            </div>
            <div class="form-group">
              <label for="board_subject">제목</label>
              <input type="text" id="board_subject" ref="board_subject" v-model="server_data.subject" class="form-control" />
            </div>
            <div class="form-group">
              <label for="board_content">내용</label>
              <textarea id="board_content" ref="board_content" v-model="server_data.text" class="form-control" rows="10" style="resize:none"></textarea>
            </div>

            <div class="form-group">
              <div class="text-right">
                <button type="button" @click="check_input" class="btn btn-primary">수정완료</button>
                <router-link to="/board_read" class="btn btn-info">취소</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-3"></div>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  data: function() {
    return {
      server_data: {},
    };
  },
  methods: {
    check_input: function() {
      if (this.server_data.subject.length == 0) {
        alert("제목을 입력해주세요");
        this.$refs.board_subject.focus();
        return;
      }
      if (this.server_data.text.length == 0) {
        alert("내용을 입력해주세요");
        this.$refs.board_content.focus();
        return;
      }

      var params = new FormData();
      params.append("content_subject", this.server_data.content_subject);
      params.append("content_text", this.server_data.content_text);
      params.append("content_idx", this.$route.params.content_idx);

      axios
        .put("http://localhost:9999/happyhouse/board/" + this.server_data.no, {
          boardInfoIdx: this.server_data.boardInfoIdx,
          date: this.server_data.date,
          no: this.server_data.no,
          subject: this.server_data.subject,
          text: this.server_data.text,
          writerNickname: this.server_data.writerNickname,
        })
        .then((response) => {
          if (response.data.result == true) {
            alert("수정되었습니다");
            this.$router.push("/board_read/" + this.$route.params.board_idx + "/" + this.$route.params.page + "/" + this.$route.params.content_idx);
          }
        });
    },
  },
  created() {
    var params = new URLSearchParams();
    params.append("content_idx", this.$route.params.content_idx);
    console.log(this.$route.params.no);

    axios.get("http://localhost:9999/happyhouse/board/" + this.$route.params.no).then((response) => {
      this.server_data = response.data.data;
      console.log(this.server_data);
    });
  },
};
</script>
