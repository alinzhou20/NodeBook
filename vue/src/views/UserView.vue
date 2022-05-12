<script setup lang="ts">

import {ref,toRaw,onMounted,reactive,require} from 'vue'
import axios from 'axios';
import type { TabsPaneContext } from 'element-plus'
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}
const activeName = ref('first')
const user = window.sessionStorage.getItem('user')
var Data = reactive({  //存储返回值
      Detail: [],
    });

onMounted(()=>{
	axios.get('/api/book/getUserBook',{
		params:{
			userId: user
		}
	}).then(res=>{ 
		/* 此处可以获取外部的Data */
		
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));
		console.log(Data.Detail)
		
	}).catch(err=>{console.log(err);})
	
	
}) 

/* 给图片动态地添加本地的前缀
   ！！ 此方法的主要目的是解决 v-for中的src不能使用{{}}，必须要求require的问题*/
const getImg = function(url){
	console.log(url)
	let img = require('@/assets/ImgData/'+url);
	return img;
}

const emits = defineEmits(['toBookView'])
const toBookView = (outData) => {
  console.log(outData)
  window.sessionStorage.setItem('tar_book', outData)
  emits('toBookView',outData)
}
</script>
<template>
	
   <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
     <el-tab-pane label="我的书架"  name="first">
		 
		 <el-row :gutter="20" style="margin-top: 20px;">
		 	<el-col :span="6" v-for="item in Data.Detail" :key="item">
		 		<div class="item">
		 			<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
		 			<img style="width: 350px; height: 200px; " :src="getImg(item.imgUrl)" class="image"/>
		 			<div>
		 				<span>{{item.name}}</span>
		 				<div class="bottom">
		 					<el-button type="text" class="button" @click="toBookView(item.id)">点击查看</el-button>
		 				</div>
		 			</div>
		 		  </el-card>
		 		</div>
		 	</el-col>			
		 </el-row>
		 
	 </el-tab-pane>
     <el-tab-pane label="Config" name="second">
		 Config
	 </el-tab-pane>

   </el-tabs>

</template>

<style>
	.demo-tabs > .el-tabs__content {
	margin: auto;
	width: 1300px;
	background-color: aquamarine;
	}
	
	.el-tabs__nav{
		margin-left: 400px;
	}
	.el-tabs__item {
		margin-top: 20px;
		margin-left: 100px;
		font-size: 30px;
		color: aliceblue;
	}

</style>