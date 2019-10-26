<template>
    <el-main>
      <el-form :inline="true" :model="queryForm" class="demo-form-inline">
        <el-form-item label="">
          <el-input v-model="queryForm.id" placeholder="请输入订单号"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="queryForm.startPrice" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="到">
          <el-input v-model="queryForm.endPrice" placeholder=""></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getList">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          label="订单编号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="userName"
          label="用户名称"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="total_price"
          label="订单价格"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="create_time"
          label="订单日期"
          width="120">
          <template slot-scope="scope">{{ scope.row.create_time }}</template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="info(scope.row.id)"
              type="text"
              size="small">
              详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        <el-button @click="">批量删除</el-button>
        <el-button @click="add">添加订单</el-button>
      </div>
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="goTopage"
        :page-count="pages">
      </el-pagination>
    </el-main>
</template>

<script>
    const axios = require('axios');
    const baseUrl = "http://127.0.0.1:90/";
    export default {
        name: "Login",
      data(){
          return{
            tableData:[],
            queryForm:{
              pageNo:1,
              startPrice:"",
              endPrice:""
            },
            pages:0,
            user_ID:0
          }
      },
      methods:{
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        getList(){
          let self = this;
          axios({
            url:baseUrl+"list",
            method:"get",
            params:this.queryForm
          }).then(function (res) {
            self.tableData=res.data.list;
            self.pages=res.data.pages;
          })
        },
        goTopage(pageNo){
          this.queryForm.pageNo=pageNo;
          this.getList();
        },
        info(id){
          console.log(id)
          this.$router.push("/info/"+id);
        },
        add(){
          this.$router.push("/add/"+this.user_ID)
        }
      },
      created(){
          let userId = window.localStorage.getItem("user");
          this.queryForm.user_id=userId;
          this.user_ID=userId
          this.getList();
      }
    }
</script>

<style scoped>

</style>
