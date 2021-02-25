<template>
  <el-row>
    <el-col :span="12">
      <el-table :data="queryInfo.records" style="width: 100%" @row-click="listClick">
        <el-table-column prop="time" label="时间" width="180">

        </el-table-column>
        <el-table-column prop="content" label="内容"></el-table-column>
        <el-table-column> → </el-table-column>
      </el-table>
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
<!--          <textarea :value="input" @input="update"></textarea>-->
          <div v-html="compiledMarkdown"></div>
        </div>
      </div>
    </el-col>
  </el-row>




</template>

<script>
import marked from "marked";
import _ from "lodash";

export default {
  name: "HelloWorld",
  props: {},
  created() {
    this.getData();
  },

  data() {
    return {
      input: "123",
      queryInfo: {
        size: 10,
        current: 1,
        total:10,
        records:[]
      },
      chooseDate:{content :"# 加载中"}
    };
  },

  computed: {
    compiledMarkdown() {
      return marked(this.chooseDate.content, { sanitize: true });
    },
  },

  methods: {
    update: _.debounce(function (e) {
      this.input = e.target.value;
    }, 300),
    getData() {
      this.$http({
        method: "post",
        url: "yourstore/note/getAll",
        data: this.queryInfo,
      }).then((response) => {
        this.queryInfo = response.data;
        this.chooseDate=this.queryInfo.records[0];
      });
    },
    currentChange(val) {
      this.queryInfo.current = val;
      this.getData();

    },
    listClick(val){
      this.chooseDate=val;
      console.log("422",this.chooseDate)
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

textarea,
#editor div {
  display: inline-block;
  width: 49%;
  height: 100%;
  vertical-align: top;
  box-sizing: border-box;
  padding: 0 20px;
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
