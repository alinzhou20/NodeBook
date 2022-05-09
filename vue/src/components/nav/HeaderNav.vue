<script setup lang="ts">
	
/* logo 导入本地图片*/

import logoSrc from '@/assets/logo.png'; 
const logo_src = logoSrc;


/* 输入框 */

//引入 注意此处的文件都是从modules中引入的 与main.ts 无关
import { ref,reactive,toRaw } from 'vue';
import axios from 'axios';

//获取输入框文本
const search = ref('');

//存储搜索后的返回值
var searchData = reactive({
      searchDetail: [],
    });
	
//触发父组件事件
const emits = defineEmits(['toSearch'])
	
//使用axios向后端发起搜索请求	
const toSearch = function(){
	axios.get('/api/book/search',{
		params:{
			search: search.value
		}
	}).then(res=>{ 
		/* 此处可以获取外部的Data */
		searchData.searchDetail = JSON.parse(JSON.stringify(res.data));
		
	}).catch(err=>{console.log(err);})

    /* 父子组件传递的时候必须要求是ref 或者 reactive*/
    const outData = toRaw(searchData)
	emits('toSearch',outData)
}




/* 用户 */
import router from '@/router/index.ts'
const circleUrl ='https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'

function toLogin(){
   router.push({name:'login'})
}


</script>

<template>
	
<div id="nav">
  <el-row :gutter="10" id="row">  
	<!-- logo -->
    <el-col :span="1" :offset="2" class="col" style="margin-right:10px;">
		<el-image style="width: 60px; height: 60px; " :src="logo_src" />
	</el-col>
	<el-col :span="2" class="col" style="margin-left:0; color: aliceblue;font-size: 25px;">
		终点阅读
	</el-col>
	
	<!-- 搜索框 -->
    <el-col :span="9" :offset="2" class="col" style="margin-right:10px;">
		<el-input v-model="search" placeholder="请输入你想要查询的书籍" />
	</el-col>
    <el-col :span="2" class="col" style="margin-left:0;">
		<el-button type="primary" @click="toSearch">搜索</el-button>
	</el-col>

    <!-- 用户信息 -->
	<el-col :span="2" :offset="1" class="col">
		<el-dropdown>
		    <div>
			  <!-- 头像 -->
		      <el-avatar :size="size" :src="circleUrl" />
			</div>
			<template #dropdown>
		      <el-dropdown-menu>
		        <el-dropdown-item @click="toLogin">用户登录</el-dropdown-item>
		        <el-dropdown-item>用户注册</el-dropdown-item>
		        <el-dropdown-item disabled>书架查询</el-dropdown-item>
		        <el-dropdown-item divided>继续阅读</el-dropdown-item>
		      </el-dropdown-menu>
		    </template> 
			
		</el-dropdown>
	</el-col> 
  </el-row>
</div>
</template>





<style scoped>
	#nav{
		margin-top: 10px;
		height: 80px;
	}
	
	#row .col{
		margin: auto;
	}
</style>

