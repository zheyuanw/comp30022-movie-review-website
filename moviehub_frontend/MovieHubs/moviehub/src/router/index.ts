import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
  { path: '/', redirect: '/Moviehub/mainpage/visitor' },
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
    path: '/Moviehub/mainpage/visitor',
    name: 'MoviemainPage',
    meta: {
      index: true,
      title: '电影主页面',
    },
    component: () => import('@/views/MainPageVisitor.vue'),
  },
  {
    path: '/Moviehub/mainpageuser/:userID(\\d+)',
    name: 'MoviemainPage2',
    meta: {
      index: true,
      title: '电影主页面2',
    },
    component: () => import('@/views/MainPageUser.vue'),
  },
  {
    path: '/Moviehub/dashboard/:userID(\\d+)',
    name: 'MovieDashbord',
    meta: {
      index: true,
      title: '用户主界面',
    },
    component: () => import('@/views/DashBoard.vue'),
  },
  {
    path: '/Moviehub/dashboard/:userID(\\d+)/edit',
    name: 'MovieDashbordedit',
    meta: {
      index: true,
      title: '编辑',
    },
    component: () => import('@/views/EditPage.vue'),
  },
  {
    path: '/Moviehub/dashboard/:userID(\\d+)/post',
    name: 'MovieDashbordpost',
    meta: {
      index: true,
      title: '发布',
    },
    component: () => import('@/views/PostPage.vue'),
  },

  {
    path: '/Moviehub/SingleEditDelete/:userID(\\d+)',
    name: 'editdelete',
    meta: {
      title: '修改删除界面',
      needLogin: true,
    },
    component: () => import('@/views/EditDelete.vue'),
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
  {
    path: '/Moviehub/forgetpassword',
    name: 'forgetpassword',
    meta: {
      title: '忘记密码',
      needLogin: true,
    },
    component: () => import('@/views/ForgetPassword.vue'),
  },

  {
    path: '/Moviehub/contentpage',
    name: 'ContentPage',
    meta: {
      index: true,
      title: '内容',
    },
    component: () => import('@/views/ContentVisitor.vue'),
  },
  {
    path: '/Moviehub/contentpage/:userID(\\d+)',
    name: 'ContentPageUser',
    meta: {
      index: true,
      title: '登录内容',
    },
    component: () => import('@/views/ContentUser.vue'),
  },
  {
    path: '/Moviehub/dashboard/:userID(\\d+)/setting',
    name: 'SettingPage',
    meta: {
      index: true,
      title: '设置',
    },
    component: () => import('@/views/SettingPage.vue'),
  },

  {
    path: '/:path(.*)*',
    name: '找不到页面',
    meta: {
      title: '忘记密码',
      needLogin: true,
    },
    component: () => import('@/views/PageNotFound.vue'),
  },
]
const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
export default router
