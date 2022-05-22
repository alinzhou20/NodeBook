<script setup lang="ts">
	
import logoSrc from '@/assets/logo.png';  //导入logo
const logo_src = logoSrc; 

/* 输入框 */
import { ref,reactive,toRaw } from 'vue';  //引入 注意此处的文件都是从modules中引入的 与main.ts 无关
import axios from 'axios';
const searchTxt = ref(''); //输入框文本
var Data = reactive({  //存储返回值
      Detail: [],
    });
const emits = defineEmits(['toSearchView','toUserView']) //触发父组件事件
const toSearchView = function(){  //使用axios向后端发起搜索请求	
	axios.get('/api/book/searchByName',{
		params:{
			bookName: searchTxt.value  //应该和spring对应
		},
	}).then(res=>{ 
		Data.Detail = JSON.parse(JSON.stringify(res.data)); //此处可以获取外部的Data
	}).catch(err=>{console.log(err);})

    const outData = toRaw(Data) //父子组件传递的时候必须要求是ref 或者 reactive
	emits('toSearchView',outData)
}

/* 用户 */
import router from '@/router/index.ts'
const circleUrl ='https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
const username = ref('')
const password = ref('')
const login = ref(false)
const err_1 = ref(false)
const toLogin = function(){
	
	console.log(username)
	axios.post('/api/user/login',{
		username: username.value,
		password: password.value,
	},{headers: {'Content-Type': 'application/json; charset=utf-8'}}, //post采用json的方式传递
	).then(res=>{ 
		Data.Detail = JSON.parse(JSON.stringify(res.data)); //此处可以获取外部的Data
        
		if(toRaw(Data.Detail).code==1){
			console.log('log_err')
		}
		if(toRaw(Data.Detail).code==0){
			login.value = false
			window.sessionStorage.setItem('user', toRaw(Data.Detail).data)
			emits('toUserView',null)
		 }
		
	}).catch(err=>{console.log(err);})
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
		<el-input v-model="searchTxt" placeholder="请输入你想要查询的书籍" />
	</el-col>
    <el-col :span="2" class="col" style="margin-left:0;">
		<el-button type="primary" @click="toSearchView">搜索</el-button>
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
		        <el-dropdown-item @click="login=true">
					用户登录
				</el-dropdown-item>
		        <el-dropdown-item @click="register">
					用户注册
				</el-dropdown-item>
		        <el-dropdown-item disabled>书架查询</el-dropdown-item>
		        <el-dropdown-item divided>继续阅读</el-dropdown-item>
		      </el-dropdown-menu>
		    </template> 		
		</el-dropdown>
		
		  <el-dialog v-model="login" title="用户登录">
			  <span style="color: red;">{{toRaw(Data.Detail).data}}</span>
		    <el-form >
				<el-form-item label="用户名">
				      <el-input v-model="username" />
				    </el-form-item>
				    <el-form-item label="密码">
				      <el-input v-model="password" />
				    </el-form-item>
		    </el-form>
		    <template #footer>
		      <span>
		        <el-button @click="login = false">取消</el-button>
		        <el-button type="primary" @click="toLogin" >确定</el-button>
		      </span>
		    </template>
		  </el-dialog>
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

