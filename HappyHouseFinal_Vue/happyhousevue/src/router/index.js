import Vue from "vue";
import VueRouter from "vue-router";
import httpVueLoader from "http-vue-loader";
import axios from "axios";

import Home from "../views/Home.vue";
import HouseInfo from "../components/house/HouseInfo.vue";
import HouseDeal from "../components/house/Housedeal.vue";
import HouseInfoRead from "../components/house/HouseInfoRead.vue";

import login from "@/components/login.vue";
import join from "@/components/join.vue";
import normaljoin from "@/components/normaljoin.vue";
import loginform from "@/components/loginform.vue";
import changeform from "@/components/changeform.vue";
import dismiss from "@/components/dismiss.vue";
import BoardList from "../components/board/BoardList.vue";
import BoardRead from "../components/board/BoardRead.vue";
import BoardModify from "../components/board/BoardModify.vue";
import BoardWrite from "../components/board/BoardWrite.vue";
import sendMessage from "../components/sendmessage.vue";
import messagelist from "@/components/messagelist.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/houseinfo",
    name: "houseinfo",
    component: HouseInfo,
  },
  {
    path: "/housedeal",
    name: "housedeal",
    component: HouseDeal,
  },
  {
    path: "/houseinforead/:no",
    component: HouseInfoRead,
  },

  /////////////////////////////////////////////////////////////////////////////////////////////

  {
    path: "/boardlist/:board_idx/:page",
    component: BoardList,
  },
  {
    path: "/boardread/:board_idx/:page/:no",
    component: BoardRead,
  },
  {
    path: "/boardmodify/:board_idx/:page/:no",
    component: BoardModify,
  },
  {
    path: "/boarddelete/:board_idx/:page/:no",
    beforeEnter(to, from, next) {
      var params = new URLSearchParams();
      params.append("no", to.params.no);
      console.log(to);
      console.log(from);
      console.log(next);
      axios.delete("http://localhost:9999/happyhouse/board/" + to.params.no).then((response) => {
        alert("삭제되었습니다");
        next("/boardlist/" + to.params.board_idx + "/" + to.params.page);
      });
    },
  },
  {
    path: "/boardwrite/:board_idx",
    component: BoardWrite,
  },

  //////////////////////////////////////////////////////////////////////////////////////////////
  {
    path: "/userinfo",
    name: "login",
    component: login,
  },
  {
    path: "/userinfo/join",
    name: "join",
    component: join,
  },
  {
    path: "/userinfo/normaljoin",
    name: "normaljoin",
    component: normaljoin,
  },
  {
    path: "/userinfo/loginform",
    name: "loginform",
    component: loginform,
  },
  {
    path: "/userinfo/changeform",
    name: "chnageform",
    component: changeform,
  },
  {
    path: "/userinfo/dismiss",
    name: "dismiss",
    component: dismiss,
  },
  {
    path: "/message/sendmessage",
    name: "sendmessage",
    component: sendMessage,
  },
  {
    path: "/userinfo/messagelist",
    name: "messagelist",
    component: messagelist,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
