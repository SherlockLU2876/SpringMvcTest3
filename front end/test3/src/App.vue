<template>
  <div id="app">
    <el-container>
      <el-header class="header">
        <NvaHeader />
      </el-header>
      <el-container>
        <NvaMenu />
        <el-main>
          <router-view v-if="isShow"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import NvaHeader from "./components/NavHeader.vue";
import NvaMenu from "./components/NvaMenu.vue";
export default {
  name: "App",
  components: {
    NvaMenu,
    NvaHeader,
  },
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isShow: true
    }
  },
  methods: {
    reload () {
      this.isShow= false
      this.$nextTick(function () {
        this.isShow= true
      })
    }
  }
};
</script>

<style>
.header {
  background-color: #409eff;
  color: #fff;
  line-height: 60px;
}
</style>
