<script setup lang="ts">
	
import logoSrc from '@/assets/logo.png';  //导入logo
import { ref,reactive,toRaw } from 'vue';  //引入 注意此处的文件都是从modules中引入的 与main.ts 无关
import axios from 'axios';
const emits = defineEmits(['toWriterInfo']) //触发父组件事件
import router from '@/router/index.ts'
const username = ref('')
const password = ref('')
const nickname = ref('')
const logo_src = logoSrc; 
const searchTxt = ref(''); //输入框文本
const login = ref(false)
const register = ref(true)
const err_1 = ref(false)

var Data = reactive({  //存储返回值
      Detail: [],
    });


/* 登录 */
const toLogin = function(){
	
	axios.post('/api/writer',{
		method:'login',
		username: username.value,
		password: password.value,
	},{headers: {'Content-Type': 'application/json; charset=utf-8'}}, //post采用json的方式传递
	).then(res=>{ 
		Data.Detail = JSON.parse(JSON.stringify(res.data)); //此处可以获取外部的Data
		if(toRaw(Data.Detail).code==0){
			login.value = false
			window.sessionStorage.setItem('writer', toRaw(Data.Detail).data)
			router.push({name:'writerBody'})
		 }
		
	}).catch(err=>{console.log(err);})
}


/* 注册 */
const toRegister = function(){
	
	axios.post('/api/writer',{
		method:'register',
		username: username.value,
		password: password.value,
		nickname: nickname.value,
	},{headers: {'Content-Type': 'application/json; charset=utf-8'}},
	).then(res=>{ 
		Data.Detail = JSON.parse(JSON.stringify(res.data)); //此处可以获取外部的Data
		if(toRaw(Data.Detail).code==0){
			register.value = false
			window.sessionStorage.setItem('writer', toRaw(Data.Detail).data)
			router.push({name:'writerBody'})
		 }
		
	}).catch(err=>{console.log(err);})
}


let writerRigster=ref(true)
let writerLogin=ref(false)

const toRigster = function(){
	writerRigster.value = false
	writerLogin.value = true
}

</script>
<template>
	
<div id="nav">  
<el-form v-model="register" style="color: #fff;" :class="{ writerRigst: writerRigster }">
	<span>作者注册</span>
	<span style="color: red;">{{Data.Detail.msg}}</span>
	<el-form-item label="用户名">
	      <el-input v-model="username" />
	    </el-form-item>
	    <el-form-item label="密码">
	      <el-input v-model="password" />
	    </el-form-item>
		<el-form-item label="昵称">
		  <el-input v-model="nickname" />
		</el-form-item>
		<span>
		    <el-button type="primary" @click="toRegister()" >注册</el-button>
		</span>
</el-form>

<el-form v-model="login" style="color: #fff;" :class="{ writerLogi: writerLogin }">
	<span>作者登录</span>
	<span style="color: red;">{{Data.Detail.msg}}</span>
	<el-form-item label="用户名">
	      <el-input v-model="username" />
	    </el-form-item>
	    <el-form-item label="密码">
	      <el-input v-model="password" />
	    </el-form-item>
		<span>
		    <el-button type="primary" @click="toRigster()" >去注册</el-button>
			<el-button type="primary" @click="toLogin()" >登录</el-button>
		</span>
</el-form>

</div>
		  
</template>





<style scoped>
	#nav{
		font-size: 20px;
		margin-top: 200px;
		margin: auto;
		width: 500px;
		height: 80px;
	}
	
	.writerRigst{
		display: none;
	}
	.writerLogi{
		display: none;
	}
</style>

