<template>
  <div id="HelloWorld">


  <el-row>
    <el-col :span="12">
      <el-drawer v-model="showEdit" direction="ltr" size="50%" :with-header="false" :modal="false">
        <div align="right" style=" margin:10px 20px 30px">
          <el-button plain @click="updateData">保存</el-button>
          <el-button plain @click="closeEdit">返回</el-button>
        </div>
        <v-md-editor text="32" v-model="chooseDate.content" height="100%" width="100%" mode="edit">343</v-md-editor>
      </el-drawer>
      <el-collapse-transition>
      <el-table :data="queryInfo.records" style="width: 100%" @row-click="listClick">
        <el-table-column prop="time" label="时间" width="180">

        </el-table-column>
        <el-table-column prop="content" label="内容"></el-table-column>

        <el-table-column>
          <template #default="scope">
          <el-button plain @click="openEdit(scope.row)">编辑</el-button>
          <el-button plain @click="deleteData(scope.$index,scope.row,queryInfo.records)">删除</el-button>
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
export default {
  name: "HelloWorld",
  components:{

  },
  props: {},
  created() {
    this.getData();
  },

  data() {
    return {
      atest: "433",
      showEdit: false,
      input: "123",
      queryInfo: {
        size: 10,
        current: 1,
        total:10,
        records:[]
      },
      chooseDate:{content:"# 加载中"}
    };
  },

  methods: {
    getData() {
      this.$http({
        method: "post",
        url: "yourstore/note/getAll",
        data: this.queryInfo,
      }).then((response) => {
        this.queryInfo = response.data;
        this.chooseDate=this.queryInfo.records[0];
      });
    },updateData(){
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
          this.chooseDate=this.queryInfo.records[0];
        }
      });
    },
    currentChange(val) {
      this.queryInfo.current = val;
      this.getData();
    },
    listClick(val){
      this.chooseDate=val;
    },
    openEdit(){
      this.showEdit=true;
    },
    closeEdit(){
      this.showEdit=false;
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
html,
body,
#editor {
  margin: 0;
  height: 100%;
  font-family: "Helvetica Neue", Arial, sans-serif;
  color: #333;
}

textarea{
  display: inline-block;
  width: 100%;
  height: 100%;
  vertical-align: top;
  box-sizing: border-box;
  padding: 0 20px;
  background: #2c3e50;
}

textarea {
  border: none;
  border-right: 1px solid #ccc;
  resize: none;
  outline: none;
  background-color: #f6f6f6;
  font-size: 14px;
  font-family: "Monaco", courier, monospace;
  padding: 20px;
}

code {
  color: #f66;
}
</style>
