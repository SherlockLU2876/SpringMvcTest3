<template>
  <div>
    <h4>添加学生</h4>
    <el-form :inline="true" class="demo-form-inline" :model="addtabledata">
      <el-form-item label="学生姓名">
        <el-input placeholder="学生姓名" v-model="addtabledata.name"></el-input>
      </el-form-item>
      <br />
      <el-form-item label="学生性别">
        <el-input placeholder="学生性别" v-model="addtabledata.sex"></el-input>
      </el-form-item>
      <br />
      <el-form-item label="学生年龄">
        <el-input placeholder="学生年龄" v-model="addtabledata.age"></el-input>
      </el-form-item>
      <br />
      <el-form-item>
        <el-button type="primary" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      addtabledata: {
        name: "",
        sex: "",
        age: "",
      },
    };
  },
  methods: {
    add() {
      console.log(this.addtabledata.name);
      axios
        .post("http://localhost:8080/student/addstudent", {
          name: this.addtabledata.name,
          sex: this.addtabledata.sex,
          age: this.addtabledata.age,
        })
        .then((response) => {
          console.log("cccccc", response),
            this.$message({
              type: "success",
              message: "成功!",
            });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>