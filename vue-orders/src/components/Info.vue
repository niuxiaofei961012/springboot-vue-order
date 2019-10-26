<template>
    <el-main>
      <span>
        订单日期:{{info[0].create_time}}&nbsp;&nbsp;&nbsp;
        订单编号:{{info[0].order_id}}
      </span>
      <br>
      <br>
        <span v-for="inf in info">
          {{inf.name}}×{{inf.num}} &nbsp;&nbsp;&nbsp;  {{inf.total_price}}
          <br>
        </span>
      <br>

      <span v-for="inf in info" v-show="false">
          {{total}}={{total+=inf.total_price}}
          <br>
        </span>
      订单总金额:{{total}}
      <br>
      <br>
      <router-link to="/list">返回</router-link>
    </el-main>
</template>

<script>
    const axios = require('axios');
    const baseUrl = "http://127.0.0.1:90/";
    export default {
        name: "Login",
      data(){
          return{
            info:[
            ],
            total:0
          }
      },
      methods:{
          getInfo(id){
            let self = this;
            axios({
              url:baseUrl+"orderInfo",
              method:"get",
              params:{order_id:id}
            }).then(function (res) {
              self.info=res.data
            })
          },
      },
      created(){
          let id = this.$route.params.id;
          this.getInfo(id);
      }
    }
</script>

<style scoped>

</style>
