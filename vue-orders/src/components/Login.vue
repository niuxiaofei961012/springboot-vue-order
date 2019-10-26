<template>
    <el-main>
      <h3>输入用户名密码进行登录</h3>
      <el-form :inline="true" :label-position="labelPosition" label-width="80px" :model="loginForm">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <br>
        <el-form-item label="密码">
          <el-input v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <br>
        <el-form-item>
          <el-button @click="login" type="info">登录</el-button>
        </el-form-item>
      </el-form>
    </el-main>
</template>

<script>
    const axios = require('axios');
    const baseUrl = "http://127.0.0.1:90/";
    export default {
        name: "Login",
      data(){
          return{
            labelPosition: 'right',
            loginForm:{

            }
          }
      },
      methods:{
        login(){
          let self = this;
          if(this.loginForm.username==null){
            this.$message("请输入用户名")
            return;
          }
          if(this.loginForm.password==null){
            this.$message("请输入密码")
            return;
          }
          axios({
            url:baseUrl+"login",
            method:"get",
            params:this.loginForm
          }).then(function (res) {
            if(res.data.nameMsg){
              self.$message(res.data.nameMsg);
            }else if(res.data.pwdMsg){
              self.$message(res.data.pwdMsg);
            }else{
              //将用户id存入localStorage
              window.localStorage.setItem("user",res.data.user.id);
              self.$router.push("/list")
            }
          })
        }
      },
      created(){

      }
    }
</script>

<style scoped>

</style>
