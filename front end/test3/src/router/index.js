import VueRouter from 'vue-router'
import TableAdd from '../pages/TableAdd.vue'
import TableInfo from '../pages/TableInfo.vue'
const router = new VueRouter({
    routes: [{
        name: 'TableAdd',
        path: '/TableAdd',
        component: TableAdd,

    }, {
        name: 'TableInfo',
        path: '/TableInfo',
        component: TableInfo,

    }]
})
export default router