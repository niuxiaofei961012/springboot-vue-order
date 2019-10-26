<template>
  <el-main>
    <h3>请选择下单商品</h3>
    <el-table
      :data="dataForm"
      style="width: 100%">
      <!--<el-table-column>
        <template slot-scope="scope">
          <el-checkbox :value="dataForm.id" name="chk"></el-checkbox>
        </template>
      </el-table-column>-->
      <el-table-column
        prop="name"
        width="180">
      </el-table-column>
      <el-table-column
        prop="num"
        width="180">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.num" @change="sum" controls-position="center" :min="0" :max="10"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column
        prop="price">
      </el-table-column>
      <el-table-column
        prop="total"
        width="180">
        <template slot-scope="scope">
          <span>{{scope.row.num*scope.row.price}}</span>
        </template>
      </el-table-column>
    </el-table>
    总金额:{{allTotal}}&nbsp;&nbsp;&nbsp;&nbsp;
    <el-button type="success" @click="onSubmit">保存</el-button>
  </el-main>
</template>

<script>
  const axios = require('axios');
  const baseUrl = "http://127.0.0.1:90/";
  export default {
    name: "Add",
    data() {
      return {
        saveForm: [],
        dataForm: [],
        allTotal:0,
        user_ID:0
      }
    },
    methods: {
      onSubmit(){
        let self = this;
        let orderInfo = {
          productsList:this.dataForm,
          total_price:this.allTotal,
          user_id:this.user_ID
        };
        axios({
          url: baseUrl + "save",
          method: "post",
          data:orderInfo
        }).then(function (res) {
          if(res){
            self.$router.push("/list/")
          }
        })
      },
      sum(){
        this.allTotal=0;
        this.dataForm.forEach(d=>{
          this.allTotal+=d.price*d.num
        })
      }
    },
    created() {
      this.user_ID = this.$route.params.id
      let self = this;
      axios({
        url: baseUrl + "initData",
        method: "get",
      }).then(function (res) {
        self.dataForm = res.data
      })
    }
  }
</script>

<style scoped>

</style>
