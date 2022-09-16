import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
  {
    path: '/Moviehub/mainpage',
    name: 'MoviemainPage',
    meta: {
      index: true,
      title: '电影主页面',
    },
    component: () => import('@/views/RegisterPage.vue'),
  },
  {
    path: '/Moviehub/loginpage',
    name: 'login',
    meta: {
      title: '登录界面',
      needLogin: true,
    },
    component: () => import('@/views/LoginPage.vue'),
  },
  {
    path: '/Moviehub/registerpage',
    name: 'Register',
    meta: {
      title: '注册界面',
      needLogin: true,
    },
    component: () => import('@/views/RegisterPage.vue'),
  },
]
const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
export default router
