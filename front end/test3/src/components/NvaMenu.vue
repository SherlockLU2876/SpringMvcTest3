<template>
  <div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :router="true"
        >
          <el-menu-item index="/TableAdd">
            <i class="el-icon-menu"></i>
            <router-link to="/TableAdd" active-class="active">
              <span>添加学生信息</span></router-link
            >
          </el-menu-item>
          <el-menu-item index="/TableInfo">
            <i class="el-icon-setting"></i>
            <router-link to="/TableInfo" active-class="active"
              ><span>学生列表</span></router-link
            >
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>
  
  <script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>