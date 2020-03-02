<template>
<div>
  <div class="login">
    <div id="logo">
      <span id="project-name">房产直销系统</span>
      <img src="../assets/img/login_logo.jpg">
    </div>
    <div id="form">
      <div id="div-form">
        <ul>
          <li>
            <span>账号</span>
          </li>
          <li>
            <input id="input-username" v-model="username" type="text" name="username">
          </li>
          <li>
            <span>密码</span>
          </li>
          <li>
            <input id="input-password" v-model="password" type="password" name="password">
          </li>
          <li>
            <input id="input-remember" type="checkbox" name="remember">
            <span>记住密码</span>
          </li>
          <li>
            <button id="button-login" v-on:click="login()" class="button">登录</button>
          </li>
          <li>
            <span>还没有账号？</span>
            <a>创建一个账号</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="footer">
      <span>Copyright © 售房网 2020 All Rights Reserved </span>
    </div>
  </div>
</div>
</template>

<script>
import CONSTANT from '@/assets/js/const.js'
import router from '@/router'
export default {

  name: 'Login',
  data () {
    return {
      username: '',
      password: '',
      baseurl: process.env.BASE_URL
    }
  },
  methods: {
    login: function () {
      this.$post('/login', {
        username: this.username,
        password: this.password
      })
        .then(response => {
          if (CONSTANT.STATUS_CODE.SUCCESS === response.RespCode) {
            this.$message.success('登录成功')
            router.push('/')
          } else {
            this.$message.error(response.RespDesc)
          }
        })
      // alert(this.username + this.password)
    }
  }

}

</script>

<style scoped>
.login{
    width: 100vw;
    height: 100vh;
    background-image: url("../assets/img/background2.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    font-size: 16px;
}
#logo{
  text-align: center;
  width: 100vw;
  /*height: 30vh;*/
  padding-top: 5vh;
  padding-bottom: 5vh;
}
#logo > span{
  position: absolute;
  left: 20px;
}
#logo > img{
  border-radius: 50%;
  width: 6vw;
}
#form{
  width: 100vw;
}
#form > div{
  background-color: white;
  border-style: solid;
  border-width: 1px;
  border-color: black;
  border-radius: 1rem;
  width: 25vw;
  height: 30vw;
  margin: 0 auto;
  padding: 2rem;
}

#form ul li{
  width: 100%;
  margin-bottom: 0.5rem;
}
#input-username,#input-password,#button-login{
  width: 100%;
  border-radius: 0.25rem;
  padding: 0.5rem 0.75rem;
  font-size: 1rem;
  line-height: 1.25;
  border: 1px solid rgba(0,0,0,0.15);
  overflow: visible;
}
#button-login{
  background-color: #007bff;
  color: #ffffff;
  border-color: #007bff;
}
#project-name{
  font-family: Arial;
  font-size: 30px;
  font-weight: bolder;
  color: rgb(226, 141, 43);
}
.footer{
  font-family: Arial;
  color: grey;
  font-size: 16px;
  position: absolute;
  bottom: 4vh;
  left: 50%;
  /* transform: translateX(-50%) translateY(-50%); */
}
.footer > span{
  position: relative;
  left: -50%;
}
</style>
