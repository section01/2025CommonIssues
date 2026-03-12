import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../components/LoginForm.vue'
import MenuScreen from '../components/MenuScreen.vue'
import KintaiInput from '../components/KintaiInput.vue'
import KintaiList from '../components/KintaiList.vue'
import KintaiDetail from '../components/KintaiDetail.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginForm
  },
  {
    path: '/menu',
    name: 'Menu',
    component: MenuScreen
  },
  {
    path: '/kintai-input',
    name: 'KintaiInput',
    component: KintaiInput
  },
  {
    path: '/kintai-list',
    name: 'KintaiList',
    component: KintaiList
  },
  {
	path:'/kintai-detail',
	name:'KintaiDetail',
	component: KintaiDetail
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
