import Vue from 'vue'
import App from './App.vue'
//引入router
import VueRouter from 'vue-router'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(ElementUI);
Vue.use(ViewUI);

new Vue({
    render: h => h(App),
    router: router,

}).$mount('#app')