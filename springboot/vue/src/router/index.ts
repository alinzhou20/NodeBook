/* 该文件专门用于创建整个应用的路由器 */
import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import Home from '../main/Home.vue'
import Read from '../main/Read.vue'

const routes: Array<RouteRecordRaw> = [
  {
	  /* 主页面*/
    path: '/',
    name: 'home',	
    component: Home,
	children:[
		{
		  path: '/',
		  name: 'indexView',
		  component: () => import('../views/IndexView.vue')
		},
		{
		  path: '/bookView',
		  name: 'bookView',
		  component: () => import('../views/BookView.vue'),
/* 		  children:[
			  {
				  path: '/',
				  components: {
					default:()=>import('../components/nav/BookNav.vue'),
					  nav:() => import('../components/nav/BookNav.vue'),
					  body:() => import('../components/body/BookBody.vue')
				  },
			  }
		  ] */
		},	
		{
		  path: '/searchView',
		  name: 'searchView',
		  component: () => import('../views/SearchView.vue')
		},
		{
			  /* 阅读页面*/
		  path: '/readView',
		  name: 'readView',
		  component: () => import('../views/ReadView.vue')
		},
		{
			  /* 用户页面*/
		  path: '/userView',
		  name: 'userView',
		  component: () => import('../views/UserView.vue')
		},
	]
  },


]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
