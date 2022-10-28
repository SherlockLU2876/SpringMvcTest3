<template>
  <div>
    <h4>查询</h4>
    <el-form :inline="true" class="demo-form-inline" :model="search">
      <el-form-item label="学生姓名">
        <el-input placeholder="学生姓名" v-model="search.name"></el-input>
      </el-form-item>
      <el-form-item label="学生性别">
        <el-input placeholder="学生性别" v-model="search.sex"></el-input>
      </el-form-item>
      <el-form-item label="学生年龄">
        <el-input placeholder="学生年龄" v-model="search.age"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchinfo">查询</el-button>
      </el-form-item>
    </el-form>
    <Table
      :columns="columns12"
      :data="
        tabledata.slice((currentPage - 1) * pageSize, currentPage * pageSize)
      "
    >
      <!-- <Table  :columns="columns12" :data="tabledata"> -->
      <template slot-scope="{ row }" slot="name">
        <strong>{{ row.name }}</strong>
      </template>
      <template slot-scope="index" slot="action">
        <Button
          type="primary"
          size="small"
          style="margin-right: 5px"
          @click="edit(index.row)"
          >编辑</Button
        >
        <Button type="error" size="small" @click="remove(index.row.id)"
          >删除</Button
        >
      </template>
    </Table>
    <el-dialog title="学生信息" :visible.sync="dialogFormVisible" width="33%">
      <el-form
        ref="form"
        label-width="120px"
        label-position="left"
        :model="formdata"
      >
        <el-form-item label="学生id">
          <el-input prop="id" v-model="formdata.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="学生姓名">
          <el-input v-model="formdata.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="formdata.sex"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="formdata.age"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[1, 5, 10, 20]"
        :page-size="pageSize"
        layout="total,  sizes,prev, pager, next, jumper"
        :total="tabledata.length"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  inject: ["reload"],

  data() {
    return {
      columns12: [
        {
          title: "ID",
          key: "id",
        },
        {
          title: "姓名",
          key: "name",
        },
        {
          title: "年龄",
          key: "age",
        },
        {
          title: "性别",
          key: "sex",
        },
        {
          title: "Action",
          slot: "action",
          width: 150,
          align: "center",
        },
      ],
      tabledata: [],
      search: {
        name: "",
        age: "",
        sex: "",
      },
      formdata: {
        id: "",
        name: "",
        age: "",
        sex: "",
      },
      currentPage: 1,
      pageSize: 5,
      deleteid: "",
      dialogTableVisible: false,
      dialogFormVisible: false,
    };
  },
  watch: {
    $route() {
      this.getData();
    },
  },
  created() {
    this.getinfo();
  },
  methods: {
    getinfo() {
      axios({
        method: "post",
        url: "http://localhost:8080/student/allstudent",
      })
        .then((res) => {
          console.log("bbbbbb", res.data);
          this.tabledata = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    searchinfo() {
      console.log(this.search);
      axios({
        method: "post",
        url: "http://localhost:8080/student/querystudent",
        data: {
          name: this.search.name,
          sex: this.search.sex,
          age: this.search.age,
        },
      })
        .then((res) => {
          console.log("bbbbbb", res.data);
          this.tabledata = res.data;
          this.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    edit(row) {
      this.dialogFormVisible = true;
      this.formdata.id = row.id;
      this.formdata.name = row.name;
      this.formdata.sex = row.sex;
      this.formdata.age = row.age;
      console.log("dddd", row);
    },
    save() {
      axios
        .post("http://localhost:8080/student/updatestudent", {
          updateid: this.formdata.id,
          name: this.formdata.name,
          age: this.formdata.age,
          sex: this.formdata.sex,
        })
        .then((response) => {
          console.log("cccccc", response),
            this.$message({
              type: "success",
              message: "成功!",
            });
          this.getinfo();
        })
        .catch((error) => {
          console.log(error);
        });
      this.$router.go(0);
      this.dialogFormVisible = false;
    },
    remove(id) {
      console.log(id, "1");
      this.$confirm("此操作将永久删除该条记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          this.deleteid = id;
          console.log(id);
          axios
            .post("http://localhost:8080/student/deletestudent", {
              deleteid: this.deleteid,
            })
            .then((response) => {
              console.log(response);
              this.getinfo();
            })
            .catch((error) => {
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    handleSizeChange(val) {
      this.currentPage = 1;
      this.pageSize = val;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
    },
  },
};
</script>