<template>
  <div id="HelloWorld">

    <div style="text-align: left;font-size: 30px;padding-left: 60px">HelloTime
      <el-button plain :loading="loadSave" @click="createData" style="margin-right: 20px">我想到了</el-button>

      <el-input ref="search" v-show="showSearch" style="display: inline-block;width: 20%;" v-model="queryInfo.searchData" autofocus="true"  placeholder="想找"></el-input>

        <el-button plain :loading="loadSave" @click="createSearch">我想找</el-button></div>

  <el-row>
    <el-col :span="12">
      <el-drawer v-model="showEdit" direction="ltr" size="50%" :with-header="false" :modal="false" destroy-on-close>

        <el-row :gutter="20" >

        <el-col :span="16">
          <el-input size="20%" v-model="chooseDate.title" placeholder="请输入标题"></el-input>
        </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="7" style="float:right" >
            <el-button plain :loading="loadSave" @click="updateData">保存</el-button>
            <el-button plain :loading="loadSave" @click="closeEdit">返回</el-button>
          </el-col>
        </el-row>
        <v-md-editor text="32" v-model="chooseDate.content" height="100%" width="100%" mode="edit"></v-md-editor>
      </el-drawer>
      <el-collapse-transition>
      <el-table :data="tableData" style="width: 100%" @row-click="listClick">

        <el-table-column prop="time" label="记录头" width="180">
          <template #default="scope">
          {{ dateFormat(scope.row.time)}}<br>{{scope.row.title}}
        </template>
        </el-table-column>
        <el-table-column prop="content" label="内容"></el-table-column>

        <el-table-column>
          <template #default="scope">
          <el-button plain @click="openEdit(scope.row)">编辑</el-button>
          <el-button plain @click="deleteData(scope.$index,scope.row,tableData)">删除</el-button>
          </template>
        </el-table-column>


      </el-table>
      </el-collapse-transition>
      <div class="block">
        <el-pagination
            layout="prev, pager, next"
            @current-change="currentChange"
            :page-size="queryInfo.size"
            :current-page="queryInfo.current"
            :total="queryInfo.total"
        >
        </el-pagination>
      </div>
    </el-col>
    <el-col :span="12">
      <div class="hello">
        <div id="editor">
          <v-md-preview :text="chooseDate.content"></v-md-preview>
        </div>
      </div>
    </el-col>
  </el-row>


  </div>

</template>

<script>
import '@kangc/v-md-editor/lib/style/preview.css';
export default {
  name: "HelloWorld",
  components:{

  },
  props: {},
  created() {
    this.getData();
  },
  computed: {
    dateFormat() {
      return function(originVal){
        const dt = new Date(originVal)
        const y = dt.getFullYear()
        // 月份从0开始,使她变成字符串,不足两位时,前面补个0.
        const m = (dt.getMonth() + 1 + '').padStart(2, '0')
        const d = (dt.getDate() + '').padStart(2, '0')
        const hh = (dt.getHours() + '').padStart(2, '0')
        const mm = (dt.getMinutes() + '').padStart(2, '0')
        const ss = (dt.getSeconds() + '').padStart(2, '0')
        return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
      }
    }

  },

  data() {
    return {
      loadSave:false,
      showEdit: false,
      input: "123",
      queryInfo: {
        size: 10,
        current: 1,
        total:10,
        searchData:""
      },
      tableData:[],
      chooseDate:{content:"# 加载中"},
      showSearch:false

    };
  },

  methods: {
    getData() {
      this.$http({
        method: "post",
        url: "yourstore/note/getAll",
        data: this.queryInfo,
      }).then((response) => {
        this.tableData = response.data.records;
        this.queryInfo.total=response.data.total;
        this.chooseDate=this.tableData[0];
      });
    },updateData(){
      this.loadSave=true;
      this.$http({
        method: "post",
        url: "yourstore/note/update",
        data: this.chooseDate,
      }).then(() => {
        this.closeEdit();
      });
    },deleteData(index,row,tableData){
      this.$http({
        method: "post",
        url: "yourstore/note/delete",
        data: row,
      }).then((data) => {
        if (data.status==200){
          tableData.splice(index, 1);
          // this.getData();
          this.chooseDate=this.tableData[0];
        }
      });
    },
    currentChange(val) {
      this.queryInfo.current = val;
      this.getData();
    },
    listClick(val){
      console.log("2",val)
      this.chooseDate=val;
    },
    createData(){
      this.chooseDate={};
      this.openEdit();
    },
    openEdit(){
      this.showEdit=true;
    },
    closeEdit(){
      this.loadSave=false;
      this.showEdit=false;
    },createSearch(){
      if (!this.showSearch) {
        this.showSearch = true;
        this.$refs['search'].focus()
      }else{
        this.getData();
      }
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
